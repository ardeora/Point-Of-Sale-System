import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MealCounter extends JPanel implements MouseListener {

	private int xPos;
	private int yPos;
	private int mealCode;
	private int counter;

	
	public MealCounter(int x,int y,int mealCode) {
		xPos = x;
		yPos = y;
		this.mealCode = mealCode;
		setLayout(null);
		setBounds(x,y,174,32);
		addMouseListener(this);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(Color.white);
		JLabel image = new JLabel();
		image.setIcon(new ImageIcon(getClass().getResource("/ImageAssets/Counter.png")));
		image.setBounds(0, 0, 174, 32);
		this.add(image);
		
	}
	
	public int getMealCode() {
		return mealCode;
	}
	
	public int getCounter() {
		return counter;
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
		arg0.setColor(new Color(50, 50, 62));
		arg0.setFont(new Font("Helvetica Neue", Font.BOLD, 34));
		arg0.drawString(""+counter, 79, 28);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		if (mouseInsideMinus(arg0.getPoint())) {
			if (counter > 0) {
				counter--;
			}
			repaint();
		}
		if (mouseInsidePlus(arg0.getPoint())) {
			if (counter < 2) {
				counter++;
				
			}
			repaint();
		}
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	private boolean mouseInsideMinus(Point click) {
		boolean answer = false;
		if (click.getX() <= 44) {
			answer = true;
		}
		
		return answer;
	}
	
	private boolean mouseInsidePlus(Point click) {
		boolean answer = false;
		if (click.getX() >= 130) {
			answer = true;
		}
		
		return answer;
	}
	
	public void reset() {
		counter = 0;
		repaint();
	}
}
