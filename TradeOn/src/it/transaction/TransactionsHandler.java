package it.transaction;

import java.util.HashSet;
import java.util.concurrent.Callable;

import lt.beans.Enumerations;
import lt.beans.Enumerations.ItemType;
import lt.beans.Item;
import lt.beans.Offer;
import lt.beans.Enumerations.OfferStatus;
import lt.maps.MapsHolder;

public class TransactionsHandler implements Callable<Boolean>{

	private Offer offer = null;
	
	public TransactionsHandler(Offer offer) {
		this.offer = offer;
	}
	
	@Override
	public Boolean call() {
		//check if userId exists in the lock map
		synchronized (MapsHolder.INSTANCE.getUserLockMap()) {
			//lock items exchanged by sender
			if(!MapsHolder.INSTANCE.getUserLockMap().containsKey(offer.getSenderId())) {
				//add the user in the lock map
				HashSet<Integer> itemSet = new HashSet<Integer>();
				for(Item item:offer.getItemsToBeSent()){
					itemSet.add(item.getItemId());
				}
				MapsHolder.INSTANCE.getUserLockMap().put(offer.getSenderId(), itemSet);
			} else {
				HashSet<Integer> itemset = MapsHolder.INSTANCE.getUserLockMap().get(offer.getSenderId());
				boolean alreadyAcceptedBySomeone = false;
				for(Item item : offer.getItemsToBeSent()) {
					if(itemset.contains(item.getItemId())) {
						alreadyAcceptedBySomeone = true;
						break;
					}
				}
				if(alreadyAcceptedBySomeone) {
					return false;
				} else {
					for(Item item : offer.getItemsToBeSent()) {
						if(item.getType()==ItemType.POINTS) {
							if(MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).getPoints() < item.getPoints()) {
								return false;
							}
						}
					}
					//lock the items to be received from the sender
					for(Item item : offer.getItemsToBeSent()) {
						itemset.add(item.getItemId());
					}
				}
			}
			
			//lock items exchanged by receiver
			if(!MapsHolder.INSTANCE.getUserLockMap().containsKey(offer.getReceiverId())) {
				//add the user in the lock map
				HashSet<Integer> itemSet = new HashSet<Integer>();
				for(Item item:offer.getItemsToBeReceived()){
					itemSet.add(item.getItemId());
				}
				MapsHolder.INSTANCE.getUserLockMap().put(offer.getReceiverId(), itemSet);
			} else {
				HashSet<Integer> itemset = MapsHolder.INSTANCE.getUserLockMap().get(offer.getReceiverId());
				boolean alreadyAcceptedBySomeone = false;
				for(Item item : offer.getItemsToBeReceived()) {
					if(itemset.contains(item.getItemId())) {
						alreadyAcceptedBySomeone = true;
						break;
					}
				}
				if(alreadyAcceptedBySomeone) {
					return false;
				} else {
					for(Item item : offer.getItemsToBeReceived()) {
						if(item.getType()==ItemType.POINTS) {
							if(MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).getPoints() < item.getPoints()) {
								return false;
							}
						}
					}
					//lock the items to be received from the sender
					for(Item item : offer.getItemsToBeReceived()) {
						itemset.add(item.getItemId());
					}
				}
			}
		}//items locked
		
		//now update all the objects
		for(Item item:offer.getItemsToBeSent()) {
			if(item.getType() != Enumerations.ItemType.POINTS) {
				//change the owner
				item.setUserId(offer.getReceiverId());
			} else {
				//exchange the points
				int points = MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).getPoints();
				MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).setPoints(points-item.getPoints());

				points = MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).getPoints();
				MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).setPoints(points+item.getPoints());
			}
			item.setStatus(Enumerations.ItemStatus.TRADED);
			MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).getItemsForTrade().remove(item);
		}
		
		//now update all the objects
		for(Item item:offer.getItemsToBeReceived()) {
			if(item.getType() != Enumerations.ItemType.POINTS) {
				//change the owner
				item.setUserId(offer.getSenderId());
			} else {
				//exchange the points
				int points = MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).getPoints();
				MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).setPoints(points-item.getPoints());

				points = MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).getPoints();
				MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).setPoints(points+item.getPoints());
			}
			item.setStatus(Enumerations.ItemStatus.TRADED);
			MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).getItemsForTrade().remove(item);
		}
		
		//cancel offers that contain items that the sender is selling
		for(Item item: offer.getItemsToBeSent()) {
			for(Offer offr : item.getOffersIncludedIn()) {
				if(!offr.equals(offer)) {
					offr.setStatus(Enumerations.OfferStatus.CANCELLED);
					MapsHolder.INSTANCE.getUserMap().get(offer.getSenderId()).getOffersMade().remove(offr);
				}
			}
		}
		
		//cancel offers that contain items that the receiver is selling
		for(Item item: offer.getItemsToBeReceived()) {
			for(Offer offr : item.getOffersIncludedIn()) {
				if(!offr.equals(offer)) {
					offr.setStatus(Enumerations.OfferStatus.CANCELLED);
					MapsHolder.INSTANCE.getUserMap().get(offer.getReceiverId()).getOffersMade().remove(offr);
				}
			}
		}
		
		offer.setStatus(OfferStatus.ACCEPTED);
		
		synchronized (MapsHolder.INSTANCE.getUserLockMap()) {
			for(Item item:offer.getItemsToBeSent()) {
				MapsHolder.INSTANCE.getUserLockMap().get(offer.getSenderId()).remove(item.getItemId());
			}
			
			for(Item item:offer.getItemsToBeReceived()) {
				MapsHolder.INSTANCE.getUserLockMap().get(offer.getReceiverId()).remove(item.getItemId());
			}
		}
		return true;
	}
	
}
