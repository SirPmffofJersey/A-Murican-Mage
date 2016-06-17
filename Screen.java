package Graphics;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Game;

public class Screen extends JPanel implements MouseListener{
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
		frame.setFocusable(true);
		frame.requestFocus();
		frame.addKeyListener(new KeyAdapter()
		{
			
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
		
		System.out.println("Set Menu");
        menu = new Menu("Start");
        withinMenu = true;
        menuName = "Start";
        System.out.println("Added Elements");
        menu.addJComp(this);
        
        game = new GameGraphics();
        addMouseListener(this);
        
        this.setDoubleBuffered(true);
    }
	
	public JFrame getFrame() {
		return frame;
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
			//System.out.println("Painting Game");
			game.paintComponent(g);
		}
	}
	
	public static void setMenu(String m) {
		menuName = m;
		if(m.equals("None")) {
			game.start(frame.getContentPane());
			withinMenu = false;
			menu.removeJComp(frame.getContentPane());
			menu = null;
			Game.enterGame();
		} else {
			System.out.println("Changing Menu");
			Game.exitGame();
			game.stop(frame.getContentPane());
			withinMenu = true;
			if(menu != null)
				menu.removeJComp(frame.getContentPane());
			menu = new Menu(m);
			menu.addJComp(frame.getContentPane());
		}
	}
	
	public static boolean inMenu() {return withinMenu;}
	
	public static String getMenuName() {return menuName;}
	
	public static Menu getMenu() {return menu;}
	
	public static Rectangle getFrameDim() {return frame.getBounds();}
	
	public static GameGraphics getGameGraphics() {return game;}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX());
		System.out.println(e.getY());
		Game.getPlayer().attack(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
