import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EntreeView extends JPanel implements MouseListener {

	private MealCounter[] counters;
	private SidesView sidesView;
	
	public EntreeView(int x, int y) {
		setBounds(x,y,940,606);
		setLayout(null);
		addMouseListener(this);
		setBackground(Color.white);
		counters = new MealCounter[8];
		
		placeCounters();
		
		JLabel foodView = new JLabel();
		foodView.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/EntreeView.png")));
		foodView.setBounds(0, 0, 940, 606);
		this.add(foodView);
	}

	public void placeCounters() {
		
		MealCounter one = new MealCounter(39, 270,1);
		this.add(one);
		counters[0] = one;
		
		MealCounter two = new MealCounter(268, 270,2);
		this.add(two);
		counters[1] = two;
		
		MealCounter three = new MealCounter(500, 270,3);
		this.add(three);
		counters[2] = three;
		
		MealCounter four = new MealCounter(728, 270,4);
		this.add(four);
		counters[3] = four;
		
		MealCounter five = new MealCounter(39, 526,5);
		this.add(five);
		counters[4] = five;
		
		MealCounter six = new MealCounter(268, 526,6);
		this.add(six);
		counters[5] = six;
		
		MealCounter seven = new MealCounter(500, 526,7);
		this.add(seven);
		counters[6] = seven;
		
		MealCounter eight = new MealCounter(728, 526,8);
		this.add(eight);
		counters[7] = eight;
	
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
	
	
	public void setSidesView(SidesView sidesView) {
		this.sidesView = sidesView;
	}
	
	public void close() {
		this.setVisible(false);
	}

	public void open() {
		this.setVisible(true);
	}
	
	public void resetCounters() {
		for (int i = 0; i < counters.length; i++) {
			counters[i].reset();
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getX() > 232 && arg0.getX() <= 414 && arg0.getY() > 0 && arg0.getY() <= 45) {
			this.close();
			sidesView.open();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
