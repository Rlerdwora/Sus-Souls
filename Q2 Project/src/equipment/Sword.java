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

		//sword class is subclass of hand, is main class of weapons
public class Sword extends Hand{
	
	public Effect effect;											//effect variable
	
	public int attackTimer, attackTimerSet, attackStart, attackEnd;	//weapon stats
	
	public Hitbox hitbox;											//hitbox variable
	
	public Sword(Character character) {
		super(character);
		damage = 20;
		staminaReduction = 7;
		attackTimerSet = 15;
		attackStart = 9;
		attackEnd = 2;
		effect = new Slash(character);
		hitbox = new Hitbox(damage);
		direction = "Right";
		action = "Stand";
		fileType = ".png";
		type = "sword";
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	//tostring method to identify what kind of equipment the hand subclass is for sorting
	public String toString() {
		return "sword";
	}
	
	//attackmethdo to instantiate the attacktimer to be the attacktimerset
	public void attack() {
		if(attackTimer == 0)
		attackTimer = attackTimerSet;
	}
	
	//copyaction method to copy the action of its character
	public void copyAction() {
		action = character.action;
		if(character.action.equals("Stand")) {
			fileType = ".png";
		}else {
			fileType = ".gif";
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
		img = getImage("/equipmentSprites/handSword" + action + direction + fileType);
		//edited xPos and yPos values are used to shift the location of the sprite
		init(x + xPos, y + yPos);
	}
	
	//paint method overriden
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();							//updates variables
		g2.drawImage(img, tx, null);		//draws image
		effect.paint(g2);					//draws effect	
		
		if(attackTimer <= attackStart && attackTimer >= attackEnd) {	//shows the hitboxes
			switch(direction) {
			case "Right":
				g.drawRect(x + 66, y - 4, 60, 90);
				break;
				
			case "Left":
				g.drawRect(x - 44, y - 4, 60, 90);
				break;
				
			case "Up":
				g.drawRect(x - 4, y - 46, 90, 60);
				break;
				
			case "Down":
				g.drawRect(x - 4, y + 69, 90, 60);
				break;
			}
		}
	}
}