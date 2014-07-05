import java.awt.Color;
import java.awt.event.KeyEvent;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.util.RandomGenerator;
public class TheMaze extends GraphicsProgram {
	public static final int APPLICATION_WIDTH = 600;
	
	public static final int APPLICATION_HEIGHT = 600;
	
	public static final int BLOCKS_PER_ROW = 10;
	
	public static final int NUMBER_OF_ROWS = 10;
	
	public static final double BLOCK_WIDTH = APPLICATION_WIDTH / BLOCKS_PER_ROW;
	
	public static final double BLOCK_HEIGHT = APPLICATION_HEIGHT / NUMBER_OF_ROWS;
	
	private int lastKeyEvent;
	 
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	
	private Cube lostCube;
	
	private double xLocation = 0 ;
	
	private double yLocation = 0;
	private double type;
	
	/*when i learn how to do 2D arrays i can replace*/
	/*Just testing git */
	String Path = "9,9 : 9,8 : 9,7 : 8,7 : 7,7 : 6,7 : 5,7 : 4,7 : 4,6 : 4,5 : 4,4 : 5,4 : 6,4 : 7,4 : 7,3 : 7,2 : 6,2 : 5,2 : 5,1 : 5,0 ";
	String Path1 = "9,9 : 9,8 : 9,7 : 8,7 : 7,7 : 6,7 : 6,8  : 5,8 : 4,8 : 4,7 : 4,6 : 4,5 : 5,5 : 6,5 : 7,5 : 7,4 : 7,3 : 7,2 : 6,2 : 5,2 : 4,2 : 3,2 : 3,3 : 2,3 : 1,3 : 1,2 : 1,1 : 1,0";
	String CurrPath;
	private double PathCh = Math.random();
	public static void main(String[]args){
		
		new TheMaze().start();
	}
	
	
	public void run() {
		setup();
		addKeyListeners();
		addMouseListeners();
		GLabel label = new GLabel("Try to guess the path to the top.", 25,255);
		GLabel label1 = new GLabel("A wrong Path will take you back to start",25,280);
		GLabel label2 = new GLabel ("Use the arrow keys to navigate the black box",25,335);
		add(label);
		add(label1);
		add(label2);
		
		if (rgen.nextInt(2,3) == 2){
			CurrPath = Path1;
		}
		else{
			CurrPath = Path;
		}
	}
	
