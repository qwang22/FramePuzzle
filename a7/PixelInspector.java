package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class PixelInspector {
	
	public static void main(String[] args) throws IOException {
		
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		
		JLabel x = new JLabel("X: ");
		JLabel y = new JLabel("Y: ");
		JLabel red = new JLabel("Red: ");
		JLabel green = new JLabel("Green: ");
		JLabel blue = new JLabel("Blue: ");
		JLabel brightness = new JLabel("Brightness: ");
		
		PixelInspectorWidget pixelInspector = new PixelInspectorWidget(p, x, y,
				red, green, blue, brightness);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Pixel Inspector");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new BorderLayout());
		main_panel.add(pixelInspector, BorderLayout.CENTER);
		main_frame.setContentPane(main_panel);
		
		JPanel label_panel = new JPanel();
		label_panel.setLayout(new GridLayout(6,1));
		label_panel.setOpaque(true);
		
		main_panel.add(label_panel, BorderLayout.WEST);
		
		label_panel.add(x);
		label_panel.add(y);
		label_panel.add(red);
		label_panel.add(green);
		label_panel.add(blue);
		label_panel.add(brightness); 
		
		main_frame.pack();
		main_frame.setVisible(true);
	}
	
}
