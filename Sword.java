package Item;

import java.awt.Image;
import java.awt.Rectangle;

public class Sword implements Item{
	private Rectangle dim;
	private String image;
	private boolean equipped;
	private int damage;
	private int range;
	private int swingSpeed;
	
	public Sword(String name) {
		if(name.equals("Dagger")) {
			damage = 10;
			range = 10;
			swingSpeed = 10;
		}
	}

	public String getImage() {
		// TODO Auto-generated method stub
		return image;
	}

	public boolean getEquipped() {
		// TODO Auto-generated method stub
		return equipped;
	}
	
	public Rectangle geDim() {
		return dim;
	}
	
	public void setImage(String i) {
		image =  i;
	}
	
	public void setEquipped(Boolean e) {
		equipped = e;
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
