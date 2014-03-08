package lt.maps;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lt.beans.Item;
import lt.beans.Offer;
import lt.beans.User;

public enum MapsHolder {
	INSTANCE;

	private MapsHolder() {
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
