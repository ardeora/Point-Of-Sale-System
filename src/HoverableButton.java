import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HoverableButton extends JLabel implements MouseListener {

	private String hoverPath;
	private String notHoverPath;
	
	public HoverableButton(String notHoverPath, String hoverPath) {
		super();
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.notHoverPath = notHoverPath;
		this.hoverPath = hoverPath;
		addMouseListener(this);
		setIcon(new ImageIcon(getClass().getResource(this.notHoverPath)));
	}
		
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setIcon(new ImageIcon(getClass().getResource(this.hoverPath)));
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		setIcon(new ImageIcon(getClass().getResource(this.notHoverPath)));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}



