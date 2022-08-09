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

					//skeleton sword exclusive to enemeis, subclass of sword
public class SkeletonSword extends Sword{
	
	//constructor
	public SkeletonSword(Character character) {
		super(character);
	}
	
	public void update() {
		super.follow();
		effect.follow();
		
		if(attackTimer > 0) {				//nested if-statement for hitbox
			attackTimer --;					//attackTimer ticking down
			action = "Attack";				//adjust image variables
			fileType = ".gif";
			if(attackTimer <= attackStart && attackTimer >= attackEnd) {		//if the attackTimer is between the
				if(attackTimer == attackStart) {								//attackstart and attackend values
					effect.play();												//the effect plays and the effect
					hitbox.crewmateHit = false;									//is active
				}
				switch(direction) {							//switch statement with parameter direction
				case "Right":								//to adjust dimensions of hitbox
					hitbox.startCrewmateHitBox(x + 66, y - 4, 60, 90, direction);
					break;
					
				case "Left":
					hitbox.startCrewmateHitBox(x - 44, y - 4, 60, 90, direction);
					break;
					
				case "Up":
					hitbox.startCrewmateHitBox(x - 4, y - 46, 90, 60, direction);
					break;
					
				case "Down":
					hitbox.startCrewmateHitBox(x - 4, y + 69, 90, 60, direction);
					break;
				}
				if(attackTimer == attackEnd)				//timer ends, and the hitbox resets
					hitbox.resetCrewmateHit();
			}
		}
		
		switch(direction) {				//nested switch statement to adjust xPos and yPos
		case "Right":					//depending on direction and action	
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

		//variable naming system
		img = getImage("/skeletonSprites/skeletonSword" + action + direction + fileType);
		//edited xPos and yPos values are used to shift the location of the sprite
		init(x + xPos + Camera.x(), y + yPos + Camera.y());
	}
}