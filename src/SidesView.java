import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidesView extends JPanel implements MouseListener {

	private MealCounter[] counters;
	private EntreeView entreeView;
	
	public SidesView(int x, int y) {
		setBounds(x,y,941,606);
		setLayout(null);
		addMouseListener(this);
		setBackground(Color.white);
		counters = new MealCounter[8];
		
		placeCounters();
		
		JLabel foodView = new JLabel();
		foodView.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/SidesView.png")));
		foodView.setBounds(0, 0, 941, 606);
		this.add(foodView);
	}
	
	public void setEntreeView(EntreeView entreeView) {
		this.entreeView = entreeView;
	}

	public void placeCounters() {
		
		MealCounter one = new MealCounter(39, 270,9);
		this.add(one);
		counters[0] = one;
		
		MealCounter two = new MealCounter(268, 270,10);
		this.add(two);
		counters[1] = two;
		
		MealCounter three = new MealCounter(500, 270,11);
		this.add(three);
		counters[2] = three;
		
		MealCounter four = new MealCounter(728, 270,12);
		this.add(four);
		counters[3] = four;
		
		MealCounter five = new MealCounter(39, 526,13);
		this.add(five);
		counters[4] = five;
		
		MealCounter six = new MealCounter(268, 526,14);
		this.add(six);
		counters[5] = six;
		
		MealCounter seven = new MealCounter(500, 526,15);
		this.add(seven);
		counters[6] = seven;
		
		MealCounter eight = new MealCounter(728, 526,16);
		this.add(eight);
		counters[7] = eight;
	
	}
	
	public void close() {
		this.setVisible(false);
	}

	public void open() {
		this.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Integer[]> getOrderEntered(){
		ArrayList<Integer[]> foodList = new ArrayList<>();
		
		for (int i = 0; i < counters.length; i++) {
			if (counters[i].getCounter() != 0) {
				int foodCode = counters[i].getMealCode();
				int qty = counters[i].getCounter();
				Integer[] foodArray = new Integer[2];
				foodArray[0] = foodCode;
				foodArray[1] = qty;
				foodList.add(foodArray);
			}
		}
		
		return foodList;
	}
	
	public void resetCounters() {
		for (int i = 0; i < counters.length; i++) {
			counters[i].reset();
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getX() > 52 && arg0.getX() <= 232 && arg0.getY() > 0 && arg0.getY() <= 45) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getX() > 52 && arg0.getX() <= 232 && arg0.getY() > 0 && arg0.getY() <= 45) {
			this.close();
			entreeView.open();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
