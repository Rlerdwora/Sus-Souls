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

public class Murasama extends Sword{
	
	public int count = 1;
	
	public Murasama(Character character) {
		super(character);
		effect = new MurasamaSlash(character);
		damage = 30;
		staminaReduction = 3;
		attackTimerSet = 10;
		attackStart = 8;
		attackEnd = 2;
	}
	
	public String toString() {
		return "murasama";
	}
	
	public void copyAction() {
		action = character.action;
		fileType = ".gif";
	}
	
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
					hitbox.hits = new boolean[Frame.b.enemies.size()];
					for(boolean b : hitbox.hits) {
						b = false;
					}
				}
				switch(direction) {
				case "Right":
					hitbox.checkCollision(x + 66, y - 4, 60, 90, direction);
					break;
					
				case "Left":
					hitbox.checkCollision(x - 44, y - 4, 60, 90, direction);
					break;
					
				case "Up":
					hitbox.checkCollision(x - 4, y - 46, 90, 60, direction);
					break;
					
				case "Down":
					hitbox.checkCollision(x - 4, y + 69, 90, 60, direction);
					break;
				}
				if(attackTimer == attackEnd)
					hitbox.resetHits();
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
		
		img = getImage("/equipmentSprites/murasama" + action + direction + fileType);
		init(x + xPos, y + yPos);
	}
}