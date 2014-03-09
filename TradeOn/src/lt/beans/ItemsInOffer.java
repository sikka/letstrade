package lt.beans;

public class ItemsInOffer {
	private int offerId;
	private char offerItemType;
	private Enumerations.ItemType itemType;
	private int itemId;
	private int points;
	
	
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public char getOfferItemType() {
		return offerItemType;
	}
	public void setOfferItemType(char offerItemType) {
		this.offerItemType = offerItemType;
	}
	public Enumerations.ItemType getItemType() {
		return itemType;
	}
	public void setItemType(Enumerations.ItemType itemType) {
		this.itemType = itemType;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
}
