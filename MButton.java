package Graphics;

import java.awt.Rectangle;

public class MButton {
	private char name;
	private Rectangle dim;
	
	public MButton(char n) {
		name = n;
		dim = setDim();
	}
	
	private Rectangle setDim(){
		switch (name) {
			case 'E':
				return new Rectangle(0, 0, 100, 50);
			default:
				return new Rectangle(0,0,0,0);
		}
	}
	
	public String toString() {
		switch (name) {
			case 'E':
				return "Exit";
			default:
				return "Error";
		}
	}
	
	public char getName() {return name;}
	
	public int getHeight() {return dim.height;}
	
	public int getWidth() {return dim.width;}
	
	public int getX() {return dim.x;}
	
	public int getY() {return dim.y;}
	
	public Rectangle getDim() {return new Rectangle(dim);}
	
	public void setX(int i) {dim.x = i;}
	
	public void setY(int i) {dim.y = i;}
	
	public static void getPurpose(MButton b) {
		char n = b.getName();
		switch (n) {
			case 'E':
				System.exit(0);
			default:
				System.out.println("Error");
		}
	}
}
