package lt.maps;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lt.beans.DBUtil;
import lt.beans.Enumerations;
import lt.beans.Item;
import lt.beans.ItemsInOffer;
import lt.beans.Offer;
import lt.beans.User;
import lt.beans.Enumerations.*;

public class MapsHolder {
	private static MapsHolder instance = null;

	private MapsHolder() {
		loadMaps();
	}
	
	public static MapsHolder getInstance(){
		if(instance == null){
			synchronized (MapsHolder.class){
				if(instance == null){
					instance = new MapsHolder();
				}
			}
		}
		return instance;
	}
	
	
	private void loadMaps() {
		loadUserMap();
		loadItemMap();
		loadOfferMap();
		
		populateUserItemLists();
		populateUserOfferLists();
		
		populateItemOfferLists();
	}

	
	private void populateItemOfferLists() {
		List<ItemsInOffer> itemsInOfferList = DBUtil.getItemsInOffer();
		for(ItemsInOffer itemsInOffer : itemsInOfferList){
			Offer offer = offerMap.get(itemsInOffer.getOfferId());
			Item item = itemMap.get(itemsInOffer.getItemId());
			item.getOffersIncludedIn().add(offer);
		}
		
	}

	private void populateUserOfferLists() {
		for(Offer offer: offerMap.values()){
			int senderId = offer.getSenderId();
			int receiverId = offer.getReceiverId();
			switch(offer.getStatus()){
			case PENDING:
				userMap.get(senderId).getOffersMade().add(offer);
				userMap.get(receiverId).getOffersReceived().add(offer);
				break;
			case ACCEPTED:
				break;
			case CANCELLED:
				break;
			}
		}
		
	}

	private void populateUserItemLists() {
		for(Item item: itemMap.values()){
			int userId = item.getUserId();
			switch(item.getStatus()){
			case HAVES:
				userMap.get(userId).getTradeList().add(item);
				break;
			case NEEDS:
				userMap.get(userId).getWishList().add(item);
				break;
			case TRADED:
				userMap.get(userId).getObtainedList().add(item);
				break;
			default:
				break;
			
			}
		}		
	}

	private void loadUserMap() {
		List<User> userList = DBUtil.getAllUsers();
		
		for(User user: userList){
			userMap.put(user.getUid(), user);
		}
	}
	
	private void loadItemMap() {
		List<Item> itemList = DBUtil.getAllItems();
		
		for(Item item: itemList){
			itemMap.put(item.getItemId(), item);
		}
	}

	private void loadOfferMap() {
		List<Offer> offerList = DBUtil.getAllOffers();
		
		for(Offer offer: offerList){
			offerMap.put(offer.getOfferId(), offer);
		}
	}

	



	private Map<Integer, User> userMap = new ConcurrentHashMap<Integer, User>();
	
	private Map<Integer, Item> itemMap = new ConcurrentHashMap<Integer, Item>();
	
	private Map<Integer, Offer> offerMap = new ConcurrentHashMap<Integer, Offer>();
	
	private Map<Integer, HashSet<Integer>> userLockMap = new ConcurrentHashMap<Integer, HashSet<Integer>>();

	public Map<Integer, User> getUserMap() {
		return userMap;
	}

	public Map<Integer, Item> getItemMap() {
		return itemMap;
	}

	public Map<Integer, Offer> getOfferMap() {
		return offerMap;
	}

	public Map<Integer, HashSet<Integer>> getUserLockMap() {
		return userLockMap;
	}
}