import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * <h1> Sword Class: </h1>
 * This represents a sword held by a character
 * 
 * @author Douglas Fisher
 * @since 6/11/16
 */
public class Sword {
	private Rectangle dim;
	private Image image;
	private String name;
	private int damage;
	private int range;
	private int swingSpeed;
	private final int WIDTH;
	
	/**
	 * This is a constructor that builds a sword
	 * of a specified type
	 * @param n This is the type of sword being constructed
	 */
	public Sword(String n) {
		name = n;
		if(name.equals("Dagger")) {
			damage = 15;
			range = 20;
			swingSpeed = 10;
			WIDTH = 10;
			setImage(1);
		} else if (name.equals("Saber")) {
			damage = 25;
			range = 50;
			swingSpeed = 20;
			WIDTH = 20;
			setImage(1);
		} else if (name.equals("Katana")){
			damage = 20;
			range = 100;
			swingSpeed = 15;
			WIDTH = 20;
			setImage(1);
		} else {
			damage = 10;
			range = 30;
			swingSpeed = 20;
			WIDTH = 10;
		}
	}
	
	/**
	 * This method returns the sword's damage
	 * @return int This is the sword's damage
	 */
	public int getDamage() {return damage;}
	
	/**
	 * This method returns the sword's dimensions
	 * @return Rectangle This is the sword's dimensions
	 */
	public Rectangle getDim() {return dim;}
	
	/**
	 * This method returns this sword's image
	 * @return Image This is the swords image
	 */
	public Image getImage() {return image;}
	
	/**
	 * This method returns this sword's name
	 * @return String This is the sword's name
	 */
	public String getName() {return name;}
	
	/**
	 * This method returns this sword's range
	 * @return int This is the sword's range
	 */
	public int getRange() {return range;}
	
	/**
	 * This method returns how long this sword's "swing" is
	 * @return int This is the sword's swingSpeed
	 */
	public int getSwingSpeed() {return swingSpeed;}
	
	/**
	 * This method returns this sword's width
	 * @return int This is the sword's width
	 */
	public int getWidth() {return WIDTH;}
	
	/**
	 * This method sets the sword's dimensions
	 * @param d This is what the sword's dimensions will be changed to
	 * @param di This is the direction the character swinging the sword is facing
	 */
	public void setDim(Rectangle d, int di) {
		dim = d;
		setImage(di);
	}
	
	/**
	 * This method sets the sword's image
	 * @param d The direction the sword is facing
	 */
	public void setImage(int d) {image =  new ImageIcon(getClass().getResource("/" + name + d + ".gif")).getImage();}
}
