package a7;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class FramePuzzleWidget extends JPanel implements MouseListener, KeyListener {
	private int column=5;
	private int row=5;
	private Picture pic;
	private PictureView [][]pView;
	JPanel puzzle=new JPanel();
	int blankX=4;
	int blankY=4;
	
	public FramePuzzleWidget(Picture p){
		pic=p;
		puzzle.setLayout(new GridLayout(5,5));
		//puzzle.addMouseListener(this);
		puzzle.addKeyListener(this);
		puzzle.setFocusable(true);
		puzzle.requestFocus();

		pView=new PictureView[row][column];
		
		int pieWidth=p.getWidth()/5;
		int pieHeight=p.getHeight()/5;
		
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if((i==row-1)&(j==column-1)){
					PictureImpl newP=new PictureImpl(pieWidth, pieHeight);
					pView[j][i]=new PictureView(newP.createObservable());
					pView[j][i].addMouseListener(this);
					pView[j][i].setFocusable(false);
					puzzle.add(this.pView[j][i]);
				}
				else{
					Picture newP=p.extract(j*pieWidth, i*pieHeight, pieWidth, pieHeight);
					pView[j][i]= new PictureView(newP.createObservable()); 
					pView[j][i].addMouseListener(this);
					pView[j][i].setFocusable(false);
					puzzle.add(this.pView[j][i]);
					
				}
			}
		}
		add(puzzle);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println("KEYS");
		int keyCode=e.getKeyCode();
		switch(keyCode){
		case KeyEvent.VK_UP:
			if(blankY-1<0)
				return;
			Picture tempL= pView[blankX][blankY-1].getPicture();
			Picture blankL = new PictureImpl(pic.getWidth()/5,pic.getHeight()/5);
			pView[blankX][blankY].setPicture(tempL.createObservable());
			pView[blankX][blankY-1].setPicture(blankL.createObservable());
			blankY-=1;
			break;
		case KeyEvent.VK_DOWN:
			if(blankY+1>4)
				return;
			Picture tempR=pView[blankX][blankY+1].getPicture();
			Picture blankR=new PictureImpl(pic.getWidth()/5, pic.getHeight()/5);
			pView[blankX][blankY].setPicture(tempR.createObservable());
			pView[blankX][blankY+1].setPicture(blankR.createObservable());
			blankY+=1;
			break;
		case KeyEvent.VK_LEFT:
			if(blankX-1<0)
				return;
			Picture tempU= pView[blankX-1][blankY].getPicture();
			Picture blankU= new PictureImpl(pic.getWidth()/5, pic.getHeight()/5);
			pView[blankX][blankY].setPicture(tempU.createObservable());
			pView[blankX-1][blankY].setPicture(blankU.createObservable());
			blankX-=1;
			break;
		case KeyEvent.VK_RIGHT:
			if(blankX+1>4)
				return;
			Picture tempD=pView[blankX+1][blankY].getPicture();
			Picture blankD=new PictureImpl(pic.getWidth()/5,pic.getHeight()/5);
			pView[blankX][blankY].setPicture(tempD.createObservable());
			pView[blankX+1][blankY].setPicture(blankD.createObservable());
			blankX+=1;
			break;
			}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {		
		
		int mouseX = -1;
		int mouseY = -1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == pView[i][j]) {
					mouseX = i;
					mouseY = j;
				}
			}
			
		}
		
		for(int i=0;i<5;i++){
			for(int j=0; j<5;j++){
				if(mouseX==blankX){
					if (mouseY>blankY){ // mouse below blank
						int numMoves=mouseY-blankY;
						for(int k=0;k<numMoves;k++){
							Picture tempL= pView[blankX][blankY+1].getPicture();
							Picture blankL = new PictureImpl(pic.getWidth()/5,pic.getHeight()/5);
							pView[blankX][blankY].setPicture(tempL.createObservable());
							pView[blankX][blankY+1].setPicture(blankL.createObservable());
							blankY++;
						}
					}
					else if(mouseY<blankY){	// mouse above blank
						int numMoves=blankY-mouseY;
						for(int k=0;k<numMoves;k++){
							Picture tempR=pView[blankX][blankY-1].getPicture();
							Picture blankR=new PictureImpl(pic.getWidth()/5, pic.getHeight()/5);
							pView[blankX][blankY].setPicture(tempR.createObservable());
							pView[blankX][blankY-1].setPicture(blankR.createObservable());
							blankY--;
						}
					}
				}
				if(mouseY==blankY){
					if(mouseX>blankX){	// mouse left of blank
						int numMoves=mouseX-blankX;
						for(int k=0;k<numMoves;k++){
							Picture tempD=pView[blankX+1][blankY].getPicture();
							Picture blankD=new PictureImpl(pic.getWidth()/5,pic.getHeight()/5);
							pView[blankX][blankY].setPicture(tempD.createObservable());
							pView[blankX+1][blankY].setPicture(blankD.createObservable());
							blankX++;
						}
					}
					else if(mouseX<blankX){	// mouse right of blank
						int numMoves=blankX-mouseX;
						for(int k=0;k<numMoves;k++){
							Picture tempU= pView[blankX-1][blankY].getPicture();
							Picture blankU= new PictureImpl(pic.getWidth()/5, pic.getHeight()/5);
							pView[blankX][blankY].setPicture(tempU.createObservable());
							pView[blankX-1][blankY].setPicture(blankU.createObservable());
							blankX--;
						}
					}
				}
			}
		}
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