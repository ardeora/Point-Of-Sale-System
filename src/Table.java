import java.util.ArrayList;

public class Table {

	private String username;
	private ArrayList<String> tablesReserved;
	
	public Table(String username, ArrayList<String> tablesReserved) {
		this.username = username;
		this.tablesReserved = tablesReserved;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ArrayList<String> getTablesReserved() {
		return tablesReserved;
	}

	public void setTablesReserved(ArrayList<String> tablesReserved) {
		this.tablesReserved = tablesReserved;
	}
	
	
}
