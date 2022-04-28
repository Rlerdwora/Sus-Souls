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
import ui.Camera;

public class SkeletonSword extends Hand{
	
	public SkeletonSword(Character character) {
		super(character);
		direction = "Right";
		action = "Stand";
		fileType = ".png";
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
				xPos = 21;
				yPos = 0;
				break;
				
			case "Stand":
				xPos = 21;
				yPos = 0;
				break;
				
			case "Block":
				xPos = 0;
				yPos = 0;
				break;
				
			case "Attack":
				xPos = 30;
				yPos = 10;
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
				xPos = -21;
				yPos = 0;
				break;
				
			case "Stand":
				xPos = -21;
				yPos = 0;
				break;
				
			case "Block":
				xPos = 0;
				yPos = 0;
				break;
				
			case "Attack":
				xPos = -30;
				yPos = 10;
				break;
			}
			break;
			
		case "Up":
			switch(action) {
			case "Run":
				xPos = 25;
				yPos = -10;
				break;
			
			case "Walk":
				xPos = 25;
				yPos = 0;
				break;
				
			case "Stand":
				xPos = 25;
				yPos = 0;
				break;
				
			case "Block":
				xPos = 25;
				yPos = 10;
				break;
				
			case "Attack":
				xPos = -30;
				yPos = -20;
				break;
			}
			break;
			
		case "Down":
			switch(action) {
			case "Run":
				xPos = -25;
				yPos = 10;
				break;
			
			case "Walk":
				xPos = -25;
				yPos = 0;
				break;
				
			case "Stand":
				xPos = -25;
				yPos = 0;
				break;
				
			case "Block":
				xPos = -25;
				yPos = 0;
				break;
				
			case "Attack":
				xPos = -10;
				yPos = 20;
				break;
			}
			break;
		}

		img = getImage("/skeletonSprites/skeletonSword" + action + direction + fileType);
		init(x + xPos + Camera.x(), y + yPos + Camera.y());
	}
}