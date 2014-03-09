package lt.beans;


public class Rating {
	private int ratingUserId;
	private int ratedUserId;
	private int rating;
	private String review;
	
	
	public int getRatingUserId() {
		return ratingUserId;
	}
	public void setRatingUserId(int ratingUserId) {
		this.ratingUserId = ratingUserId;
	}
	public int getRatedUserId() {
		return ratedUserId;
	}
	public void setRatedUserId(int ratedUserId) {
		this.ratedUserId = ratedUserId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	
	
	
}
