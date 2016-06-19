package Item;

public class Potion {
	private final int FILL_VALUE = 50;
	private int quantity;
	private int type;
	private boolean equipped;
	
	public Potion(int t) {
		type = t;
		quantity = 1;
		equipped = false;
	}
	
	public Potion(int t, int q) {
		type = t;
		quantity = q;
	}
	
	public void addPotion() {
		quantity++;
	}
	
	public void addPotion(int num) {
		quantity += num;
	}
	
	public int getType() {
		return type;
	}
	
	public int getQuantity() {return quantity;}
	
	public boolean getEquipped(){return equipped;}
	  
	public void setEquipped(boolean e){equipped = e;}
	
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

