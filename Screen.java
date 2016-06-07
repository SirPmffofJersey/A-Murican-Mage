package Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Game;

public class Screen extends JPanel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;	//Research more
	private static JFrame frame;
	private static boolean withinMenu;
	private static String menuName;
	private static Menu menu;
	private static GameGraphics game;
   
	public Screen() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setContentPane(this);
		
		System.out.println("Set Menu");
        menu = new Menu("Start");
        withinMenu = true;
        menuName = "Start";
        System.out.println("Added Elements");
        menu.addJComp(this);
        
        game = new GameGraphics();
    }
	
	public void paintComponent(Graphics g) {
		//Image background = new Media("Flag", "image").getImage();
		//background = Media.setBackground(background, this);		//Doesn't work
		//g.drawImage(background, 0, 0, this);
		if(menu != null) {
			System.out.println("Painting Screen");
			menu.paintComponent(g);
		}
		if(Game.getGameState()) {
			System.out.println("Painting Game");
			game.paintComponent(g);
		}
	}
	
	public static void setMenu(String m) {
		menuName = m;
		if(m.equals("None")) {
			Game.enterGame();
			withinMenu = false;
			menu.removeJComp(frame.getContentPane());
			menu = null;
		} else {
			System.out.println("Changing Menu");
			Game.exitGame();
			withinMenu = true;
			menu.removeJComp(frame.getContentPane());
			menu = new Menu(m);
			menu.addJComp(frame.getContentPane());
		}
	}
	
	public static boolean inMenu() {return withinMenu;}
	
	public static String getMenuName() {return menuName;}
	
	public static Menu getMenu() {return menu;}
	
	public static Rectangle getFrameDim() {return frame.getBounds();}
}
