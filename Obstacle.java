import java.awt.*;

/**
 * description: anything in a room's environment that isn't a character. This includes
 * walls, pillars(walls in the middle of a room), and doorways. 
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacle
{
  private Rectangle dimensions;
  private String imageName;
  
  public Obstacle(Rectangle dim, String imgName)
  {
   dimensions = new Rectangle(dim);
   imageName = new String(imgName);
  }
  
  public boolean isColliding(Rectangle dim)
  {return dimensions.contains(dim) || dimensions.intersects(dim);}
  
  public String getImageName()
  {return imageName;}
  
  public Rectangle getDimensions()
  {return dimensions;}
  
  public void setLoc(int x, int y)
  {
   dimensions.setLocation(x,y);
  }
  
  public Point getLoc()
  {return dimensions.getLocation();}
  
  public void setImage(String name)
  {imageName=name;}
  
}
