package a7;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterWidget extends JPanel implements ChangeListener {
	
	private PictureView picture_view;
	private JSlider blur, saturation, brightness;
	private Picture p;
	
	public ImageAdjusterWidget(Picture picture, JSlider blur, 
			JSlider saturation, JSlider brightness) {
		
		this.p = picture;
		this.blur = blur;
		this.saturation = saturation;
		this.brightness = brightness;
		
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		add(picture_view, BorderLayout.CENTER);
		
		blur.addChangeListener(this);
		saturation.addChangeListener(this);
		brightness.addChangeListener(this);
		
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}

}
