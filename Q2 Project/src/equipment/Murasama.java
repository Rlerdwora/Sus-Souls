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
import effects.MurasamaSlash;
import effects.Slash;
import runner.Frame;

		//murasama is a sword, so it extends the sword class
public class Murasama extends Sword{
	
	public int count = 1;				//integer count to keep track of how many times you swing
	
	//constructor that initializes the effect to be the murasama slash and initializes the weapon stats
	public Murasama(Character character) {
		super(character);
		effect = new MurasamaSlash(character);
		damage = 30;
		staminaReduction = 3;
		attackTimerSet = 10;
		attackStart = 8;
		attackEnd = 2;
	}
	
	//tostring for messages
	public String toString() {
		return "murasama";
	}
	
	//copyaction method overriden to add a line of code initializing filetype to a .gif because most
	public void copyAction() {						//murasama sprites are gifs
		action = character.action;
		fileType = ".gif";
	}
	
	//attack method overriden to add and image resseting if-statement
	public void attack() {
		if(attackTimer == 0) {
			if(count == 3) {
				img.flush();
				effect.img.flush();
				count = 1;
			}else {
				count ++;
			}
			attackTimer = attackTimerSet;
			effect.play();
		}
	}
	
	//update method overriden
	public void update() {
		super.follow();
		effect.follow();
		
		if(attackTimer > 0) {				//nested if-statement for hitbox
			attackTimer --;					//attackTimer ticking down
			action = "Attack";				//adjust image variables
			fileType = ".gif";
			if(attackTimer <= attackStart && attackTimer >= attackEnd) {	//if the attackTimer is between the
				if(attackTimer == attackStart) {							//attackStart and attackEnd values
					effect.play();											//the effect plays and the hitbox is
					hitbox.hits = new boolean[Frame.b.enemies.size()];		//active
					for(boolean b : hitbox.hits) {
						b = false;
					}
				}
				switch(direction) {							//switch statement with parameter direction
				case "Right":								//to adjust dimensions of hitbox
					hitbox.startHitBox(x + 66, y - 4, 60, 90, direction);
					break;
					
				case "Left":
					hitbox.startHitBox(x - 44, y - 4, 60, 90, direction);
					break;
					
				case "Up":
					hitbox.startHitBox(x - 4, y - 46, 90, 60, direction);
					break;
					
				case "Down":
					hitbox.startHitBox(x - 4, y + 69, 90, 60, direction);
					break;
				}
				if(attackTimer == attackEnd)				//timer ends, and the hitbox resets
					hitbox.resetHits();
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
		
		//variable naming system
		img = getImage("/equipmentSprites/murasama" + action + direction + fileType);
		//edited xPos and yPos values are used to shift the location of the sprite
		init(x + xPos, y + yPos);
	}
}