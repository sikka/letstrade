package lt.beans;
import java.util.List;


public class User {
	private String uid;
	private String userName;
	private String emailAddress;
	private int points;
	
	private int avgRating;
	private List<Rating> reviews;
	private List<Transaction> transactions;
	private List<Item> Wishlist;
	private List<Item> ItemsForTrade;

	private List<Offer> offersMade;
	private List<Offer> offersReviewed;
	
	
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

	public List<Item> getWishlist() {
		return Wishlist;
	}

	public void setWishlist(List<Item> wishlist) {
		Wishlist = wishlist;
	}

	public List<Item> getItemsForTrade() {
		return ItemsForTrade;
	}

	public void setItemsForTrade(List<Item> itemsForTrade) {
		ItemsForTrade = itemsForTrade;
	}

	public List<Offer> getOffersMade() {
		return offersMade;
	}

	public void setOffersMade(List<Offer> offersMade) {
		this.offersMade = offersMade;
	}

	public List<Offer> getOffersReviewed() {
		return offersReviewed;
	}

	public void setOffersReviewed(List<Offer> offersReviewed) {
		this.offersReviewed = offersReviewed;
	}

	public String getUid() {
		return uid;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	
	
	// Behavior
	
	public int createOffer(List<Item> sentItems, List<Item> receivedItems){
		return 0;
	}
	
	public void acceptOffer(int offerId){
		
	}
	
	public void cancelOffer(int offerId){
		
	}
}
