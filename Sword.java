package Item;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sword {
	private Rectangle dim;
	private Image image;
	private String name;
	private boolean equipped;
	private int damage;
	private int range;
	private int swingSpeed;
	private final int WIDTH;
	
	public Sword(String n) {
		name = n;
		if(name.equals("Dagger")) {
			damage = 10;
			range = 20;
			swingSpeed = 10;
			WIDTH = 10;
			setImage("Dagger", 1);
		} else if (name.equals("Saber")) {
			damage = 20;
			range = 50;
			swingSpeed = 20;
			WIDTH = 20;
			setImage("Saber", 1);
		} else if (name.equals("Katana")){
			damage = 15;
			range = 100;
			swingSpeed = 15;
			WIDTH = 20;
			setImage("Katana", 1);
		} else {
			damage = 5;
			range = 30;
			swingSpeed = 20;
			WIDTH = 10;
		}
	}

	public Image getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	public boolean getEquipped() {
		// TODO Auto-generated method stub
		return equipped;
	}
	
	public Rectangle getDim() {
		return dim;
	}
	
	public String getName() {
		return name;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public void setImage(String i, int d) {
		image =  new ImageIcon(getClass().getResource("/" + i + d + ".gif")).getImage();
	}
	
	public void setEquipped(Boolean e) {
		equipped = e;
	}
	
	public void setDim(Rectangle d, int di) {
		dim = d;
		setImage(name, di);
	}

	public int getRange() {
		return range;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getSwingSpeed() {
		return swingSpeed;
	}
}
