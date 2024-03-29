package characters;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import effects.Slash;
import equipment.SkeletonSword;
import equipment.Sword;
import runner.Frame;
import ui.Camera;

import java.awt.Color;

							//skeleton class is a child class of Enemy
public class Skeleton extends Enemy{
	
	//constructor with parameters integer x and y to determine where the skeleton is, instantiates all other variables
	public Skeleton(int x, int y) {
		this.x = x;
		this.y = y;
		hurtBoxW = 50;
		hurtBoxH = 70;
		health = 100;
		deathTimer = 0;
		randomTimer = 10;
		weaponSelect = 0;
		detectRange = 300;
		combatRange = 90;
		action = "Stand";
		direction = "Right";
		fileType = ".png";
		weaponSelect = 0;
		sword.add(new SkeletonSword(this));
	}
	
	public void moveRight() {
		fileType = ".gif";
		if(yv == 0) {
			xv = 4;
			direction = "Right";
		}else {
			xv = 3;
		}
	}
	
	public void moveLeft() {
		fileType = ".gif";
		if(yv == 0) {
			xv = -4;
			direction = "Left";
		}else {
			xv = -3;
		}
	}
	
	public void moveUp() {
		fileType = ".gif";
		if(xv == 0) {
			yv = -4;
			direction = "Up";
		}else {
			yv = -3;
		}
	}
	
	public void moveDown() {
		fileType = ".gif";
		if(xv == 0) {
			yv = 4;
			direction = "Down";
		}else {
			yv = 3;
		}
	}
	
	public void stopMoveRight() {
		if(xv > 0) {
			xv = 0;
		}
		if(yv < 0) {
			direction = "Up";
		}else if(yv > 0) {
			direction = "Down";
		}
	}
	
	public void stopMoveLeft() {
		if(xv < 0) {
			xv = 0;
		}
		if(yv < 0) {
			direction = "Up";
		}else if(yv > 0) {
			direction = "Down";
		}
	}
	
	public void stopMoveUp() {
		if(yv < 0) {
			yv = 0;
		}
		if(xv < 0) {
			direction = "Left";
		}else if(xv > 0) {
			direction = "Right";
		}
	}
	
	public void stopMoveDown() {
		if(yv > 0) {
			yv = 0;
		}
		if(xv < 0) {
			direction = "Left";
		}else if(xv > 0) {
			direction = "Right";
		}
	}
	
	public void attack() {
		if(sword.get(weaponSelect) == null)
			return;
		
		((Sword)sword.get(weaponSelect)).attack();
	}
	
	public void takeDamage(int damage, String direction) {
		if(invincible == false && health > 0) {
			if(damage >= health) {
				die();
			}else {
				health -= damage;
				
				switch(direction) {
				case "Right":
					x += 1;
					break;
				
				case "Left":
					x -= 1;
					break;
					
				case "Up":
					y -= 1;
					break;
					
				case "Down":
					y += 1;
					break;
				}
			}
		}
	}
	
	public void die() {
		health = 0;
		attackTimer = 0;
		deathTimer = 22;
		xv = 0;
		yv = 0;
		fileType = ".gif";
		action = "Death";
	}
	
	/* update variables here */
	public void update() {
		
		//if the skeleton is alive it will try to detect the player, if it returns true then it will
		//call the follow method. Otherwise it will randomly move
		if(health > 0) {
			if(detect() == true) {
				follow();
			}else {
				moveRandom();
			}
		}
		
		//nested if statements to adjust the xv and yv in certain directions
		if(xv == 0) {
			if(yv > 0) {
				yv = 4;
			}else if(yv < 0) {
				yv = -4;
			}
		}else if(xv > 0) {
			xv = 4;
			if(yv > 0) {
				yv = 3;
				xv = 3;
			}else if(yv < 0) {
				yv = -3;
				xv = 3;
			}
		}else if(xv < 0) {
			xv = -4;
			if(yv > 0) {
				yv = 3;
				xv = -3;
			}else if(yv < 0) {
				yv = -3;
				xv = -3;
			}
		}
		
		//add the velocities to the coordinates
		x += xv;
		y += yv;
		//align the hurtbox to the sprite
		hurtBoxX = x + 16;
		hurtBoxY = y + 6;
		
		//if alive, the skeleton will be standing if it is still and walking if it is moving
		if(health > 0) {
			if(xv == 0 && yv == 0) {
				action = "Stand";
				fileType = ".png";
			}else {
				action = "Walk";
				fileType = ".gif";
			}
		}
		
		//sword constantly copies the skeleton's action
		sword.get(weaponSelect).copyAction();
		
		//nested if-statement-timer to pick appropriate death sprite
		if(deathTimer > 0 && health <= 0) {
			if(deathTimer <= 20 && deathTimer > 1) {
				action = "Death";
				fileType = ".gif";
			}else if(deathTimer == 1){
				action = "Dead";
				fileType = ".png";
			}
			deathTimer --;
		}
		
		//variable naming system
		img = getImage("/skeletonSprites/skeleton" + action + fileType);
		//initialize sprite location
		init(x, y);
	}
	
	//paint method to draw sprites
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//calls update method to update variables every frame
		update();
		
		//facing different directions while alive will cause the layers of sprites to be ordered differently
		if(health > 0)
			switch(direction) {
			case "Right":
				g2.drawImage(img, tx, null);
				sword.get(weaponSelect).paint(g);
				break;
			case "Left":
				sword.get(weaponSelect).paint(g);				
				g2.drawImage(img, tx, null);
				break;
			case "Up":
				sword.get(weaponSelect).paint(g);			
				g2.drawImage(img, tx, null);
				break;
			case "Down":
				g2.drawImage(img, tx, null);
				sword.get(weaponSelect).paint(g);			
				break;		
		}else {	//only draw the enemy when dead
			g2.drawImage(img, tx, null);
		}
		
		//paint the red eye flash
		effect.paint(g2);
		
		/*
		g.setColor(new Color(255,0,0));
		g.drawOval(hurtBoxX + hurtBoxW/2 + Camera.x() - combatRange, hurtBoxY + hurtBoxH/2 + Camera.y() - combatRange, combatRange * 2, combatRange * 2);
		g.drawRect(hitBoxX + Camera.x(), hitBoxY + Camera.y(), hitBoxW, hitBoxH);
		g.setColor(new Color(0,255,0));
		g.drawOval(hurtBoxX + hurtBoxW/2 + Camera.x() - detectRange, hurtBoxY + hurtBoxH/2 + Camera.y() - detectRange, detectRange * 2, detectRange * 2);
		g.setColor(new Color(0,0,0));
		g.drawRect(hurtBoxX + Camera.x(), hurtBoxY + Camera.y(), hurtBoxW, hurtBoxH);*/
	}
}