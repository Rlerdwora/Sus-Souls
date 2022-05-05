package runner;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import characters.Amogus;
import characters.Character;
import characters.Skeleton;
import hands.Hand;
import hands.Shield;
import hands.SkeletonSword;
import objects.Background;
import objects.Bonfire;
import objects.Brick;
import objects.Chest;
import ui.Bar;
import ui.Camera;
import ui.Equipment;
import ui.Img;
import ui.SuspicionIcon;

import java.util.ArrayList;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	Color red = new Color(255,0,0);
	Color green = new Color(0, 255, 0);
	Color black = new Color(0,0,0);
	public static Character amogus = new Amogus(370,600);
	public static ArrayList<Character> enemies = new ArrayList<Character>();
	public static Img deathScreen = new Img(0, 180, "/uiSprites/deathScreen.png", 0f);
	public static Camera camera = new Camera(amogus);
	SuspicionIcon susIcon = new SuspicionIcon(5,5);
	Bar health = new Bar(90,20, Frame.amogus.health, 10, red);
	Bar stamina = new Bar(90,50, (int)Frame.amogus.stamina, 5, green);
	Background b = new Background(0,0, 1);
	Equipment equipment = new Equipment(10,520);

	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(black);
		g.fillRect(0, 0, 1000, 1000);
		b.paint(g);
		susIcon.paint(g);
		health.updateValue(amogus.health);
		health.paint(g);
		stamina.updateValue((int)amogus.stamina);
		stamina.paint(g);
		for(Character c : enemies) {
			c.paint(g);
		}
		amogus.paint(g);
		camera.focus();
		equipment.paint(g);
		deathScreen.paint(g);
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Sus Souls");
		f.setSize(new Dimension(900, 720));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		enemies.add(new Skeleton(100,100));
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println(arg0.getKeyCode());
		
		//w is pressed
		if(arg0.getKeyCode() == 87) {
			amogus.moveUp();
		}
		
		//a is pressed
		if(arg0.getKeyCode() == 65) {
			amogus.moveLeft();
		}
		
		//s is pressed
		if(arg0.getKeyCode() == 83) {
			amogus.moveDown();
		}
		
		//d is pressed
		if(arg0.getKeyCode() == 68) {
			amogus.moveRight();
		}
		
		//shift is pressed
		if(arg0.getKeyCode() == 16) {
			amogus.run();
		}
		
		//space is pressed
		if(arg0.getKeyCode() == 32) {
			((Amogus) amogus).roll();
		}
		
		//j is pressed
		if(arg0.getKeyCode() == 74) {
			amogus.shield();
		}
		
		//k i pressed
		if(arg0.getKeyCode() == 75) {
			((Amogus) amogus).lean();
		}
		
		//l is pressed
		if(arg0.getKeyCode() == 76) {
			((Amogus) amogus).attack();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		//w is released
		if(arg0.getKeyCode() == 87) {
			amogus.stopMoveUp();
		}
	
		//a is released
		if(arg0.getKeyCode() == 65) {
				amogus.stopMoveLeft();
		}
		
		//s is released
		if(arg0.getKeyCode() == 83) {
			amogus.stopMoveDown();
		}
		
		//d is released
		if(arg0.getKeyCode() == 68) {
			amogus.stopMoveRight();
		}
		
		//shift is released
		if(arg0.getKeyCode() == 16) {
			amogus.stopRun();
		}
		
		if(arg0.getKeyCode() == 74) {
			amogus.stopShield();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
