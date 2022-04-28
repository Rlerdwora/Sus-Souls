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
import hands.SkeletonSword;
import runner.Frame;
import ui.Camera;

import java.awt.Color;

public class Skeleton extends Character{

	private int moveTimer, randomTimer;
	
	public Skeleton(int x, int y) {
		this.x = x;
		this.y = y;
		hurtBoxW = 50;
		hurtBoxH = 70;
		hitBoxW = 40;
		hitBoxH = 90;
		health = 100;
		hurtTimer = 0;
		attackTimer = 0;
		recoilTimer = 0;
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
		effect.add(new Slash(this));
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
		if(attackTimer <= 0 && health > 0 && blocking == false) {
			attackTimer = 15;
			effect.get(weaponSelect).play();
			switch(direction) {
			case "Right":
				hitBoxX = hurtBoxX + hurtBoxW + 20;
				hitBoxY = hurtBoxY - 10;
				hitBoxW = 40;
				hitBoxH = 90;
				break;
				
			case "Left":
				hitBoxX = hurtBoxX - hitBoxW - 20;
				hitBoxY = hurtBoxY - 10;		
				hitBoxW = 40;
				hitBoxH = 90;
				break;
				
			case "Up":
				hitBoxX = hurtBoxX - 20;
				hitBoxY = hurtBoxY - hurtBoxH + 20;
				hitBoxW = 90;
				hitBoxH = 40;
				break;
				
			case "Down":
				hitBoxX = hurtBoxX - 20;
				hitBoxY = hurtBoxY + hurtBoxH + 20;
				hitBoxW = 90;
				hitBoxH = 40;
				break;
			}
			
			if(checkHitBox(Frame.amogus)) {
				Frame.amogus.takeDamage(10, direction);
			}
		}
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
		hurtTimer = 0;
		deathTimer = 22;
		xv = 0;
		yv = 0;
		fileType = ".gif";
		action = "Death";
	}
	
	public void moveRandom() {
		if(randomTimer > 0) {
			randomTimer --;
			if(randomTimer == 0) {
				moveTimer = (int) (Math.random()*15) + 1;
				int randomX = (int)(Math.random() * 3);
				int randomY = (int)(Math.random() * 3);
				switch(randomX) {
				case 0:
					moveRight();
					break;
					
				case 1:
					moveLeft();
					break;
				}
				switch(randomY) {
				case 0:
					moveUp();
					break;
					
				case 1:
					moveDown();
					break;
				}
			}
		}
		
		if(moveTimer > 0) {
			moveTimer --;
			if(moveTimer == 0) {
				stopMove();
				randomTimer = (int)(Math.random() * 20) + 40;
			}
		}
	}
	
	/* update variables here */
	public void update() {
		
		if(health > 0) {
			if(detect() == true) {
				stopMove();
				follow();
			}else {
				moveRandom();
			}
		}
		
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
		
		x += xv;
		y += yv;
		hurtBoxX = x + 16;
		hurtBoxY = y + 6;
		
		if(health > 0) {
			if(xv == 0 && yv == 0) {
				action = "Stand";
				fileType = ".png";
			}else {
				action = "Walk";
				fileType = ".gif";
			}
		}
		
		sword.get(weaponSelect).copyAction();
		effect.get(weaponSelect).x = x + Camera.x();
		effect.get(weaponSelect).y = y + Camera.y();
		
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
		
		if(attackTimer > 0) {
			attackTimer --;
			sword.get(weaponSelect).setAction("Attack");
			sword.get(weaponSelect).setFileType(".gif");
		}
		
		img = getImage("/skeletonSprites/skeleton" + action + fileType);
		init(x + Camera.x(),y + Camera.y());
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		update();
		
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
		}else {
			g2.drawImage(img, tx, null);
		}
		g.setColor(new Color(255,0,0));
		g.drawOval(hurtBoxX + hurtBoxW/2 + Camera.x() - combatRange, hurtBoxY + hurtBoxH/2 + Camera.y() - combatRange, combatRange * 2, combatRange * 2);
		g.drawRect(hitBoxX + Camera.x(), hitBoxY + Camera.y(), hitBoxW, hitBoxH);
		g.setColor(new Color(0,255,0));
		g.drawOval(hurtBoxX + hurtBoxW/2 + Camera.x() - detectRange, hurtBoxY + hurtBoxH/2 + Camera.y() - detectRange, detectRange * 2, detectRange * 2);
		g.setColor(new Color(0,0,0));
		g.drawRect(hurtBoxX + Camera.x(), hurtBoxY + Camera.y(), hurtBoxW, hurtBoxH);
		effect.get(weaponSelect).paint(g);
	}
}