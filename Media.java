package Main;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import Graphics.Screen;

public class Media {
	private Image image;		//Find a better way?
	private String name;		//Not Used yet
	private String type;
	
	public Media(String n, String t) {
		if(t.equals("image"))
			image = new ImageIcon(getClass().getResource("/" + n + ".jpg")).getImage();
		type = t;
		name = n;
	}
	
	public Image getImage() {
		if(type.equals("image"))
			return image;
		else
			return null;
	}
	
	public void setImage(Image i) {
		image = i;
	}
	
	/*
	public static Image getImage(String n) {
		return new ImageIcon(getClass().getResource("/" + n + ".jpg")).getImage();
	}
	*/
	
	public static Rectangle getBackgroundDim(Image i, ImageObserver io) {
		Rectangle r = Screen.getFrameDim();
		Rectangle temp = null;
		int w = i.getWidth(io);
		int h = i.getHeight(io);
		double scale1 = r.getWidth()/w, scale2 = r.getHeight()/h;
		if(scale1 > scale2) {
			temp = new Rectangle(0, (int)Math.abs((h * scale1) - r.getHeight()) / 2 * -1, (int)r.getWidth(), (int)(h * scale1));
		} else {
			temp = new Rectangle((int)Math.abs((w * scale2) - r.getWidth()) / 2 * -1, 0, (int)(w * scale2), (int)r.getHeight());
		}
		return temp;
	}
}
