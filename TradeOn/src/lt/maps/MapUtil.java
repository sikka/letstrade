package lt.maps;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lt.beans.Item;
import lt.beans.Offer;
import lt.beans.User;

public class MapUtil {
	
	public void makeOffer(int senderId, int receiverId, List<Integer> itemIdsToSend, List<Integer> itemIdsToReceive){
		MapsHolder mh = MapsHolder.getInstance();
		Map<Integer, User> userMap = mh.getUserMap();
		Map<Integer, Item> itemMap = mh.getItemMap();
		Map<Integer, Offer> offerMap = mh.getOfferMap();
		
		// FETCH USER OBJECTS FROM MAPS
		User sender = userMap.get(senderId);
		User receiver = userMap.get(receiverId);
		
		// FETCH ITEM OBJECTS FROM MAPS
		List<Item> itemsToBeSent = new ArrayList<Item>();
		List<Item> itemsToBeReceived = new ArrayList<Item>();
		
		for(int id : itemIdsToSend){
			itemsToBeSent.add(itemMap.get(id));
		}
		for(int id : itemIdsToReceive){
			itemsToBeReceived.add(itemMap.get(id));
		}
		
		Offer offer = new Offer();
		offer.setSenderId(senderId);
		offer.setReceiverId(receiverId);
		offer.setItemsToBeReceived(itemsToBeReceived);
		offer.setItemsToBeSent(itemsToBeSent);
		try{
		offer.makeOffer(offer);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
			
	}
	
	
}
