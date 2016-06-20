package Graphics;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Item.Sword;
import Main.Game;

/**
 * <h1> Menu Class: </h1>
 * This class paints the constructed menu onto the screen.
 * 
 * @author Douglas Fisher
 * @since 6/5/16
 */
public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 2L;	//Research more
	private String name;
	private Button[] buttons;
	private JLabel[] labels;
	private Image image;
	private Rectangle r;
	
	/**
	 * This is a constructor the builds a Menu object whose
	 * elements depend of the name of the menu.
	 * @param n This is the name of the menu
	 */
	public Menu(String n) {
		name = n;
		buttons = setButtons(n);
		labels = setLabels(n);
		if(name.equals("Start") || name.equals("Pause") || name.equals("Help")) {
			image = new ImageIcon(getClass().getResource("/Flag.png")).getImage();
		} else if(name.equals("Inventory")) {
			image = new ImageIcon(getClass().getResource("/InventoryV1.png")).getImage();
		} else {
			image = new ImageIcon(getClass().getResource("/city1.png")).getImage();
		}
	}
	
	/**
	 * This method performs an specific action based on the 
	 * ActionEvent's action command.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if("Exit".equals(e.getActionCommand())) {
			System.exit(0);
		} else if("Play".equals(e.getActionCommand())) {
			Screen.setMenu("Story");
		} else if("Continue".equals(e.getActionCommand())) {
			Screen.setMenu("None");
		} else if("Help".equals(e.getActionCommand())) {
			Screen.setMenu("Help");
		} else if("Back".equals(e.getActionCommand())) {
			Screen.setMenu("None");
		} else if("Back2".equals(e.getActionCommand())) {
			Screen.setMenu("Start");
		} else if("New Game".equals(e.getActionCommand())) {
			Game.restartGame();
		} else if("SelectH".equals(e.getActionCommand())) {
			Game.getPlayer().setSelectedPotion("Health");
		} else if("SelectM".equals(e.getActionCommand())) {
			Game.getPlayer().setSelectedPotion("Mana");
		} else if("SelectD".equals(e.getActionCommand())) {
			Game.getPlayer().setSword(new Sword("Dagger"));
		} else if("SelectS".equals(e.getActionCommand())) {
			if(Game.getPlayer().getSaberUnlocked())
				Game.getPlayer().setSword(new Sword("Saber"));
		} else if("SelectK".equals(e.getActionCommand())) {
			if(Game.getPlayer().getKatanaUnlocked())
				Game.getPlayer().setSword(new Sword("Katana"));
		}
	}
	
	/**
	 * This method adds all of this menu's
	 * Buttons to a specified container.
	 * @param c This is the Container that the buttons are being added to
	 */
	private void addButtons(Container c) {
		for(int i = 0; i < buttons.length; i++)
			c.add(buttons[i]);
	}
	
	/**
	 * This method adds all of this menu's
	 * JLabels to a specified container.
	 * @param c This is the Container that the labels are being added to
	 */
	private void addLabels(Container c) {
		for(int i = 0; i < labels.length; i++)
			c.add(labels[i]);
	}
	
	/**
	 * This method calls all the sub-methods to add
	 * all of this menu's JComponents to a specified 
	 * Container.
	 * @param c This is the Container that the JComponents are being added to
	 */
	public void addJComp(Container c) {
		addButtons(c);
		addLabels(c);
	}
	
	/**
	 * This method paints the menu onto the screen.
	 * @param This is the graphics that is used to paint onto the screen
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		updateJComp();
		r = Game.getBackgroundDim(image, this);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(), this);
	}
	
	/**
	 * This method removes all of the Buttons
	 * from the specified Container.
	 * @param c This is the container that buttons are being removed from
	 */
	private void removeButtons(Container c) {
		for(int i = 0; i < buttons.length; i++)
			c.remove(buttons[i]);
	}
	
	/**
	 * This method removes all of the JLabels
	 * from the specified Container
	 * @param c This is the container that labels are being removed from
	 */
	private void removeLabels(Container c) {
		for(int i = 0; i < labels.length; i++)
			c.remove(labels[i]);
	}
	
	/**
	 * This method calls all the sub-methods to remove
	 * all of this menu's JComponents from a specified 
	 * Container.
	 * @param c This is the Container that the JComponents are being removed from
	 */
	public void removeJComp(Container c) {
		removeButtons(c);
		removeLabels(c);
	}
	
	/**
	 * This method sets the properties of all of
	 * this menu's buttons.
	 * @param n This is the name of the menu
	 * @return Buttons[] This is the array of Buttons that are associated with this menu
	 */
	private Button[] setButtons(String n) {
		Button[] b = null;
		if(n.equals("Start")) {
			b = new Button[3];
			//Exit Button
			b[0] = new Button("Exit");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_E));
			b[0].setActionCommand("Exit");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 - 25, 100, 50);
			//Play Button
			b[1] = new Button("Play");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_P));
			b[1].setActionCommand("Play");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height / 2 - 25, 100, 50);
			//Help Button
			b[2] = new Button("Help");
			b[2].setVerticalTextPosition(AbstractButton.CENTER);
			b[2].setHorizontalTextPosition(AbstractButton.CENTER);
			b[2].setMnemonic((KeyEvent.VK_H));
			b[2].setActionCommand("Help");
			b[2].setEnabled(true);
			b[2].setVisible(true);
			b[2].addActionListener(this);
			b[2].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 5 / 8 - 25, 100, 50);
		} else if(n.equals("Pause")) {
			b = new Button[3];
			//Exit Button
			b[0] = new Button("Exit");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_E));
			b[0].setActionCommand("Exit");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 - 25, 100, 50);
			//New Game Button
			b[1] = new Button("New Game");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_N));
			b[1].setActionCommand("New Game");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height / 2 - 25, 100, 50);
			//Back Button
			b[2] = new Button("Back");
			b[2].setVerticalTextPosition(AbstractButton.CENTER);
			b[2].setHorizontalTextPosition(AbstractButton.CENTER);
			b[2].setMnemonic((KeyEvent.VK_B));
			b[2].setActionCommand("Back");
			b[2].setEnabled(true);
			b[2].setVisible(true);
			b[2].addActionListener(this);
			b[2].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 + 125, 100, 50);
		} else if(n.equals("Death")) {
			b = new Button[2];
			//New Game Button
			b[0] = new Button("New Game");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_N));
			b[0].setActionCommand("New Game");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			System.out.println(Screen.getFrameDim().width + " " + Screen.getFrameDim().height);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height / 2 - 50, 200, 100);
			//Exit Button
			b[1] = new Button("Exit");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_E));
			b[1].setActionCommand("Exit");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 + 150, 200, 100);
		} else if(n.equals("Win")) {
			b = new Button[2];
			//New Game Button
			b[0] = new Button("New Game");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_N));
			b[0].setActionCommand("New Game");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height / 2 - 50, 200, 100);
			//Exit Button
			b[1] = new Button("Exit");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_E));
			b[1].setActionCommand("Exit");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 + 150, 200, 100);
		} else if(n.equals("Help")) {
			b = new Button[1];
			//Back Button
			b[0] = new Button("Back");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_B));
			b[0].setActionCommand("Back2");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 - 50, 200, 100);
		} else if(n.equals("Story")) {
			b = new Button[1];
			//Back Button
			b[0] = new Button("Continue");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_B));
			b[0].setActionCommand("Continue");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 - 50, 200, 100);
		} else if(n.equals("Inventory")) {
			b = new Button[6];
			Rectangle r = Screen.getFrameDim();
			//Back Button
			b[0] = new Button("Back");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_B));
			b[0].setActionCommand("Back");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds((int)(r.getX() + r.getWidth() * .3), (int)(r.getY() + r.getHeight() * (5.0/6)), (int)(r.getWidth() * .4), (int)(r.getHeight() * .1));
			//Health Potion Button
			b[1] = new Button("Health Potion");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_H));
			b[1].setActionCommand("SelectH");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Mana Potion Button
			b[2] = new Button("Mana Potion");
			b[2].setVerticalTextPosition(AbstractButton.CENTER);
			b[2].setHorizontalTextPosition(AbstractButton.CENTER);
			b[2].setMnemonic((KeyEvent.VK_M));
			b[2].setActionCommand("SelectM");
			b[2].setEnabled(true);
			b[2].setVisible(true);
			b[2].addActionListener(this);
			b[2].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (1.0/2)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Dagger Button
			b[3] = new Button("Dagger");
			b[3].setVerticalTextPosition(AbstractButton.CENTER);
			b[3].setHorizontalTextPosition(AbstractButton.CENTER);
			b[3].setMnemonic((KeyEvent.VK_D));
			b[3].setActionCommand("SelectD");
			b[3].setEnabled(true);
			b[3].setVisible(true);
			b[3].addActionListener(this);
			b[3].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Saber Button
			b[4] = new Button("Saber");
			b[4].setVerticalTextPosition(AbstractButton.CENTER);
			b[4].setHorizontalTextPosition(AbstractButton.CENTER);
			b[4].setMnemonic((KeyEvent.VK_S));
			b[4].setActionCommand("SelectS");
			b[4].setEnabled(true);
			b[4].setVisible(true);
			b[4].addActionListener(this);
			b[4].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (1.0/2)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Katana Button
			b[5] = new Button("Katana");
			b[5].setVerticalTextPosition(AbstractButton.CENTER);
			b[5].setHorizontalTextPosition(AbstractButton.CENTER);
			b[5].setMnemonic((KeyEvent.VK_K));
			b[5].setActionCommand("SelectK");
			b[5].setEnabled(true);
			b[5].setVisible(true);
			b[5].addActionListener(this);
			b[5].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (2.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
		} else {
			b = new Button[1];
			b[0] = new Button("Exit");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_E));
			b[0].setActionCommand("Exit");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(0, 0, 100, 50);
		}
		return b;
	}
	
	/**
	 * This method sets the properties of all of
	 * this menu's labels.
	 * @param n This is the name of the menu
	 * @return JLabels[] This is the array of JLabels that are associated with this menu
	 */
	private JLabel[] setLabels(String n) {
		JLabel[] l = null;
		if(n.equals("Start")) {
			l = new JLabel[1];
			l[0] = new JLabel("A 'Murican Mage");
			l[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
			l[0].setForeground(Color.MAGENTA);
		} else if(n.equals("Pause")) {
			l = new JLabel[1];
			l[0] = new JLabel("Pause");
			l[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setForeground(Color.MAGENTA);
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
		} else if(n.equals("Death")) {
			l = new JLabel[1];
			l[0] = new JLabel("You Died");
			l[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setForeground(Color.MAGENTA);
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
		} else if(n.equals("Win")) {
			l = new JLabel[1];
			l[0] = new JLabel("You Won!");
			l[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setForeground(Color.MAGENTA);
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
		} else if(n.equals("Help")) {
			l = new JLabel[2];
			l[0] = new JLabel("Help");
			l[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setForeground(Color.MAGENTA);
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
			//
			l[1] = new JLabel("Help2");
			l[1].setBounds(0, Screen.getFrameDim().height / 4 - 350, Screen.getFrameDim().width, Screen.getFrameDim().height - (Screen.getFrameDim().height / 4 - 150));
			l[1].setHorizontalAlignment(SwingConstants.CENTER);
			l[1].setVerticalAlignment(SwingConstants.CENTER);
			l[1].setForeground(Color.MAGENTA);
			l[1].setFont(new Font("Arial", Font.BOLD, 20));
		} else if(n.equals("Story")) {
			l = new JLabel[2];
			l[0] = new JLabel("Story");
			l[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setForeground(Color.WHITE);
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
			//
			l[1] = new JLabel("Story2");
			l[1].setBounds(0, Screen.getFrameDim().height / 4 - 350, Screen.getFrameDim().width, Screen.getFrameDim().height - (Screen.getFrameDim().height / 4 - 150));
			l[1].setHorizontalAlignment(SwingConstants.CENTER);
			l[1].setVerticalAlignment(SwingConstants.CENTER);
			l[1].setForeground(Color.WHITE);
			l[1].setFont(new Font("Arial", Font.BOLD, 20));
		} else if(n.equals("Inventory")) {
			l = new JLabel[8];
			Rectangle r = Screen.getFrameDim();
			l[0] = new JLabel("Potions Label");
			l[0].setText("Selected Potion: " + Game.getPlayer().getEquippedPotionName());
			l[0].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setFont(new Font("Arial", Font.PLAIN, 20));
			//
			l[1] = new JLabel("Swords Label");
			l[1].setText("Selected Sword: " + Game.getPlayer().getSword().getName());
			l[1].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			l[1].setHorizontalAlignment(SwingConstants.CENTER);
			l[1].setVerticalAlignment(SwingConstants.CENTER);
			l[1].setFont(new Font("Arial", Font.PLAIN, 20));
			//
			l[2] = new JLabel("Spells Label");
			l[2].setText("Selected Spell: " + "--");
			l[2].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			l[2].setHorizontalAlignment(SwingConstants.CENTER);
			l[2].setVerticalAlignment(SwingConstants.CENTER);
			l[2].setFont(new Font("Arial", Font.PLAIN, 20));
			//
			l[3] = new JLabel("Fire Lobber");
			l[3].setText("Fire Lobber - Locked");
			if(true)
				l[3].setText("Fire Lobber - Launches a fireball towards the direction \nof the cursor. Deals damage and burns enemies.");
			l[3].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[3].setHorizontalAlignment(SwingConstants.LEFT);
			l[3].setVerticalAlignment(SwingConstants.TOP);
			l[3].setForeground(Color.BLACK);
			l[3].setFont(new Font("Arial", Font.PLAIN, 15));
			//
			l[4] = new JLabel("Lightning Strike");
			l[4].setText("Lightning Strike - Locked");
			if(true)
				l[4].setText("Lightning Strike - Sends a burst of lightning towards the direction of the cursor. The lightning moves faster and does more damage than the fireball");
			l[4].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (31.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[4].setHorizontalAlignment(SwingConstants.LEFT);
			l[4].setVerticalAlignment(SwingConstants.TOP);
			l[4].setForeground(Color.BLACK);
			l[4].setFont(new Font("Arial", Font.PLAIN, 15));
			//
			l[5] = new JLabel("Magic Powder");
			l[5].setText("\"Magic\" Powder - Locked");
			if(true)
				l[5].setText("\"Magic\" Powder - Summon \"sugar\" that the player instantly inhales. This will temporarily increase the player's speed.");
			l[5].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (37.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[5].setHorizontalAlignment(SwingConstants.LEFT);
			l[5].setVerticalAlignment(SwingConstants.TOP);
			l[5].setForeground(Color.BLACK);
			l[5].setFont(new Font("Arial", Font.PLAIN, 15));
			//
			l[6] = new JLabel("Armor Plated Armor");
			l[6].setText("Armor Plated Armor - Locked");
			if(true)
				l[6].setText("Armor Plated Armor - Yo dawg, I heard you like armor, so I added armor to your armor so you can defend while you defend.");
			l[6].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (43.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[6].setHorizontalAlignment(SwingConstants.LEFT);
			l[6].setVerticalAlignment(SwingConstants.TOP);
			l[6].setForeground(Color.BLACK);
			l[6].setFont(new Font("Arial", Font.PLAIN, 15));
			//
			l[7] = new JLabel("Enchanted Steel");
			l[7].setText("Enchanted Steel - Locked");
			if(true)
				l[7].setText("Enchanted Steel - It may look exactly the same, but trust me your sword is all sparkily and magical now. Just pertend to see the effect while so slice your enemies.");
			l[7].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (49.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[7].setHorizontalAlignment(SwingConstants.LEFT);
			l[7].setVerticalAlignment(SwingConstants.TOP);
			l[7].setForeground(Color.BLACK);
			l[7].setFont(new Font("Arial", Font.PLAIN, 15));
		}
		return l;
	}
	
	/**
	 * This updates the text and dimensions of all of
	 * this menu's JComponents.
	 */
	private void updateJComp() {
		Rectangle r = Screen.getFrameDim();
		if(name.equals("Start")) {
			//Exit Button
			buttons[0].setText("Exit");
			buttons[0].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 - 25, 100, 50);
			//Play Button
			buttons[1].setText("Play");
			buttons[1].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height / 2 - 25, 100, 50);
			//Help Button
			buttons[2].setText("Help");
			buttons[2].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 5 / 8 - 25, 100, 50);
		} else if(name.equals("Pause")) {
			//Exit Button
			buttons[0].setText("Exit");
			buttons[0].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 - 25, 100, 50);
			//New Game Button
			buttons[1].setText("New Game");
			buttons[1].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height / 2 - 25, 100, 50);
			//Back Button
			buttons[2].setText("Back");
			buttons[2].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 + 125, 100, 50);
		} else if(name.equals("Death")) {
			//New Game Button
			buttons[0].setText("New Game");
			buttons[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height / 2 - 50, 200, 100);
			//Exit Button
			buttons[1].setText("Exit");
			buttons[1].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 - 50, 200, 100);
		} else if(name.equals("Win")) {
			//New Game Button
			buttons[0].setText("New Game");
			buttons[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height / 2 - 50, 200, 100);
			//Exit Button
			buttons[1].setText("Exit");
			buttons[1].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 - 50, 200, 100);
		} else if(name.equals("Help")) {
			buttons[0].setText("Back");
			buttons[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 - 50, 200, 100);
		} else if(name.equals("Story")) {
			buttons[0].setText("Continue");
			buttons[0].setBounds(Screen.getFrameDim().width / 2 - 100, Screen.getFrameDim().height * 3 / 4 - 50, 200, 100);
		} else if(name.equals("Inventory")) {
			//Back Button
			buttons[0].setText("Back");
			buttons[0].setBounds((int)(r.getX() + r.getWidth() * .3), (int)(r.getY() + r.getHeight() * (5.0/6)), (int)(r.getWidth() * .4), (int)(r.getHeight() * .1));
			//Health Potion Button
			buttons[1].setText("Health Potion");
			buttons[1].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Mana Potion Button
			buttons[2].setText("Mana Potion");
			buttons[2].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (1.0/2)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Dagger Button
			buttons[3].setText("Dagger");
			buttons[3].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Saber Button
			buttons[4].setEnabled(Game.getPlayer().getSaberUnlocked());
			buttons[4].setText("Saber");
			buttons[4].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (1.0/2)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Katana Button
			buttons[5].setEnabled(Game.getPlayer().getKatanaUnlocked());
			buttons[5].setText("Katana");
			buttons[5].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (2.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
		} else {
			buttons[0].setText("Exit");
			buttons[0].setBounds(0, 0, 100, 50);
		}
		if(name.equals("Start")) {
			labels[0].setText("A 'Murican Mage");
			labels[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
		} else if(name.equals("Pause")) {
			labels[0].setText("Pause");
			labels[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
		} else if(name.equals("Death")) {
			labels[0].setText("You Died");
			labels[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
		} else if(name.equals("Win")) {
			labels[0].setText("You Won!");
			labels[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
		} else if(name.equals("Help")) {
			labels[0].setText("Help");
			labels[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			labels[1].setText("<html><p>Controls: The game uses WASD for movement and the mouse for attacking.</p><p>Attacking: Left clicking is a melee attack while right clicking uses your selected spell</p><p>Inventory: To access your inventory, press the &lsquo;I&rsquo; key. Once there you can change your"
					+ "<br />selected potion and sword by clicking the buttons. The inventory also has a description of<br />the spells.</p><p>Menu: To access the menu, hit the &lsquo;Esc.&rsquo; key. Once there you can exit or start a new game.</p>"
					+ "<p>Changing Your Selected Spell: To selected a particular spell, hit the num key corresponding <br />to that spell in either the numPad or the Alphanumeric num key.</p><p>Combat: Enemies will move toward and they are within range they will attack at regular intervals.</p>"
					+ "<p>Bosses: There are two bosses that in addition to being stronger, spawn other enemies.<br /></p></html>");
			labels[1].setBounds(0, Screen.getFrameDim().height / 4 , Screen.getFrameDim().width, Screen.getFrameDim().height / 2);
		} else if(name.equals("Story")) {
			labels[0].setText("Story");
			labels[0].setBounds(0, Screen.getFrameDim().height / 4 - 100, Screen.getFrameDim().width, 200);
			labels[1].setText("<html><p>You were just a teenager when all this started. You didn&rsquo;t have a say. No voice. No vote. On <br />Friday, January 20, 2017, to many &lsquo;Murican&rsquo;s shock, Tronald Dump becomes President of the <br />United Steaks. It seemed that vast majority of people were against Dump, but somehow he <br />"
					+ "managed to win. His first act in office was to build his wall, but not just at the Mehican border, at <br />every border. Immigrants were no longer allowed in &lsquo;Murica. In fear of radical resistance, Dump <br />banned all guns. The public seemed in disbelief at Congress, but all Congress said was &ldquo;Buy <br />Dump brand ties for only $29.99&rdquo;. After countless of mistakes and poor decision, it is now two <br />"
					+ "years later and &lsquo;Murica is in ruins. Cities in shambles, fires laying waste to farms and Dump <br />supporters filling the streets. Regular people struggle to survive off of the crumbs of <br />Tronald Dump&rsquo;s meals, which is enough food to feed all of Chyna. All the food that was <br />left for the people was Dump&rsquo;s leftover which were thrown into radioactive. When you <br />"
					+ "ate an overcooked bag of popcorn, you gained magical powers. Your mission is now to <br />go to Dumpsville, the former capital, and find a powerful mind controlling spell to use on <br />Congress to force them to impeach Tronald. Due to Dump&rsquo;s ban on guns, you have only <br />your late grandfather&rsquo;s knife and your magic powers to defeat him. However, he has <br />"
					+ "sent the army, police and his supporters to try and stop you from reaching Congress.</p></html>");
			labels[1].setBounds(0, Screen.getFrameDim().height / 4 , Screen.getFrameDim().width, Screen.getFrameDim().height / 2);
		} else if(name.equals("Inventory")) {
			labels[0].setText("Selected Potion: " + Game.getPlayer().getEquippedPotionName());
			labels[0].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			//
			labels[1].setText("Selected Sword: " + Game.getPlayer().getSword().getName());
			labels[1].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			//
			labels[2].setText("Selected Spell: " + "--");
			labels[2].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			//
			labels[3].setText("Fire Lobber - Locked");
			if(true)
				labels[3].setText("Fire Lobber - Launches a fireball towards the direction \nof the cursor. Deals damage and burns enemies.");
			labels[3].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			//
			labels[4].setText("Lightning Strike - Locked");
			if(true)
				labels[4].setText("Lightning - Sends a burst of lightning towards the direction of the cursor. The lightning moves faster and does more damage than the fireball");
			labels[4].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (31.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			//
			labels[5].setText("\"Magic\" Powder - Locked");
			if(true)
				labels[5].setText("\"Magic\" Powder - Summon \"sugar\" that the player instantly inhales. This will temporarily increase the player's speed.");
			labels[5].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (37.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			//
			labels[6].setText("Armor Plated Armor - Locked");
			if(true)
				labels[6].setText("Armor Plated Armor - Yo dawg, I heard you like armor, so I added armor to your armor so you can defend while you defend.");
			labels[6].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (43.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			//
			labels[7].setText("Enchanted Steel - Locked");
			if(true)
				labels[7].setText("Enchanted Steel - It may look exactly the same, but trust me your sword is all sparkily and magical now. Just pertend to see the effect while so slice your enemies.");
			labels[7].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (49.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
		}
	}
}
