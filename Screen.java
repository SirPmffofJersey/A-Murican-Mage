import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Game;

/**
 * <h1> Screen Class: </h1>
 * This class stores and runs all of the graphics
 * for the game.
 * 
 * @author Douglas Fisher
 * @since 6/5/16
 */
public class Screen extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static boolean withinMenu;
	private static Menu menu;
	private static GameGraphics game;
   
	/**
	 * This is a constructor that builds an object
	 * of the Screen class.
	 */
	public Screen() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(this);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent a)
			{
				if(a.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if(Game.getGameState())
						setMenu("Pause");
				} else if(a.getKeyCode() == KeyEvent.VK_I) {
					if(Game.getGameState())
						setMenu("Inventory");
				} else {
					Game.getPlayer().move(a);
				}
			}
		});
		
        menu = new Menu("Start");
        withinMenu = true;
        menu.addJComp(this);
        
        game = new GameGraphics();
        addMouseListener(new MouseAdapter() {public void mouseClicked(MouseEvent e) {Game.getPlayer().attack(e);}});
        
        this.setDoubleBuffered(true);
    }
	
	/**
	 * This method returns this screen's JFrame.
	 * @return JFrame This is the screen's JFrame
	 */
	public JFrame getFrame() {return frame;}
	
	/**
	 * This method returns the bounds of this screen's
	 * JFrame.
	 * @return Rectangle This is the bounds of this screen's JFrame
	 */
	public static Rectangle getFrameDim() {return frame.getBounds();}
	
	/**
	 * This method returns this screen's GameGraphics object.
	 * @return GameGraphics This is the screen's GameGraphics
	 */
	public GameGraphics getGameGraphics() {return game;}
	
	/**
	 * This method returns whether or not a menu is being displayed.
	 * @return boolean This is the withinMenu field
	 */
	public static boolean inMenu() {return withinMenu;}
	
	/**
	 * This method paints either a menu or the game play.
	 */
	public void paintComponent(Graphics g) {
		if(menu != null) {
			menu.paintComponent(g);
		}
		if(Game.getGameState()) {
			game.paintComponent(g);
		}
	}
	
	/**
	 * This method changes the menu based on the String
	 * inputed. If "None" the menu is set to null.
	 * @param m This is the name of the new menu
	 */
	public static void setMenu(String m) {
		if(m.equals("None")) {
			game.start(frame.getContentPane());
			withinMenu = false;
			menu.removeJComp(frame.getContentPane());
			menu = null;
			Game.enterGame();
		} else {
			Game.exitGame();
			game.stop(frame.getContentPane());
			withinMenu = true;
			if(menu != null)
				menu.removeJComp(frame.getContentPane());
			menu = new Menu(m);
			menu.addJComp(frame.getContentPane());
		}
	}
}
