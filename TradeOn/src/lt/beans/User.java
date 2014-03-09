package lt.beans;

import java.sql.SQLException;
import java.util.List;


public class User {
	private int uid;
	private int profileImageId;

	public int getProfileImageId() {
		return profileImageId;
	}

	public void setProfileImageId(int profileImageId) {
		this.profileImageId = profileImageId;
	}

	private String userName;
	private String emailAddress;

	private int points;
	
	private int avgRating;
	private List<Rating> reviews;
	private List<Transaction> transactions;
	
	private List<Item> wishList;
	private List<Item> tradeList;
	private List<Item> obtainedList;

	private List<Offer> offersMade;
	private List<Offer> offersReceived;
	
	
	
	public List<Item> getObtainedList() {
		return obtainedList;
	}

	public void setObtainedList(List<Item> obtainedList) {
		this.obtainedList = obtainedList;
	}	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(int avgRating) {
		this.avgRating = avgRating;
	}

	public List<Rating> getReviews() {
		return reviews;
	}

	public void setReviews(List<Rating> reviews) {
		this.reviews = reviews;
	}

	public List<Item> getWishList() {
		return wishList;
	}

	public void setWishList(List<Item> wishList) {
		this.wishList = wishList;
	}

	public List<Item> getTradeList() {
		return tradeList;
	}

	public void setTradeList(List<Item> tradeList) {
		this.tradeList = tradeList;
	}

	public List<Offer> getOffersMade() {
		return offersMade;
	}

	public void setOffersMade(List<Offer> offersMade) {
		this.offersMade = offersMade;
	}

	public List<Offer> getOffersReceived() {
		return offersReceived;
	}

	public void setOffersReceived(List<Offer> offersReceived) {
		this.offersReceived = offersReceived;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	
	// Behavior
	
	public int createOffer(List<Item> sentItems, List<Item> receivedItems){
		return 0;
	}
	
	public void acceptOffer(int offerId){
		
	}
	
	public void cancelOffer(int offerId){
		
	}
	
	
	
	public void addItemsToUserList(List<Item> items) throws SQLException{
		for(Item item : items){
			switch(item.getStatus().getValue()){
			case 0:
				wishList.add(item);
				break;
			case 1:
				tradeList.add(item);
				break;
			case 2:
				obtainedList.add(item);
			}
			
		}
		DBUtil.addItemsToUserList(uid, items);
	}
	
	public void removeItemsFromUserList(List<Item> items) throws SQLException{
		for(Item item : items){
			switch(item.getStatus().getValue()){
			case 0:
				wishList.remove(item);
				break;
			case 1:
				tradeList.remove(item);
				break;
			case 2:
				obtainedList.remove(item);
			}
			
		}
		DBUtil.removeItemsFromUserList(uid, items);
	}


}
