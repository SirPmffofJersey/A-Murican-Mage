package Graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Button extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;
	private RoundRectangle2D rRectFill, rRectDraw;
	private Rectangle2D rectUR, rectLL, rectURD, rectLLD;
	private double widthFill, heightFill, widthDraw, heightDraw;
	private Area areaFill, areaDraw;
	private Shape shape;
	private static final int BORDER_WIDTH = 5;
	private static final Insets INSETS_MARGIN = new Insets(2,5,2,5);
	
	public Button(String n) {
		super(n);
		this.setContentAreaFilled(false);
	    this.setMargin(INSETS_MARGIN);
	    this.setFocusPainted(false);
	    Font font  = (Font)UIManager.get("Button.font");
	    Frame frame = JOptionPane.getRootFrame();
	    FontMetrics fm = frame.getFontMetrics(font);
		setShape();
	}
	
	protected void setShape() {

	    //area --------------------------------------
		
		widthFill = this.getBounds().getWidth() - 1;
		heightFill = this.getBounds().getHeight() - 1;
		widthDraw = widthFill - BORDER_WIDTH - 1;
		heightDraw = heightFill - BORDER_WIDTH - 1;
	    double dArcLengthFill = Math.min(widthFill, heightFill);
	    double dOffsetFill = dArcLengthFill / 2;

	    rRectFill = new RoundRectangle2D.Double(
	      0d, 0d, widthFill, heightFill, 
	      dArcLengthFill, dArcLengthFill);
	    //WARNING: arclength and archeight are divided by 2
	    //when they get into the roundedrectangle shape

	    rectUR = new Rectangle2D.Double(
	      0d, dOffsetFill, widthFill - dOffsetFill, 
	      heightFill - dOffsetFill);
	    rectLL = new Rectangle2D.Double(
	      dOffsetFill, 0d, widthFill - dOffsetFill, 
	    heightFill - dOffsetFill);

	    areaFill = new Area(rRectFill);
	    areaFill.add(new Area(rectUR));
	    areaFill.add(new Area(rectLL));

	    //border ------------------------------------------------

	    double dArcLengthDraw = Math.min(widthDraw, heightDraw);
	    double dOffsetDraw = dArcLengthDraw / 2;

	    rRectDraw = new RoundRectangle2D.Double(
	      (BORDER_WIDTH - 1) / 2,
	      (BORDER_WIDTH - 1) / 2,
	      widthDraw,
	      heightDraw,
	      dArcLengthDraw,
	      dArcLengthDraw);

	    rectURD = new Rectangle2D.Double(
	      (BORDER_WIDTH - 1) / 2,
	      dOffsetDraw + (BORDER_WIDTH - 1) / 2,
	      widthDraw - dOffsetDraw,
	      heightDraw - dOffsetDraw);

	    rectLLD = new Rectangle2D.Double(
	      dOffsetDraw + (BORDER_WIDTH - 1) / 2,
	      (BORDER_WIDTH - 1) / 2,
	      widthDraw - dOffsetDraw,
	      heightDraw - dOffsetDraw);

	    areaDraw = new Area(rRectDraw);
	    areaDraw.add(new Area(rectURD));
	    areaDraw.add(new Area(rectLLD));
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

	    RenderingHints hints = new RenderingHints(
	      RenderingHints.KEY_ANTIALIASING,
	      RenderingHints.VALUE_ANTIALIAS_ON
	      );
	    g2.setRenderingHints(hints);

	    if(getModel().isArmed()){
	      g2.setColor(Color.cyan);
	    }
	    else{
	      if (this.hasFocus()) {
	        g2.setColor(Color.blue);
	      }
	      else {
	        g2.setColor(Color.yellow);
	      }
	    }

	    g2.fill(areaFill);

	    super.paintComponent(g2);
	  }
	  ////////////////////////////////////////////////
	  protected void paintBorder(Graphics g){

	    Graphics2D g2 = (Graphics2D)g;

	    RenderingHints hints = new RenderingHints(
	      RenderingHints.KEY_ANTIALIASING,
	      RenderingHints.VALUE_ANTIALIAS_ON
	      );
	    g2.setRenderingHints(hints);

	    g2.setColor(Color.black);

	    Stroke strokeOld = g2.getStroke();
	    g2.setStroke(
	      new BasicStroke(
	        BORDER_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
	    );
	    g2.draw(areaDraw);

	    if(this.hasFocus()){
	      g2.setColor(Color.white);
	      g2.draw(areaDraw);
	    }

	    g2.setStroke(strokeOld);
	  }
	  ////////////////////////////////////////////////
	  public boolean contains(int nX, int nY){
	    if(null == shape || shape.getBounds().equals(getBounds())){
	      shape = new Rectangle2D.Float(
	        0, 0, this.getBounds().width, this.getBounds().height);
	    }
	    return shape.contains(nX, nY);
	}
	  
	  public void componentResized(ComponentEvent e){
		    shape = new Rectangle2D.Float(
		      0, 0, this.getBounds().width, this.getBounds().height);

		    widthFill  = (double)this.getBounds().width - 1;
		    heightFill = (double)this.getBounds().height -1;

		    widthDraw  = ((double)this.getBounds().width-1) - 
		      (BORDER_WIDTH - 1);
		    heightDraw = ((double)this.getBounds().height-1)- 
		      (BORDER_WIDTH - 1);

		    this.setShape();
		  };
		  ////////////////////////////////////////////////
		  public void componentHidden(ComponentEvent e){};
		  public void componentMoved(ComponentEvent e){};
		  public void componentShown(ComponentEvent e){};
		  ////////////////////////////////////////////////
		  ////////////////////////////////////////////////
		  //This is so the button is triggered when it has focus 
		  //and we press the Enter key.
		  public void keyPressed(KeyEvent e){
		    if(e.getSource() == this && e.getKeyCode() == KeyEvent.VK_ENTER){
		      this.doClick();
		    }
		  }
}
