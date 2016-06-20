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
  private Rectangle dimensions; //bounds and location of the object
  private Image image;
  
  public Obstacle(Rectangle dim, String imgName)
  {
   dimensions = new Rectangle(dim);
   image = new ImageIcon(getClass().getResource("/" + imgName + ".gif")).getImage();
  }
  
  public boolean isColliding(Rectangle dim) //determines if dim is at all within the area of dimensions or intersecting it
  {return dimensions.contains(dim) || dimensions.intersects(dim);}
  
  public Image getImage() //returns the name of which image should be displayed
  {return image;}
  
  public Rectangle getDimensions() //returns the 'dimensions' field of the obstacle
  {return dimensions;}
  
  public void setLoc(int x, int y) //changes the location of the 'dimensions' field
  {
   dimensions.setLocation(x,y);
  }
  
  public Point getLoc() //returns the location of 'dimensions' field
  {return dimensions.getLocation();}
  
  public void setImage(String name) //changes the name of which image should be displayed
  {image=new ImageIcon(getClass().getResource("/" + name + ".gif")).getImage();}
  
}
