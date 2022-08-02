package equipment;
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
import effects.Slash;
import runner.Frame;
import ui.Camera;

public class SkeletonSword extends Sword{
	
	public SkeletonSword(Character character) {
		super(character);
	}
	
	public void update() {
		super.follow();
		effect.follow();
		
		if(attackTimer > 0) {
			attackTimer --;
			action = "Attack";
			fileType = ".gif";
			if(attackTimer <= attackStart && attackTimer >= attackEnd) {
				if(attackTimer == attackStart) {
					effect.play();
					hitbox.crewmateHit = false;
				}
				switch(direction) {
				case "Right":
					hitbox.checkCrewmateCollision(x + 66, y - 4, 60, 90, direction);
					break;
					
				case "Left":
					hitbox.checkCrewmateCollision(x - 44, y - 4, 60, 90, direction);
					break;
					
				case "Up":
					hitbox.checkCrewmateCollision(x - 4, y - 46, 90, 60, direction);
					break;
					
				case "Down":
					hitbox.checkCrewmateCollision(x - 4, y + 69, 90, 60, direction);
					break;
				}
				if(attackTimer == attackEnd)
					hitbox.resetCrewmateHit();
			}
		}
		
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
				xPos = -25;
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