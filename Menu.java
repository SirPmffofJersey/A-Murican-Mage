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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
		if(name.equals("Start")) {
			System.out.println("Painting Flag");
			image = new Media("Flag", "image").getImage();
		} else {
			System.out.println("Painting City");
			image = new Media("city1", "image").getImage();
		}
		r = Media.getBackgroundDim(image, this);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(image, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight(), this);
	}
	
	private Rectangle getDim(String n){
		if(n.equals("Start"))
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
	}
}
