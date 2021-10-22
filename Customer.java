
import java.util.Map;

public class Customer extends User{
	
	enum Rating {
		RATING_1(1), RATING_2(2), RATING_3(3), RATING_4(4), RATING_5(5);
		private int rating;
		
		private Rating(int rating) {
			this.rating = rating;
		}
		
		public int getRating() {
			return this.rating;
		}
	}
	
	private Rating rating;
	private int credit;
	
	
	public Customer(String account, String fname, String lname, String phoneNum, String rating, String credit) {
		super(account, fname, lname, phoneNum);
		
		for(Rating custRating: Rating.values()) {
			if(custRating.getRating() == Integer.parseInt(rating)) {
				this.rating = custRating;
			}
		}
		this.credit = Integer.parseInt(credit);
	}
    
	public int getRating() {
		return rating.getRating();
	}

	public void setRating(String rating) {
		for(Rating custRating: Rating.values()) {
			if(custRating.getRating() == Integer.parseInt(rating)) {
				this.rating = custRating;
			}
		}
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public int calculateTotalOrderCost(Map<String, Order> orderMap) {
		int custTotalOrderPrice = 0;
		for(Order order:orderMap.values()) {
			if(this.account.equalsIgnoreCase(order.getCustAcc())) {
				custTotalOrderPrice = custTotalOrderPrice + order.calculateOrderPrice();
			}
		}
		return custTotalOrderPrice;
	}

}