	public void setup() {
		blocks();
		lostBox();
	}
	public void blocks(){
		for(int i = 0; i < BLOCKS_PER_ROW ; i ++) {
			
			for(int j = 0; j < NUMBER_OF_ROWS; j ++) {
				GRect boxes = new GRect(i * BLOCK_WIDTH,j * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
				int b = rgen.nextInt(100,200);
				int c = rgen.nextInt(100,255);
				int d = rgen.nextInt(100,150);
				boxes.setFilled (true);
				boxes.setFillColor(new Color(b,c,d));
				add(boxes);
			}
		}	
	}
	public void lostBox() {
		lostCube = new Cube(APPLICATION_HEIGHT  - (BLOCK_HEIGHT - 2),APPLICATION_WIDTH - (BLOCK_WIDTH - 2),
				BLOCK_HEIGHT - 4,BLOCK_WIDTH - 4);
		
		lostCube.setFilled(true);
		lostCube.setFillColor(new Color(0,0,0));
		add(lostCube);
		xLocation = APPLICATION_HEIGHT  - (BLOCK_HEIGHT - 2);
		yLocation = APPLICATION_WIDTH - (BLOCK_WIDTH - 2);
	}
	
	public String getCurrLoc(){
		
		return ((int)lostCube.getX())  / 60 + "," + ((int)lostCube.getY() / 60);
	}
	
	
	
	public void keyPressed(KeyEvent ke){
		if (ke.getKeyCode() == KeyEvent.VK_UP){
			
				Cube.setY(yLocation - BLOCK_WIDTH);
				yLocation = yLocation - BLOCK_WIDTH;
				

			
			lostCube.setLocation(xLocation,yLocation);
			lastKeyEvent = KeyEvent.VK_UP;
			if(CurrPath.indexOf(getCurrLoc())>0){
				GRect RPath = new GRect(xLocation ,yLocation,BLOCK_WIDTH - 4,BLOCK_HEIGHT - 4);
				RPath.setFilled(true);
				RPath.setFillColor(Color.WHITE);
				add(RPath);
				lostCube.sendToFront();
			}
		    else{
		    	GLabel tryAgain = new GLabel("WRONG PATH. TRY AGAIN",25,255);
		    	tryAgain.setColor(Color.BLACK);
		    	add(tryAgain);
		    	tryAgain.sendToFront();
		    	remove(tryAgain);
		    	xLocation = APPLICATION_HEIGHT  - (BLOCK_HEIGHT - 2);
		    	yLocation = APPLICATION_WIDTH - (BLOCK_WIDTH - 2);
		    	lostCube.setLocation(xLocation,yLocation);
		    }
			
			
		}
		if (ke.getKeyCode() == KeyEvent.VK_DOWN){
			
				Cube.setY(yLocation + BLOCK_HEIGHT);
				yLocation = yLocation + BLOCK_HEIGHT;
		
			lostCube.setLocation(xLocation,yLocation);
			
		    
		    lastKeyEvent = KeyEvent.VK_DOWN;
		    if(CurrPath.indexOf(getCurrLoc())>0){
				GRect RPath = new GRect(xLocation ,yLocation,BLOCK_WIDTH - 4,BLOCK_HEIGHT - 4);
				RPath.setFilled(true);
				RPath.setFillColor(Color.WHITE);
				add(RPath);
				lostCube.sendToFront();
			}
		    else{
		    	GLabel tryAgain = new GLabel("WRONG PATH. TRY AGAIN",25,255);
		    	tryAgain.setColor(Color.BLACK);
		    	add(tryAgain);
		    	tryAgain.sendToFront();
		    	remove(tryAgain);
		    	xLocation = APPLICATION_HEIGHT  - (BLOCK_HEIGHT - 2);
		    	yLocation = APPLICATION_WIDTH - (BLOCK_WIDTH - 2);
		    	lostCube.setLocation(xLocation,yLocation);
		    }
			
		}
		
		
		
		/*LEFT KEY DETECTOR*/
		
		
		if (ke.getKeyCode() == KeyEvent.VK_LEFT){
			
				Cube.setX(xLocation - BLOCK_WIDTH);
				
				xLocation = xLocation - BLOCK_HEIGHT;
				lostCube.setLocation(xLocation,yLocation);

				
				
			
			lastKeyEvent = KeyEvent.VK_LEFT;
			if(CurrPath.indexOf(getCurrLoc())>0){
				GRect RPath = new GRect(xLocation ,yLocation,BLOCK_WIDTH - 4,BLOCK_HEIGHT - 4);
				RPath.setFilled(true);
				RPath.setFillColor(Color.WHITE);
				add(RPath);
				lostCube.sendToFront();
			}
		    else{
		    	GLabel tryAgain = new GLabel("WRONG PATH. TRY AGAIN",25,255);
		    	tryAgain.setColor(Color.BLACK);
		    	add(tryAgain);
		    	tryAgain.sendToFront();
		    	remove(tryAgain);
		    	xLocation = APPLICATION_HEIGHT  - (BLOCK_HEIGHT - 2);
		    	yLocation = APPLICATION_WIDTH - (BLOCK_WIDTH - 2);
		    	lostCube.setLocation(xLocation,yLocation);
		    }		
		}
		
		
		/*RIGHT KEY DETECTOR*/
		
		if (ke.getKeyCode() == KeyEvent.VK_RIGHT){
				if (xLocation < APPLICATION_HEIGHT - BLOCK_WIDTH){
				Cube.setX(xLocation + BLOCK_WIDTH);
				
				xLocation = xLocation + BLOCK_HEIGHT;
				lostCube.setLocation(xLocation,yLocation);
			
				
				lastKeyEvent = KeyEvent.VK_RIGHT;
				if(CurrPath.indexOf(getCurrLoc())>0){
					GRect RPath = new GRect(xLocation ,yLocation,BLOCK_WIDTH - 4,BLOCK_HEIGHT - 4);
					RPath.setFilled(true);
					RPath.setFillColor(Color.WHITE);
					add(RPath);
					lostCube.sendToFront();
				}
			    else{
			    	GLabel tryAgain = new GLabel("WRONG PATH. TRY AGAIN",25,255);
			    	tryAgain.setColor(Color.BLACK);
			    	add(tryAgain);
			    	tryAgain.sendToFront();
			    	remove(tryAgain);
			    	xLocation = APPLICATION_HEIGHT  - (BLOCK_HEIGHT - 2);
			    	yLocation = APPLICATION_WIDTH - (BLOCK_WIDTH - 2);
			    	lostCube.setLocation(xLocation,yLocation);
			    }
				}
						
				
		
		
		}
		
	}
		
	
	
	

}
