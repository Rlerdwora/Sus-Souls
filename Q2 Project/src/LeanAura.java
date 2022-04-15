import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class LeanAura extends Effect{

	public LeanAura(Character character) {
		super(character);
	}
	
	public void play() {
		timer = 60;
	}
	
	/* update variables here */
	public void update() {
		follow();
		
		/*
		switch(direction) {
		case "Right":
			xPos = 50;
			yPos = 0;
			break;
			
		case "Left":
			xPos = -50;
			yPos = 0;
			break;
			
		case "Up":
			xPos = 0;
			yPos = -45;
			break;
			
		case "Down":
			xPos = 0;
			yPos = 45;
			break;
		}*/
		
		img = getImage("/effects/leanAura.gif");
		init(x, y);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		
		if(timer > 0) {
			update();
			g2.drawImage(img, tx, null);
			timer --;
		}
	}
}