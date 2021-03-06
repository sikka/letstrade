package lt.beans;

import java.util.List;

public class Item {
	private String title;
	private int userId;
	private int itemId;
	private Enumerations.ItemType type;
	private String description;
	private int points;
	private int picId;
	private List<Offer> offersIncludedIn;
	private Enumerations.ItemStatus status;
	private String picUri;
	
	public String getPicUri() {
		return picUri;
	}
	public void setPicUri(String picUri) {
		this.picUri = picUri;
	}
	public int getPicId() {
		return picId;
	}
	public void setPicId(int picId) {
		this.picId = picId;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
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
