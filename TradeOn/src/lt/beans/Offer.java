package lt.beans;

import java.sql.SQLException;
import java.util.List;

public class Offer {
	private int offerId;
	private int senderId;
	private int receiverId;
	private Enumerations.OfferStatus status;
	private List<Item> itemsToBeSent;
	private List<Item> itemsToBeReceived;
	
	private Enumerations.OfferItemType offerItemtype;
	
	public Enumerations.OfferItemType getOfferItemtype() {
		return offerItemtype;
	}
	public void setOfferItemtype(Enumerations.OfferItemType offerItemtype) {
		this.offerItemtype = offerItemtype;
	}
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
	
	
	
	// ==========================================================================
	// FUNCTIONS TO DO MODIFICATIONS ON THE OFFER OBJECT AND IN TURN TO DATABASE
	// ==========================================================================
	
	public void saveOfferStateToDb(Offer offer) throws SQLException{
		DBUtil.saveOfferStateToDb(offer);
	}
	
	public void makeOffer(Offer offer) throws SQLException{
		// Inserts a new record in the database and sets the offerId of the object.
		DBUtil.makeOffer(offer);
	}
	
	public void acceptOffer(Offer offer) throws SQLException{
		DBUtil.acceptOffer(offer);
		offer.setStatus(Enumerations.OfferStatus.ACCEPTED);
	}
	
	public void rejectOffer(Offer offer) throws SQLException{
		DBUtil.rejectOffer(offer);
		offer.setStatus(Enumerations.OfferStatus.CANCELLED);
	}
	
}