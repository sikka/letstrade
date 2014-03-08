package lt.beans;
import java.util.List;

public class Offer {
	private int offerId;
	private int senderId;
	private int receiverId;
	private Enumerations.OfferStatus status;
	private List<Item> itemsToBeSent;
	private List<Item> itemsToBeReceived;
	
	public List<Item> getItemsToBeSent() {
		return itemsToBeSent;
	}
	public void setItemsToBeSent(List<Item> itemsToBeSent) {
		this.itemsToBeSent = itemsToBeSent;
	}
	public List<Item> getItemsToBeReceived() {
		return itemsToBeReceived;
	}
	public void setItemsToBeReceived(List<Item> itemsToBeReceived) {
		this.itemsToBeReceived = itemsToBeReceived;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public int getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}
	public Enumerations.OfferStatus getStatus() {
		return status;
	}
	public void setStatus(Enumerations.OfferStatus status) {
		this.status = status;
	}
	
	
}
