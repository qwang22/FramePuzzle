package a7;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorWidget extends JPanel implements MouseListener {

	private PictureView picture_view;
	private JLabel x, y, red, green, blue, brightness;
	private Picture p;
	
	
	public PixelInspectorWidget(Picture picture, JLabel x, JLabel y,
			JLabel red, JLabel green, JLabel blue, JLabel brightness) {
		
		this.p = picture;
		this.x = x;
		this.y = y;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.brightness = brightness;
	
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		x.setText("X: " + e.getX());
		y.setText("Y: " + e.getY());
		red.setText("Red: " + Math.round(
				p.getPixel(e.getX(), e.getY()).getRed() * 100.0)/100.0);
		green.setText("Green: " + Math.round(
				p.getPixel(e.getX(), e.getY()).getGreen() * 100.0)/100.0);
		blue.setText("Blue: " + Math.round(
				p.getPixel(e.getX(), e.getY()).getBlue() * 100.0)/100.0);
		brightness.setText("Brightness: " + Math.round(
				p.getPixel(e.getX(),e.getY()).getIntensity() * 100.0)/100.0);

	}
	

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
