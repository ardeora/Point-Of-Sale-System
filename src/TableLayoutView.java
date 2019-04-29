
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;


public class TableLayoutView extends JPanel {

	private Database database;
	private TableButton[] tableArray;
	private String username;
	
	public TableLayoutView(Database database) {
		super();
		this.database = database;
		this.username = "";
		setLayout(null);
		setBounds(290, 0, 990, 742);
		setBackground(new Color(49, 50, 62));
		setVisible(false);
		this.tableArray = new TableButton[16];
		
		fillDefaultTables();	
	}

	private void fillDefaultTables() {
		TableButton one = new TableButton(84, 24, 1) {
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(one);
		tableArray[0] = one;
		
		TableButton two = new TableButton(274, 24, 2){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};;
		this.add(two);
		tableArray[1] = two;
		
		TableButton three = new TableButton(589, 24, 3){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};;
		this.add(three);
		tableArray[2] = three;
		
		TableButton four = new TableButton(779, 24, 4){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};;
		this.add(four);
		tableArray[3] = four;
		
		TableButton five = new TableButton(84, 172, 5){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(five);
		tableArray[4] = five;
		
		TableButton six = new TableButton(274, 172, 6){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(six);
		tableArray[5] = six;
		
		TableButton seven = new TableButton(589, 172, 7){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(seven);
		tableArray[6] = seven;
		
		TableButton eight = new TableButton(779, 172, 8){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(eight);
		tableArray[7] = eight;
		
		TableButton nine = new TableButton(84, 396, 9){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(nine);
		tableArray[8] = nine;
		
		TableButton ten = new TableButton(274, 396, 10){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(ten);
		tableArray[9] = ten;
		
		TableButton eleven = new TableButton(589, 396, 11){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(eleven);
		tableArray[10] = eleven;
		
		TableButton twelve = new TableButton(779, 396, 12){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(twelve);
		tableArray[11] = twelve;
		
		TableButton thirteen = new TableButton(84, 547, 13){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(thirteen);
		tableArray[12] = thirteen;
		
		TableButton fourteen = new TableButton(274, 547, 14){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(fourteen);
		tableArray[13] = fourteen;
		
		TableButton fifteen = new TableButton(589, 547, 15){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(fifteen);
		tableArray[14] = fifteen;
		
		TableButton sixteen = new TableButton(779, 547, 16){
			@Override
			public void mousePressed(MouseEvent arg0) {
				TableLayoutView.this.buttonPressedData(getState(), getTableID());
			}
		};
		this.add(sixteen);
		tableArray[15] = sixteen;	
	}
	
	private void buttonPressedData(String state, int tableID) {
		if (state.compareTo("Yellow") == 0) {
			
		} else {
			close();
			openMenuView(username,tableID);
		}
	}
	
	public void close() {
		setVisible(false);
	}
	
	public void openMenuView(String username, int TableID) {
		//will be overridden in ServerPage
	}
	
	
	public void displayTableLayoutView(String username){
		database.updateTablesReserved();
		database.refreshTableData();
		this.username = username;
		
		if (database.authorizedPersonnel(username)) {
			displayManagerLayoutView();
		} else {
			this.setVisible(true);
			
			ArrayList<String> allTables = new ArrayList<>();

			for (int i = 0; i < 16; i++) {
				allTables.add((i+1) + "");
			}

			ArrayList<String> otherReservedTables = new ArrayList<>();
			ArrayList<String> reservedTables = new ArrayList<>();
			for (int i = 0; i < database.getTablesReserved().size(); i++) {
				if (username.compareTo(database.getTablesReserved().get(i).getUsername()) == 0) {
					reservedTables = database.getTablesReserved().get(i).getTablesReserved();	
					for (int j = 0; j < reservedTables.size(); j++) {
						tableArray[Integer.parseInt(reservedTables.get(j)) - 1].changeColor("Red");
					}
				} else {
					otherReservedTables.addAll(database.getTablesReserved().get(i).getTablesReserved());
					for (int j = 0; j < otherReservedTables.size(); j++) {
						tableArray[Integer.parseInt(otherReservedTables.get(j)) - 1].changeColor("Yellow");
					}
				} 
			}
			ArrayList<String> allReservedTables = new ArrayList<>();
			allReservedTables.addAll(reservedTables);
			allReservedTables.addAll(otherReservedTables);

			for (int i = 0; i < allReservedTables.size(); i++) {
				String store = allReservedTables.get(i);
				for (int j = 0; j < allTables.size(); j++) {
					if (store.compareTo(allTables.get(j)) == 0) {
						allTables.remove(j);
					}
				}

			}

			for (int i = 0; i < allTables.size(); i++) {
				tableArray[Integer.parseInt(allTables.get(i)) - 1].changeColor("Green");
			}
		}
	}
	
	public void displayManagerLayoutView() {
		ArrayList<String> bookedTables = new ArrayList<>();
		ArrayList<String> emptyTables = new ArrayList<>();
		
		for (int i = 0; i < database.getTablesReserved().size(); i++) {
			Table user = database.getTablesReserved().get(i);
			for (int j = 0; j < user.getTablesReserved().size(); j++) {
				bookedTables.add(user.getTablesReserved().get(j));
			}
		}
		
		for (int i = 0; i < 16; i++) {
			emptyTables.add((i+1)+"");
		}
		
		for (int i = 0; i < bookedTables.size(); i++) {
			String store = bookedTables.get(i);
			for (int j = 0; j < emptyTables.size(); j++) {
				if (store.compareTo(emptyTables.get(j)) == 0) {
					emptyTables.remove(j);
				}
			}
		}
		
		for (int i = 0; i < emptyTables.size(); i++) {
			tableArray[Integer.parseInt(emptyTables.get(i)) - 1].changeColor("Green");
		}
		
		for (int i = 0; i < bookedTables.size(); i++) {
			tableArray[Integer.parseInt(bookedTables.get(i)) - 1].changeColor("Red");
		}
		this.setVisible(true);
	}
}
