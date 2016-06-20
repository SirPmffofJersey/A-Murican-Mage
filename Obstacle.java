import java.awt.*;

import javax.swing.ImageIcon;

/**
 * description: anything in a room's environment that isn't a character. This includes
 * walls, pillars(walls in the middle of a room), and doorways. 
 * 
 * @author Nathan Nash
 * @version (a version number or a date)
 */
public class Obstacle
{
  private Rectangle dimensions;
  private Image image;
  
  public Obstacle(Rectangle dim, String imgName)
  {
   dimensions = new Rectangle(dim);
   image = new ImageIcon(getClass().getResource("/" + imgName + ".gif")).getImage();
  }
  
  public boolean isColliding(Rectangle dim)
  {return dimensions.contains(dim) || dimensions.intersects(dim);}
  
  public Image getImage()
  {return image;}
  
  public Rectangle getDimensions()
  {return dimensions;}
  
  public void setLoc(int x, int y)
  {
   dimensions.setLocation(x,y);
  }
  
  public Point getLoc()
  {return dimensions.getLocation();}
  
  public void setImage(String name)
  {image=new ImageIcon(getClass().getResource("/" + name + ".gif")).getImage();}
  
}
