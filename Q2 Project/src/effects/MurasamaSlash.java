package effects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import characters.Character;

public class MurasamaSlash extends Effect{
	
	public MurasamaSlash(Character character) {
		super(character);
	}
	
	public void play() {
		timer = 10;
		direction = character.direction();
	}
	
	/* update variables here */
	public void update() {
		switch(direction) {
		case "Right":
			xPos = -90;
			yPos = -50;
			break;
			
		case "Left":
			xPos = -90;
			yPos = -50;
			break;
			
		case "Up":
			xPos = -80;
			yPos = -60;
			break;
			
		case "Down":
			xPos = -90;
			yPos = -40;
			break;
		}
		
		img = getImage("/effectSprites/murasamaSlash" + direction + ".gif");
		init(x + xPos,y + yPos);
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