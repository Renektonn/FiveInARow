package cc.openhome;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
public class Board extends JPanel implements MouseListener{

	private int [][] chess = new int[19][19];
	private int turn=1;//black chess first
	private boolean isGame=true;

	public Board(){

		initBoard();
	}

	private void initBoard(){

		initGame();
		addMouseListener(this);
	}

	private void initGame(){

		setPreferredSize(new Dimension(1600, 900));       
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		doDrawing(g);
	}

	private void doDrawing(Graphics g) {
		
		for(int i=1 ; i<=19 ; i++){
			g.drawLine(40,40*i,40*19,40*i);	
			g.drawLine(40*i,40,40*i,40*19);	


		}
		
		Graphics2D g2d = (Graphics2D)g;
		for(int i=0 ; i<3 ; i++){
			for(int j=0 ; j<3 ; j++){
				drawCircle(g2d,160+240*i,160+240*j,10);
			}
		}
		
		
		for(int i=0 ; i<19 ; i++){
			for(int j=0 ; j<19 ; j++){
				if(chess[i][j]==1){
					g2d.setColor(Color.black);
					drawCircle(g2d,40+40*j,40+40*i,35);
				}
				else
					if(chess[i][j]==-1){
						g2d.setColor(Color.white);
						drawCircle(g2d,40+40*j,40+40*i,35);
					}
			}
		}
						
	}

	private void drawCircle(Graphics2D g2d , double x , double y , double diameter){

		Ellipse2D.Double circle = new Ellipse2D.Double(x-diameter/2,y-diameter/2,diameter,diameter);
		//construct a ellipse will create from up-left corner, so it's postition must minut diamter/2
		g2d.fill(circle);
	}
	
	@Override
	public void mouseClicked(MouseEvent e){

		Point postition = e.getLocationOnScreen();
		getAbsolutePosition(postition.x,postition.y);
	}

	@Override
	public void mousePressed(MouseEvent e){}

	@Override
	public void mouseReleased(MouseEvent e){}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	private void getAbsolutePosition(int x , int y){

		x=(x-20)/40;
		y=(y-20)/40;
		addChess(x,y);
	}

	private void addChess(int x , int y){

		if(!isGame){}
		else	
			if(!inField(x,y)){}
			else
				if(chess[y][x]!=0){}
				else{
					chess[y][x]=turn;	
					repaint();
					judgeGame(x,y);
					turn*=-1;
				}

	}

	private void judgeGame(int x , int y){

		while(sameColor(x-1 , y-1)){
			x-=1;
			y-=1;
		}			
		for(int i=0 ; i<5 ; i++){
			if(!sameColor(x+i , y+i)){
				break;
			}
			if(i==4){
				isGame=false;
				return;
			}
		}
	
		while(sameColor(x+1 , y+1)){
			x+=1;
			y+=1;
		}			
		for(int i=0 ; i<5 ; i++){
			if(!sameColor(x-i , y-i)){
				break;
			}
			if(i==4){
				isGame=false;
				return;
			}
		}

		while(sameColor(x-1 , y)){
			x-=1;
		}			
		for(int i=0 ; i<5 ; i++){
			if(!sameColor(x+i , y)){
				break;
			}
			if(i==4){
				isGame=false;
				return;
			}
		}

		while(sameColor(x , y-1)){
			y-=1;
		}			
		for(int i=0 ; i<5 ; i++){
			if(!sameColor(x , y+i)){
				break;
			}
			if(i==4){
				isGame=false;
				return;
			}
		}
	

	}

	private boolean sameColor(int x , int y){

		if(!inField(x,y)){}
		else
			if(chess[y][x]!=turn){}
			else{
				return true;
			}	
		return false;
	}

	private boolean inField(int x , int y){

		if(x<0 || y<0){}
		else
			if(x>18 || y>18){}
			else{
				return true;
			}
		return false;
	}

}
