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
import java.util.ArrayList;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//CREATE THE OBJECT (STEP 1)
	Amogus amogus = new Amogus(200,200);

	public void paint(Graphics g) {
		super.paintComponent(g);
		amogus.paint(g);
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
	}
	
	public Frame() {
		JFrame f = new JFrame("Sus Souls");
		f.setSize(new Dimension(1365, 720));
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
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
