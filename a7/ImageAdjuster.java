package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjuster {
	public static void main(String[] args) throws IOException {
		
		Picture p = A7Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");
		
		JSlider blur = new JSlider(0, 5, 2);
		JSlider saturation = new JSlider(-100, 100, 0);
		JSlider brightness = new JSlider(-100, 100, 0);
		
		ImageAdjusterWidget imageAdjuster = new ImageAdjusterWidget(p, blur,
				saturation, brightness);

		
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 7 Image Adjuster");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel main_panel = new JPanel();
		main_panel.setLayout(new GridLayout(1, 2));
		main_panel.add(imageAdjuster, BorderLayout.CENTER);
		main_frame.setContentPane(main_panel);
		
		JLabel slider_label = new JLabel();
		slider_label.setLayout(new GridLayout(3, 1));
		slider_label.setOpaque(true);
		
		main_panel.add(slider_label, BorderLayout.EAST);
		
		JLabel blur_label = new JLabel("Blur: ");
		JLabel saturation_label = new JLabel("Saturation: ");
		JLabel brightness_label = new JLabel("Brightness: ");
		
		slider_label.add(blur_label);
		slider_label.add(saturation_label);
		slider_label.add(brightness_label);
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(3, 1));
		slider_panel.setOpaque(true);
		
		main_panel.add(slider_panel, BorderLayout.EAST);
		
		slider_panel.add(blur);
		slider_panel.add(saturation);
		slider_panel.add(brightness);
		
		main_frame.pack();
		main_frame.setVisible(true);
		
	}

}

class SliderWatcher implements ChangeListener {

	@Override
	public void stateChanged(ChangeEvent e) {
		ImageAdjuster imageAdjuster = (ImageAdjuster)e.getSource();
		
		
	}
	
}
