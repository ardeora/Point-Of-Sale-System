import java.util.HashMap;

public class Menu {

	private HashMap<Integer, String> foodNames;
	private HashMap<Integer, Integer> foodPrices;
	
	public Menu() {
		this.foodNames = new HashMap<>();
		foodNames.put(1, "Beer Braised Brisket");
		foodNames.put(2, "Pan-Seared Chicken Breasts with Shallots");
		foodNames.put(3, "16-oz Boneless Rib-Eye");
		foodNames.put(4, "Smoked Peppercorn Spare Ribs");
		foodNames.put(5, "Half-Shell Oysters");
		foodNames.put(6, "Wild Troll Caught King Salmon");
		foodNames.put(7, "Butter Garlic Shrimp");
		foodNames.put(8, "Chili Lobster");
		foodNames.put(9, "Steak Fries");
		foodNames.put(10, "Mashed Potatoes");
		foodNames.put(11, "Chef Salad");
		foodNames.put(12, "Soup of The Day");
		foodNames.put(13, "Cole Slaw");
		foodNames.put(14, "Quinoa Hummus Bowl");
		foodNames.put(15, "Sauteed Mushrooms Crepes");
		foodNames.put(16, "Beef Chili");
		
		this.foodPrices = new HashMap<>();
		foodPrices.put(1, 24);
		foodPrices.put(2, 27);
		foodPrices.put(3, 32);
		foodPrices.put(4, 18);
		foodPrices.put(5, 24);
		foodPrices.put(6, 22);
		foodPrices.put(7, 30);
		foodPrices.put(8, 22);
		foodPrices.put(9, 10);
		foodPrices.put(10, 10);
		foodPrices.put(11, 14);
		foodPrices.put(12, 12);
		foodPrices.put(13, 11);
		foodPrices.put(14, 14);
		foodPrices.put(15, 16);
		foodPrices.put(16, 12);
	}

	public HashMap<Integer, String> getFoodNames() {
		return foodNames;
	}

	public HashMap<Integer, Integer> getFoodPrices() {
		return foodPrices;
	}
	
	
}
