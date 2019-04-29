import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.table.DefaultTableModel;

public class Database {

	private ArrayList<User> users;
	private ArrayList<Table> tablesReserved;
	private ArrayList<Customer> orders;
	
	public Database() {
		this.users = new ArrayList<>();
		this.tablesReserved = new ArrayList<>();
		this.orders= new ArrayList<>();
	
		refreshUserData();
		refreshTableData();
		refreshOrderData();
	}

	public void refreshUserData() {
		users.clear();
		try {
			File userFile = new File("res/DatabaseFiles/User.txt");
			Scanner userData = new Scanner(userFile);
			
			while (userData.hasNextLine()) {
				String store = userData.nextLine();
				String[] storeSplit = store.split(",");
				User newUser = new User(storeSplit[0], storeSplit[1], Integer.parseInt(storeSplit[2]));
				users.add(newUser);
			}
			userData.close();	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void refreshTableData() {
		tablesReserved.clear();
		try {
			File tableFile = new File("res/DatabaseFiles/Tables.txt");
			Scanner tableData = new Scanner(tableFile);
			
			while (tableData.hasNextLine()) {
				String store = tableData.nextLine();
				String[] storeSplit = store.split(",");
				String username = storeSplit[0];
				ArrayList<String> tables = new ArrayList<>();
				for (int i = 1; i < storeSplit.length; i++) {
					tables.add(storeSplit[i]);
				}
				tablesReserved.add(new Table(username, tables));
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void refreshOrderData() {
		orders.clear();
		try {
			File orderFile = new File("res/DatabaseFiles/Orders.txt");
			Scanner orderData = new Scanner(orderFile);
			
			while (orderData.hasNextLine()) {
				String store = orderData.nextLine();
				String[] storeSplit = store.split(",");
				
				int tableID = Integer.parseInt(storeSplit[0]);
				int customerNum = Integer.parseInt(storeSplit[1]);
				String orderDate = storeSplit[2];
				String orderTime = storeSplit[3];
				
				Customer newCustomer = new Customer(tableID, customerNum, orderDate, orderTime);
				
				for (int i = 4; i < storeSplit.length; i++) {
					int foodCode = Integer.parseInt(storeSplit[i]);
					int qty = Integer.parseInt(storeSplit[i + 1]);
					newCustomer.addFood(foodCode, qty);
					i++;
				}
				orders.add(newCustomer);
			}
			orderData.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int getLastCustomer(int tableID) {
		int lastCustomer = 0;
		for (int i = 0; i < orders.size() ; i++) {
			if (tableID == orders.get(i).getTableID()) {
				if (lastCustomer < orders.get(i).getCustomerNum()) {
					lastCustomer = orders.get(i).getCustomerNum();
				}
			}
		}
		
		return lastCustomer;
	}
	

	public ArrayList<User> getUsers() {
		return users;
	}

	public ArrayList<Table> getTablesReserved() {
		return tablesReserved;
	}
	
	public ArrayList<Customer> getOrders() {
		return orders;
	}

	public void updateUsers() {
		try {
			PrintWriter writer = new PrintWriter("res/DatabaseFiles/User.txt");
			for (int i = 0; i < users.size() - 1; i++) {
				User store = users.get(i);
				writer.print(store.getUsername() + "," + store.getPassword()+"," + store.getAuthorized());
				writer.print("\n");
			}
			
			User lastUser = users.get(users.size()-1);
			writer.print(lastUser.getUsername() + "," + lastUser.getPassword()+"," + lastUser.getAuthorized());
		
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void updateTablesReserved() {
		try {
			PrintWriter writer = new PrintWriter("res/DatabaseFiles/Tables.txt");
			for (int i = 0; i < tablesReserved.size() - 1; i++) {
				Table store = tablesReserved.get(i);
				writer.print(store.getUsername());
				
				for (int j = 0; j < store.getTablesReserved().size(); j++) {
					writer.print("," + store.getTablesReserved().get(j));
				}
				writer.print("\n");
			}
			
			Table lastTable = tablesReserved.get(tablesReserved.size()-1);
			writer.print(lastTable.getUsername());
			
			for (int j = 0; j < lastTable.getTablesReserved().size(); j++) {
				writer.print("," + lastTable.getTablesReserved().get(j));
			}
			writer.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void updateOrderFile() {
		try {
			PrintWriter writer = new PrintWriter("res/DatabaseFiles/Orders.txt");
			
			for (int i = 0; i < orders.size() - 1; i++) {
				Customer store = orders.get(i);
				writer.print(store.getTableID() + "," + store.getCustomerNum() + "," + store.getOrderDate() + "," + store.getOrderTime());
				for (int j = 0; j < store.getFoodlist().size(); j++) {
					writer.print("," + store.getFoodlist().get(j)[0] + "," + store.getFoodlist().get(j)[1]);
				}
				writer.print("\n");
			}
			
			Customer lastCustomer = orders.get(orders.size()-1);
			writer.print(lastCustomer.getTableID() + "," + lastCustomer.getCustomerNum() + "," + lastCustomer.getOrderDate() + "," + lastCustomer.getOrderTime());
			for (int j = 0; j < lastCustomer.getFoodlist().size(); j++) {
				writer.print("," + lastCustomer.getFoodlist().get(j)[0] + "," + lastCustomer.getFoodlist().get(j)[1]);
			}
			
			writer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String getPastOrders() {
		String content = "";
		try {
			File pastOrdersFile = new File("res/DatabaseFiles/PastOrders.csv");
			Scanner pastOrders = new Scanner(pastOrdersFile);
			content += pastOrders.nextLine();
			while (pastOrders.hasNextLine()) {
				content += "\n" + pastOrders.nextLine();
			}
			pastOrders.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return content;
	}
	
	public void writeToPastOrders(int TableID, Object[][] model) {
		String content = getPastOrders();
		System.out.println(content);
		try {
		
			PrintWriter writerd = new PrintWriter("res/DatabaseFiles/PastOrders.csv");
			writerd.print(content);
			
			String tableId = TableID+"";
			String dateStamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
			String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
			
			for (int i = 0; i < model.length; i++) {
				String foodName = (String) model[i][0];
				String foodPrice = (String) model[i][2];
				String foodqty = (String) (model[i][1] +"");
				writerd.print("\n" + tableId + "," + dateStamp  + "," + timeStamp  + "," + foodName  + "," +  foodqty  + "," +  foodPrice);
				//writerd.flush();
			}	
			
			
			
			writerd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void reserveNewTable(String username, int TableID) {
		boolean check = false;

		if (authorizedPersonnel(username)) {
			if (!serversTable(TableID)) {
				boolean adminAlreadtExists = false;
				
				for (int i = 0; i < tablesReserved.size(); i++) {
					
					if (username.compareTo(tablesReserved.get(i).getUsername()) == 0) {
						boolean tableAlreadyExists = false;
						String tableIDString = TableID+"";
						
						for (int j = 0; j < tablesReserved.get(i).getTablesReserved().size(); j++) {
							if (tableIDString.compareTo(tablesReserved.get(i).getTablesReserved().get(j)) == 0) {
								tableAlreadyExists = true;
							}
						}
						
						if (!tableAlreadyExists) {
							tablesReserved.get(i).getTablesReserved().add(tableIDString);
						}
						adminAlreadtExists = true;
					}
				}
				
				if (!adminAlreadtExists) {
					ArrayList<String> firstTable = new ArrayList<>();
					firstTable.add(TableID+"");
					Table firstTime = new Table(username, firstTable);
					tablesReserved.add(firstTime);
				}
			}
		} else {
			for (int i = 0; i < tablesReserved.size(); i++) {
				if (username.compareTo(tablesReserved.get(i).getUsername()) == 0) {
					boolean repeat = false;

					String tableIDString = ""+TableID;

					for (int j = 0; j < tablesReserved.get(i).getTablesReserved().size(); j++) {

						if (tableIDString.compareTo(tablesReserved.get(i).getTablesReserved().get(j)) == 0) {
							repeat = true;
						}
					}

					if (!repeat) {
						tablesReserved.get(i).getTablesReserved().add(""+TableID);
					}

					check = true;
				}
			}
			if (!check) {
				ArrayList<String> tablesReserved = new ArrayList<>();
				tablesReserved.add(""+TableID);
				Table newUser = new Table(username, tablesReserved);
				this.tablesReserved.add(newUser);
			}
		}
	}
	
	public ArrayList<Integer[]> getFoodList(int TableID){
		ArrayList<Integer[]> allStore = new ArrayList<>();
		for (int i = 0; i < orders.size(); i++) {
			if (TableID == orders.get(i).getTableID()) {
				allStore.addAll(orders.get(i).getFoodlist());
			}
		}
		return removeDuplicates(allStore);
	}
	
	private ArrayList<Integer[]> removeDuplicates(ArrayList<Integer[]> allStore) {
		ArrayList<Integer[]> sorted = new ArrayList<>();
		
		for (int i = 0; i < allStore.size(); i++) {
			int foodCode = allStore.get(i)[0];
			int foodQty = allStore.get(i)[1];
			boolean check = false;
			for (int j = 0; j < sorted.size(); j++) {
				if (sorted.get(j)[0] == foodCode) {
					sorted.get(j)[1] += foodQty;
					check = true;
				}
			}
			
			if (!check) {
				Integer[] newFood = {foodCode,foodQty};
				sorted.add(newFood);
			}
		}
		
		return sorted;
	}

	public boolean validateLogin(String username, String password) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).usernameMatch(username)) {
				if (users.get(i).passwordMatch(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean authorizedPersonnel(String username) {
		boolean answer = false;
		for (int i = 0; i < users.size(); i++) {
			if (username.compareTo(users.get(i).getUsername()) == 0) {
				if (users.get(i).getAuthorized() == 1) {
					answer = true;
				}
			}
		}
		
		return answer;
	}
	
	public void orderCompleted(int tableID) {
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getTableID() == tableID) {
				orders.remove(i);
				i--;
			}
		}
		updateOrderFile();
		refreshOrderData();
		for (int i = 0; i < tablesReserved.size(); i++) {
			for (int j = 0; j < tablesReserved.get(i).getTablesReserved().size(); j++) {
				if (tableID == Integer.parseInt(tablesReserved.get(i).getTablesReserved().get(j))) {
					tablesReserved.get(i).getTablesReserved().remove(j);
				}
			}
		}
		updateTablesReserved();
		refreshTableData();
	}
	
	public boolean serversTable(int tableID) {
		boolean answer = false;
		String stringTable = tableID+"";
		String user = "";
		for (int i = 0; i < tablesReserved.size(); i++) {	
			for (int j = 0; j < tablesReserved.get(i).getTablesReserved().size(); j++) {
				if (tablesReserved.get(i).getTablesReserved().get(j).compareTo(stringTable) == 0) {
					user = tablesReserved.get(i).getUsername();
				}
			}
		}
		if (authorizedPersonnel(user)) {
			answer = true;
		}
		return answer;
	}
	
	public void addUser(String username, String password) {
		User user = new User(username, password, 0);
		users.add(user);
		updateUsers();
		refreshUserData();
	}
	
	public boolean existingUser(String username) {
		boolean answer = false;
		for (int i = 0; i < users.size(); i++) {
			if (username.compareTo(users.get(i).getUsername()) == 0) {
				answer = true;
			}
		}
		return answer;
	}
	
	public void deleteUser(String username) {
		for (int i = 0; i < users.size(); i++) {
			if (username.compareTo(users.get(i).getUsername()) == 0) {
				users.remove(i);
				updateUsers();
				refreshUserData();
			}
		}
		
		for (int i = 0; i < tablesReserved.size(); i++) {
			String admin = "admin";
			if (username.compareTo(tablesReserved.get(i).getUsername()) == 0) {
				ArrayList<String> tables = tablesReserved.get(i).getTablesReserved();
				
				for (int j = 0; j < tablesReserved.size(); j++) {
					if (admin.compareTo(tablesReserved.get(j).getUsername()) == 0) {
						tablesReserved.get(j).getTablesReserved().addAll(tables);
						break;
					}
				}
				updateTablesReserved();
				refreshTableData();
				tablesReserved.remove(i);
				updateTablesReserved();
				refreshTableData();
			}
		}
	}
	
	public void editPassword(String username, String password) {
		for (int i = 0; i < users.size(); i++) {
			if (username.compareTo(users.get(i).getUsername()) == 0) {
				users.get(i).setPassword(password);
				updateUsers();
				refreshUserData();
			}
		}
	}
}
