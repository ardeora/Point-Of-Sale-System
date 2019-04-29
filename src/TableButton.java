import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class TableButton extends JLabel implements MouseListener {

	private int tableID;
	private String state;
	
	public TableButton(int xPos, int yPos, int tableID) {
		super();
		this.state = "Green";
		this.tableID = tableID;
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBounds(xPos, yPos, 135, 135);
		addMouseListener(this);
		this.tableID = tableID;
		setColor();
	}
	
	public void changeColor(String color) {
		this.state = color;
		setColor();
	}
	
	public void setColor() {
		setIcon(new ImageIcon(getClass().getResource("/ImageAssets/" + this.state + ".png")));
	}

	public int getTableID() {
		return tableID;
	}

	public String getState() {
		return state;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
