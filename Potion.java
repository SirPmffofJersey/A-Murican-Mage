package Item;

/**
 * <h1> Potion Class: </h1>
 * This represents the potions of a 
 * specific type held by the player
 * 
 * @author Douglas Fisher
 * @since 6/11/16
 */
public class Potion {
	private final int FILL_VALUE = 50;
	private int quantity;
	private int type;
	private boolean equipped;
	
	/**
	 * This is a constructor thats builds an
	 * object Potion of a specified type.
	 * @param t This is the type of potion (0 - Health, 1 - Mana)
	 */
	public Potion(int t) {
		type = t;
		quantity = 1;
		equipped = false;
	}
	
	/**
	 * This is a constructor thats builds an
	 * object Potion of a specified type with a specified quantity.
	 * @param t This is the type of potion (0 - Health, 1 - Mana)
	 * @param q This is the initial quantity of potions
	 */
	public Potion(int t, int q) {
		type = t;
		quantity = q;
	}
	
	/**
	 * This methods increase the quantity of potions by one.
	 */
	public void addPotion() {quantity++;}
	
	/**
	 * This method adds a specified amount to the quantity
	 * @param num This is the amount to be added to the quantity
	 */
	public void addPotion(int num) {quantity += num;}
	
	/**
	 * This method returns the type of potion this is
	 * @return int This represents the potion type
	 */
	public int getType() {return type;}
	
	/**
	 * This method returns the quantity of this type of potion
	 * @return int This is the quantity of the potion
	 */
	public int getQuantity() {return quantity;}
	
	/**
	 * This method returns whether or not the potion is equipped
	 * @return boolean This represents whether the potion is equipped or not
	 */
	public boolean getEquipped(){return equipped;}
	  
	/**
	 * This methods sets whether this potion is equipped or not
	 * @param e This is what the equipped field will be changed to
	 */
	public void setEquipped(boolean e){equipped = e;}
	
	/**
	 * This method applies the potions intended affect if 
	 * the quantity is greater than zero and then decrements
	 * quantity
	 * @param c This is the Character the affect is being applied to
	 */
	public void use(Charactor.Character c) {
		if(quantity <= 0) 
			return;
		else if( type == 0)
			c.setHealth(Math.min(c.getHealth() + FILL_VALUE, c.getMaxHealth()));
		else
			c.setMana(Math.min(c.getMana() + FILL_VALUE, c.getMaxMana()));
		quantity--;
	}
}
