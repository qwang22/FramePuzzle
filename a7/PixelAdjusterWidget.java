package a7;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class PixelAdjusterWidget extends JPanel implements ChangeListener{
	
	private JLabel blur, saturation, brightness;
	private JSlider blurSlide, satSlide,brightSlide;
	JPanel dongxi=new JPanel();
	private PictureView picture_view;
	private Picture pic;
	private Pixel pix;
	
	public PixelAdjusterWidget(Picture p){
		pic=p;
		blur=new JLabel("Blur:");
		saturation= new JLabel("Saturation:");
		brightness= new JLabel("Brightness:");
		
		blurSlide= new JSlider (0,5);
		blurSlide.setValue(0);
		blurSlide.setPaintTicks(true);
		blurSlide.setMajorTickSpacing(1);
		blurSlide.setPaintLabels(true);
		blurSlide.addChangeListener(this);
		
		satSlide= new JSlider(-100,100);
		satSlide.setPaintTicks(true);
		satSlide.setPaintLabels(true);
		satSlide.setMajorTickSpacing(25);
		satSlide.addChangeListener(this);
		
		brightSlide= new JSlider(-100,100);
		brightSlide.setPaintTicks(true);
		brightSlide.setPaintLabels(true);
		brightSlide.setMajorTickSpacing(25);
		brightSlide.addChangeListener(this);
		
		setLayout(new BorderLayout());
		
		picture_view=new PictureView(p.createObservable());
		//picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);
		
		JPanel label = new JPanel();
		label.setLayout(new GridLayout (3,2));
				
		label.add(blur);
		label.add(blurSlide);
		label.add(saturation);
		label.add(satSlide);
		label.add(brightness);
		label.add(brightSlide);
				
		add(label, BorderLayout.SOUTH);	
		
	}
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
	//public blur(int blurness, Picture pic, int_blurX, int blurY){
	//	
	//}
	//changed from pixel to picture as return type
	public Pixel blurPix(Picture p, int blurness){
		//return null;
		int bottom_x = 0;
		int bottom_y=0;
		int top_x = 0;
		int top_y=0;
		double totRed=0;
		double avgRed=0;
		double totGre=0;
		double avgGre=0;
		double totBlu=0;
		double avgBlu=0;
		int count=0;
		
		//Picture copy= new PictureImpl(p.getWidth(), p.getHeight());
		for (int i=0;i<p.getWidth();i++){  //picture of 5 by 5
			for(int j=0;j<p.getHeight();j++){

				top_x = Math.max(i-blurness,0);
				top_y = Math.max(j-blurness,0);
				bottom_x=Math.min(i+blurness,p.getWidth()-1);
				bottom_y=Math.min(j+blurness,p.getHeight()-1);

				for (int k=top_x;k<=bottom_x;k++){
					for(int l=top_y;l<=bottom_y;l++){
						//if(i ==0&&j==0)
						//System.out.println(k + " " + l );
						totRed+=p.getPixel(k, l).getRed();
						totGre+=p.getPixel(k, l).getGreen();
						totBlu+=p.getPixel(k, l).getBlue();
						count++;
						//System.out.println(count);
					}
				}

				avgRed=totRed/(count);
				avgGre=totGre/(count);
				avgBlu=totBlu/(count);
				Pixel blurPix=new ColorPixel(avgRed, avgGre, avgBlu);
				//System.out.println(i+"   "+j);
				p.setPixel(i, j, blurPix);
				totRed=0;
				totGre=0;
				totBlu=0;
				count=0;
			}
		}
		return null;
	}

	public Pixel satiPix(Picture p, double satiness){
		double satiRed=0;
		double satiGre=0;
		double satiBlu=0;
		if(satiness>=-100&&satiness<=0){/////////////////maybe write another else if for 0
			for(int i=0;i<p.getWidth();i++){
				for(int j=0;j<p.getHeight();j++){
					satiRed=p.getPixel(i, j).getRed()*(1.0+(satiness/100.0))-
							(p.getPixel(i,j).getIntensity()*satiness/100.0);
					satiGre=p.getPixel(i, j).getGreen()*(1.0+(satiness/100.0))-
							(p.getPixel(i,j).getIntensity()*satiness/100.0);
					satiBlu=p.getPixel(i, j).getBlue()*(1.0+(satiness/100.0))-
							(p.getPixel(i, j).getIntensity()*satiness/100.0);
					Pixel satiPix=new ColorPixel(satiRed, satiGre, satiBlu);
					p.setPixel(i, j, satiPix);
				
				}
				
			}

		}
		else if (satiness<=100&&satiness>0){
			for(int i=0;i<p.getWidth();i++){
				for(int j=0;j<p.getHeight();j++){
					double b=Math.max(p.getPixel(i, j).getRed(),p.getPixel(i, j).getGreen());
					double c=Math.max(p.getPixel(i, j).getGreen(), p.getPixel(i, j).getBlue());
					double a;
					if (b==c)
						a=b;
					else
						a=Math.max(b, c);
					if(a!=0){
					satiRed=p.getPixel(i, j).getRed()*((a+(1.0-a)*
							(p.getPixel(i, j).getIntensity()/100.0)/a));
					satiGre=p.getPixel(i, j).getGreen()*((a+(1.0-a)*
							(p.getPixel(i, j).getIntensity()/100.0)/a));
					satiBlu=p.getPixel(i, j).getBlue()*((a+(1.0-a)*
							(p.getPixel(i, j).getIntensity()/100.0)/a));
					}
					else{
						satiRed=0;
						satiGre=0;
						satiBlu=0;
					}
					Pixel satiPix=new ColorPixel(satiRed, satiGre, satiBlu);
					p.setPixel(i, j, satiPix);
					//new = old * ((a + ((1.0 - a) * (f / 100.0))) / a)
					
				}
				
			}
			Pixel satiPix=new ColorPixel(satiRed, satiGre, satiBlu);
			return satiPix;
			
		}
		return null;
	}
	public Pixel brighten(Picture p, double brightness){
		double briRed=0;
		double briGre=0;
		double briBlu=0;
		Pixel blendPix= null;
		for(int i=0;i<p.getWidth();i++){
			for(int j=0;j<p.getHeight();j++){
				Pixel pix=p.getPixel(i, j);
				//Pixel blendPix= null;
				ColorPixel white=new ColorPixel(1.0,1.0,1.0);
				ColorPixel black=new ColorPixel(0,0,0);
				
				if (brightness>0){
					blendPix=pix.blend(white,1-(brightness/100.0));
				}
				else{
					blendPix=pix.blend(black,(1-Math.abs(brightness/100.0)));
				}
				p.setPixel(i, j, blendPix);
				
			}
		}
		return blendPix;
		//return null;
	}
	

	@Override
	public void stateChanged(ChangeEvent e) {
		Picture copy= new PictureImpl(pic.getWidth(), pic.getHeight());

		int width=pic.getWidth();
		int height=pic.getHeight();
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				double coRed,coGre,coBlu;
				coRed=pic.getPixel(i, j).getRed();
				coGre=pic.getPixel(i, j).getGreen();
				coBlu=pic.getPixel(i, j).getBlue();
				ColorPixel newPix=new ColorPixel(coRed,coGre,coBlu);
				copy.setPixel(i, j, newPix);
				//Pixel p=pic.getPixel(i, j);
				//copy.setPixel(i, j, p);
			}
		}
		blurPix(copy,blurSlide.getValue());
		satiPix(copy,satSlide.getValue());
		brighten(copy,brightSlide.getValue());

		picture_view.setPicture(copy.createObservable());
	} 

}