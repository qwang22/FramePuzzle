package a7;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FramePuzzle {
	
	public static void main(String[] args) throws IOException {

		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		//SimplePictureViewWidget simple_widget = new SimplePictureViewWidget(p, "K to the M to the P");
		//PixelInspectWidget pixWid = new PixelInspectWidget(p);
		FramePuzzleWidget puzzPic= new FramePuzzleWidget(p);
		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Puzzle");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(puzzPic, BorderLayout.CENTER);
		//top_panel.setPreferredSize(new Dimension(600, 600));
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
	}


}