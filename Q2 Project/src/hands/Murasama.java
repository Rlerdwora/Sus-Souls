package hands;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import characters.Character;
import effects.Effect;
import effects.MurasamaSlash;
import effects.Slash;

public class Murasama extends Sword{
	
	public Murasama(Character character) {
		super(character);
		effect = new MurasamaSlash(character);
		toString = "sword";
		direction = "Down";
		action = "Stand";
		fileType = ".gif";
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	public void copyAction() {
		action = character.action;
		fileType = ".gif";
	}
	
	public void update() {
		super.follow();
		
		switch(direction) {
		case "Right":			
			switch(action) {
			case "Run":
				xPos = 0;
				yPos = 0;
				break;
			
			case "Walk":
				xPos = 25;
				yPos = -15;
				break;
				
			case "Stand":
				xPos = 25;
				yPos = -15;
				break;
				
			case "Block":
				xPos = 0;
				yPos = 0;
				break;
				
			case "Attack":
				xPos = -40;
				yPos = -30;
				break;
			}
			break;
		
		case "Left":
			switch(action) {
			case "Run":
				xPos = 0;
				yPos = 0;
				break;
			
			case "Walk":
				xPos = -25;
				yPos = -15;
				break;
				
			case "Stand":
				xPos = -25;
				yPos = -15;
				break;
				
			case "Block":
				xPos = 0;
				yPos = 0;
				break;
				
			case "Attack":
				xPos = -40;
				yPos = -30;
				break;
			}
			break;
			
		case "Up":
			switch(action) {
			case "Run":
				xPos = 25;
				yPos = 10;
				break;
			
			case "Walk":
				xPos = 25;
				yPos = -10;
				break;
				
			case "Stand":
				xPos = 25;
				yPos = -10;
				break;
				
			case "Block":
				xPos = 30;
				yPos = 10;
				break;
				
			case "Attack":
				xPos = -45;
				yPos = -37;
				break;
			}
			break;
			
		case "Down":
			switch(action) {
			case "Run":
				xPos = -25;
				yPos = -10;
				break;
			
			case "Walk":
				xPos = -25;
				yPos = -10;
				break;
				
			case "Stand":
				xPos = -25;
				yPos = -10;
				break;
				
			case "Block":
				xPos = -30;
				yPos = 10;
				break;
				
			case "Attack":
				xPos = -45;
				yPos = -12;
				break;
			}
			break;
		}
		
		img = getImage("/handSprites/murasama" + action + direction + fileType);
		init(x + xPos, y + yPos);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
		effect.paint(g2);
	}
}