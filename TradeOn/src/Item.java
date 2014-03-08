import java.util.List;

public class Item {
	private String title;
	private int userId;
	private int itemId;
	private Enumerations.ItemType type;
	
	private List<Offer> offersIncludedIn;
	private Enumerations.ItemStatus status;
	
	// Getters and Setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public Enumerations.ItemType getType() {
		return type;
	}
	public void setType(Enumerations.ItemType type) {
		this.type = type;
	}
	public List<Offer> getOffersIncludedIn() {
		return offersIncludedIn;
	}
	public void setOffersIncludedIn(List<Offer> offersIncludedIn) {
		this.offersIncludedIn = offersIncludedIn;
	}
	public Enumerations.ItemStatus getStatus() {
		return status;
	}
	public void setStatus(Enumerations.ItemStatus status) {
		this.status = status;
	}
	
}
