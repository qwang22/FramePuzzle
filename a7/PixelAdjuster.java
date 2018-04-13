package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelAdjuster {
	public static void main(String[] args) throws IOException {

		//Picture p = A7Helper.readFromURL("https://s-media-cache-ak0.pinimg.com/736x/88/dd/b2/88ddb2605244e5f64dac363d1a647ada.jpg");//("http://www.cs.unc.edu/~kmp/kmp.jpg");
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		PixelAdjusterWidget picAdjust= new PixelAdjusterWidget(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Pixel Adjuster");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(picAdjust, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}

}