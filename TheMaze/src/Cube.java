
import acm.graphics.GRect;

public class Cube extends GRect{
	private static double gx;
	private static double gy;
	private String yL = "0";
	private String xL = "0";
	
	
	public Cube(double x, double y, double width, double height) {
		super(x,y,width,height);
		gx = x;
		gy = y;
	
	}
	
	public double getx() {
		return gx;
	}
	public double gety() {
		return gy;
	}
	public static void setX(double newX) {
		gx = newX;
	}
	public static void setY(double newY) {
		gy = newY;
	}
			
}
