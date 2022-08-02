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

public class Sword extends Hand{
	
	public Effect effect;
	public int attackTimer, attackTimerSet, attackStart, attackEnd;
	public Hitbox hitbox;
	
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
	
	public String toString() {
		return "sword";
	}
	
	public void attack() {
		if(attackTimer == 0)
		attackTimer = attackTimerSet;
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
		
		img = getImage("/equipmentSprites/handSword" + action + direction + fileType);
		init(x + xPos, y + yPos);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
		effect.paint(g2);
		
		if(attackTimer <= attackStart && attackTimer >= attackEnd) {
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