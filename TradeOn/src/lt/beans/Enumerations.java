package lt.beans;


public class Enumerations {
	public static enum ItemType{
		SKILL(0), PRODUCT(1), SERVICES(2);
		private final int value;
		private ItemType(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	};
	public static enum OfferStatus{
		ACCEPTED(0), PENDING(1), CANCELLED(2);
		
		private final int value;
		private OfferStatus(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	};
	public static enum ItemStatus{
		NEEDS(0), HAVES(1), TRADED(2);
		
		private final int value;
		private ItemStatus(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	};
}
