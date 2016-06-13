package Item;

public class Potion {
  private boolean equipped;
  private int type;
  private final FILL_VALUE = 50;
  
  public Potion(int t) {
    type = t;
  }
  
  public boolean getEquipped(){return equipped;}
  
  public void setEquipped(boolean e){equipped = e};
  
  public void use(Charactor c) {
    if(type == 0) {
      c.setHealth(Math.min(c.getHealth() + FILL_VALUE, c.getMaxHealth));
    } else {
      c.setMana(Math.min(c.getMana() + FILL_VALUE, c.getMaxMana()));
    }
  }
}
