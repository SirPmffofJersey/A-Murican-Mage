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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Main.Game;
import Main.Media;

public class Menu extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;	//Research more
	private String name;
	private Rectangle dim;		//Not used yet
	private JButton[] buttons;
	private JLabel[] labels;
	private Image image;
	private Rectangle r;
	
	
	public Menu(String n) {
		name = n;
		dim = getDim(n);
		buttons = setButtons(n);
		labels = setLabels(n);
	}
	
	public void paintComponent(Graphics g) {
		System.out.println("Painting Menu");
		Graphics2D g2 = (Graphics2D)g;
		if(name.equals("Start") || name.equals("Pause")) {
			System.out.println("Painting Flag");
			image = new Media("Flag", "image").getImage();
		} else if(name.equals("Inventory")) {
			System.out.println("Painting Inventory");
			image = new Media("InventoryV1", "image").getImage();
		} else {
			System.out.println("Painting City");
			image = new Media("city1", "image").getImage();
		}
		r = Media.getBackgroundDim(image, this);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(), this);
	}
	
	private Rectangle getDim(String n){
		if(n.equals("Start") || n.equals("Pause"))
			return Screen.getFrameDim();
		return new Rectangle(0,0,0,0);
	}

	public void addJComp(Container c) {
		System.out.println("Adding Elements");
		if(hasButtons())
			addButtons(c);
		if(hasLabels())
			addLabels(c);
	}

	public void removeJComp(Container c) {
		System.out.println("Removeing Elements");
		if(hasButtons())
			removeButtons(c);
		if(hasLabels())
			removeLabels(c);
	}
	
	private JButton[] setButtons(String n) {
		JButton[] b = null;
		if(n.equals("Start")) {
			System.out.println("Setting Buttons");
			b = new JButton[2];
			//Exit Button
			b[0] = new JButton("Exit");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_E));
			b[0].setActionCommand("Exit");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 - 25, 100, 50);
			//Play Button
			b[1] = new JButton("Play");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_P));
			b[1].setActionCommand("Play");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height / 2 - 25, 100, 50);
		} else if(n.equals("Pause")) {
			b = new JButton[3];
			//Exit Button
			b[0] = new JButton("Exit");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_E));
			b[0].setActionCommand("Exit");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 - 25, 100, 50);
			//New Game Button
			b[1] = new JButton("New Game");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_N));
			b[1].setActionCommand("New Game");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height / 2 - 25, 100, 50);
			//Back Button
			b[2] = new JButton("Back");
			b[2].setVerticalTextPosition(AbstractButton.CENTER);
			b[2].setHorizontalTextPosition(AbstractButton.CENTER);
			b[2].setMnemonic((KeyEvent.VK_B));
			b[2].setActionCommand("Back");
			b[2].setEnabled(true);
			b[2].setVisible(true);
			b[2].addActionListener(this);
			b[2].setBounds(Screen.getFrameDim().width / 2 - 50, Screen.getFrameDim().height * 3 / 4 + 125, 100, 50);
		} else if(n.equals("Inventory")) {
			b = new JButton[6];
			Rectangle r = Screen.getFrameDim();
			//Back Button
			b[0] = new JButton("Back");
			b[0].setVerticalTextPosition(AbstractButton.CENTER);
			b[0].setHorizontalTextPosition(AbstractButton.CENTER);
			b[0].setMnemonic((KeyEvent.VK_B));
			b[0].setActionCommand("Back");
			b[0].setEnabled(true);
			b[0].setVisible(true);
			b[0].addActionListener(this);
			b[0].setBounds((int)(r.getX() + r.getWidth() * .3), (int)(r.getY() + r.getHeight() * (5.0/6)), (int)(r.getWidth() * .4), (int)(r.getHeight() * .1));
			//Health Potion Button
			b[1] = new JButton("Health Potion");
			b[1].setVerticalTextPosition(AbstractButton.CENTER);
			b[1].setHorizontalTextPosition(AbstractButton.CENTER);
			b[1].setMnemonic((KeyEvent.VK_H));
			b[1].setActionCommand("SelectH");
			b[1].setEnabled(true);
			b[1].setVisible(true);
			b[1].addActionListener(this);
			b[1].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Mana Potion Button
			b[2] = new JButton("Mana Potion");
			b[2].setVerticalTextPosition(AbstractButton.CENTER);
			b[2].setHorizontalTextPosition(AbstractButton.CENTER);
			b[2].setMnemonic((KeyEvent.VK_M));
			b[2].setActionCommand("SelectM");
			b[2].setEnabled(true);
			b[2].setVisible(true);
			b[2].addActionListener(this);
			b[2].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (1.0/2)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Dagger Button
			b[3] = new JButton("Dagger");
			b[3].setVerticalTextPosition(AbstractButton.CENTER);
			b[3].setHorizontalTextPosition(AbstractButton.CENTER);
			b[3].setMnemonic((KeyEvent.VK_D));
			b[3].setActionCommand("SelectD");
			b[3].setEnabled(true);
			b[3].setVisible(true);
			b[3].addActionListener(this);
			b[3].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Saber Button
			b[4] = new JButton("Saber");
			b[4].setVerticalTextPosition(AbstractButton.CENTER);
			b[4].setHorizontalTextPosition(AbstractButton.CENTER);
			b[4].setMnemonic((KeyEvent.VK_S));
			b[4].setActionCommand("SelectS");
			b[4].setEnabled(true);
			b[4].setVisible(true);
			b[4].addActionListener(this);
			b[4].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (1.0/2)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
			//Katana Button
			b[5] = new JButton("Katana");
			b[5].setVerticalTextPosition(AbstractButton.CENTER);
			b[5].setHorizontalTextPosition(AbstractButton.CENTER);
			b[5].setMnemonic((KeyEvent.VK_K));
			b[5].setActionCommand("SelectK");
			b[5].setEnabled(true);
			b[5].setVisible(true);
			b[5].addActionListener(this);
			b[5].setBounds((int)(r.getX() + r.getWidth() * .4), (int)(r.getY() + r.getHeight() * (2.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .1));
		} else {
			b = new JButton[1];
			b[0] = new JButton("Exit");
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
	
	private void addButtons(Container c) {
		System.out.println("Adding Buttons");
		for(int i = 0; i < buttons.length; i++)
			c.add(buttons[i]);
	}
	
	private void removeButtons(Container c) {
		System.out.println("Removeing Buttons");
		for(int i = 0; i < buttons.length; i++)
			c.remove(buttons[i]);
	} 
	
	private JLabel[] setLabels(String n) {
		JLabel[] l = null;
		if(n.equals("Start")) {
			System.out.println("Setting Labels");
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
			l[0].setFont(new Font("Jokerman", Font.BOLD, 100));
		} else if(n.equals("Inventory")) {
			l = new JLabel[8];
			Rectangle r = Screen.getFrameDim();
			l[0] = new JLabel("Potions Label");
			l[0].setText("Selected Potion: " + "--");
			l[0].setBounds((int)(r.getX() + r.getWidth() * .1), (int)(r.getY() + r.getHeight() * (7.0/60)), (int)(r.getWidth() * .2), (int)(r.getHeight() * .05));
			l[0].setHorizontalAlignment(SwingConstants.CENTER);
			l[0].setVerticalAlignment(SwingConstants.CENTER);
			l[0].setFont(new Font("Arial", Font.PLAIN, 20));
			//
			l[1] = new JLabel("Swords Label");
			l[1].setText("Selected Sword: " + "--");
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
				l[3].setText("Fire Lobber - [Description]");
			l[3].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (1.0/3)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[3].setHorizontalAlignment(SwingConstants.LEFT);
			l[3].setVerticalAlignment(SwingConstants.TOP);
			l[3].setFont(new Font("Arial", Font.PLAIN, 10));
			//
			l[4] = new JLabel("Lightning Strike");
			l[4].setText("Lightning Strike - Locked");
			if(true)
				l[4].setText("Lightning - [Description]");
			l[4].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (31.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[4].setHorizontalAlignment(SwingConstants.LEFT);
			l[4].setVerticalAlignment(SwingConstants.TOP);
			l[4].setFont(new Font("Arial", Font.PLAIN, 10));
			//
			l[5] = new JLabel("Magic Powder");
			l[5].setText("\"Magic\" Powder - Locked");
			if(true)
				l[5].setText("\"Magic\" Powder - [Description]");
			l[5].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (37.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[5].setHorizontalAlignment(SwingConstants.LEFT);
			l[5].setVerticalAlignment(SwingConstants.TOP);
			l[5].setFont(new Font("Arial", Font.PLAIN, 10));
			//
			l[6] = new JLabel("Armor Plated Armor");
			l[6].setText("Armor Plated Armor - Locked");
			if(true)
				l[6].setText("Armor Plated Armor - [Description]");
			l[6].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (43.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[6].setHorizontalAlignment(SwingConstants.LEFT);
			l[6].setVerticalAlignment(SwingConstants.TOP);
			l[6].setFont(new Font("Arial", Font.PLAIN, 10));
			//
			l[7] = new JLabel("Enchanted Steel");
			l[7].setText("Enchanted Steel - Locked");
			if(true)
				l[7].setText("Enchanted Steel - [Description]");
			l[7].setBounds((int)(r.getX() + r.getWidth() * .7), (int)(r.getY() + r.getHeight() * (49.0/75)), (int)(r.getWidth() * .2), (int)(r.getHeight() * (2.0/25)));
			l[7].setHorizontalAlignment(SwingConstants.LEFT);
			l[7].setVerticalAlignment(SwingConstants.TOP);
			l[7].setFont(new Font("Arial", Font.PLAIN, 10));
		}
		return l;
	}
	
	private void addLabels(Container c) {
		System.out.println("Adding Labels");
		for(int i = 0; i < labels.length; i++)
			c.add(labels[i]);
	}
	
	private void removeLabels(Container c) {
		System.out.println("Removeing Labels");
		for(int i = 0; i < labels.length; i++)
			c.remove(labels[i]);
	}
	
	private boolean hasButtons() {
		System.out.println("Checking for Buttons");
		if(buttons == null)
			return false;
		return true;
	}
	
	private boolean hasLabels() {
		System.out.println("Checking for Labels");
		if(labels == null)
			return false;
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
		if("Exit".equals(e.getActionCommand()))
			System.exit(0);
		else if("Play".equals(e.getActionCommand()))
			Screen.setMenu("None");
		else if("Back".equals(e.getActionCommand()))
			Screen.setMenu("None");
		else if("New Game".equals(e.getActionCommand()))
			System.exit(0);
		else if("SelectH".equals(e.getActionCommand()))
			;
		else if("SelectM".equals(e.getActionCommand()))
			;
		else if("SelectD".equals(e.getActionCommand()))
			;
		else if("SelectS".equals(e.getActionCommand()))
			;
		else if("SelectK".equals(e.getActionCommand()))
			;
	}
}
