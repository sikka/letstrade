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
		ACCEPTED(0), PENDING(1), CANCELLED(2), INVALID(3);
		
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
	
	public static enum OfferItemType{
		ITEM(0), POINTS(1);
		
		private final int value;
		private OfferItemType(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	};
	
	public static enum OfferItemSide{
		SENDER(0), RECEIVER(1);
		
		private final int value;
		private OfferItemSide(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
	};
}
