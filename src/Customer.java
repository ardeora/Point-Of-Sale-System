import java.util.ArrayList;

public class Customer {
	private int tableID;
	private int customerNum;
	private String orderDate;
	private String orderTime;
	private ArrayList<Integer[]> foodlist;
	
	public Customer(int tableID, int customerNum, String orderDate, String orderTime) {
		super();
		this.tableID = tableID;
		this.customerNum = customerNum;
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.foodlist = new ArrayList<>();
	}

	public int getTableID() {
		return tableID;
	}

	public int getCustomerNum() {
		return customerNum;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public ArrayList<Integer[]> getFoodlist() {
		return foodlist;
	}

	public void addFood(int foodCode, int qty) {
		Integer[] newFood = new Integer[2];
		newFood[0] = foodCode;
		newFood[1] = qty;
		foodlist.add(newFood);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return tableID + " "  + customerNum + " " + orderDate + " " + orderTime+ " " + foodlist;
	}
	
}
