package characters;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import effects.Slash;
import hands.Hand;
import hands.Lean;
import hands.Shield;
import hands.Sword;
import runner.Frame;

public class Amogus extends Character{
	
	//image related variables
	public int rollTimer, leanCount, leanTimer, suspicion;
	public boolean blocking, invincible, control, running;
	private Hand lean;

	public Amogus(int x, int y) {
		this.x = x;
		this.y = y;
		hurtBoxX = x + 16;
		hurtBoxY = y + 6;
		suspicion = 00;
		hurtBoxW = 50;
		hurtBoxH = 70;
		health = 100;
		stamina = 50;
		staminaRegen = .5;
		staminaRegenTimer = 0;
		maxHealth = health;
		maxStamina = stamina;
		control = true;
		blocking = false;
		hurtTimer = 0;
		attackTimer = 0;
		recoilTimer = 0;
		deathTimer = 0;
		leanTimer = 0;
		weaponSelect = 0;
		action = "Stand";
		direction = "Down";
		fileType = ".png";
		weaponSelect = 0;
		shieldSelect = 0;
		shield.add(new Shield(this));
		sword.add(new Sword(this));
		lean = new Lean(this);
	}
	
	public boolean running() {return running;}
	
	public void moveRight() {
		if(control == true) {
			if(running == true && blocking == false) {
				action = "Run";
			}else {
				action = "Walk";
			}
			if(yv == 0) {
				direction = "Right";
			}
			fileType = ".gif";
			if(yv == 0) {
				xv = 4;
			}else {
				xv = 3;
			}
		}
	}
	
	public void moveLeft() {
		if(control == true) {
			if(running == true && blocking == false) {
				action = "Run";
			}else {
				action = "Walk";
			}
			if(yv == 0) {
				direction = "Left";
			}
			fileType = ".gif";
			if(yv == 0) {
				xv = -4;
			}else {
				xv = -3;
			}
		}
	}
	
	public void moveUp() {
		if(control == true) {
			if(running == true && blocking == false) {
				action = "Run";
			}else {
				action = "Walk";
			}
			if(xv == 0) {
				direction = "Up";
			}
			fileType = ".gif";
			if(xv == 0) {
				yv = -4;
			}else {
				yv = -3;
			}
		}
	}
	
	public void moveDown() {
		if(control == true) {
			if(running == true && blocking == false) {
				action = "Run";
			}else {
				action = "Walk";
			}
			if(xv == 0) {
				direction = "Down";
			}
			fileType = ".gif";
			if(xv == 0) {
				yv = 4;
			}else {
				yv = 3;
			}
		}
	}
	
	public void stopMoveRight() {
		if(control == true) {
			if(xv > 0) {
				xv = 0;
			}
			if(yv < 0) {
				direction = "Up";
			}else if(yv > 0) {
				direction = "Down";
			}
		}
	}
	
	public void stopMoveLeft() {
		if(control == true) {
			if(xv < 0) {
				xv = 0;
			}
			if(yv < 0) {
				direction = "Up";
			}else if(yv > 0) {
				direction = "Down";
			}
		}
	}
	
	public void stopMoveUp() {
		if(control == true) {
			if(yv < 0) {
				yv = 0;
			}
			if(xv < 0) {
				direction = "Left";
			}else if(xv > 0) {
				direction = "Right";
			}
		}
	}
	
	public void stopMoveDown() {
		if(control == true) {
			if(yv > 0) {
				yv = 0;
			}
			if(xv < 0) {
				direction = "Left";
			}else if(xv > 0) {
				direction = "Right";
			}
		}
	}
	
	public void run() {
		running = true;
		stopShield();
		if(xv != 0 || yv != 0) {
			action = "Run";
		}
	}
	
	public void stopRun() {
		running = false;
		if(xv != 0 || yv != 0) {
			action = "Walk";
		}
	}
	
	public void attack() {
		if(attackTimer <= 0 && health > 0 && blocking == false && stamina >= 5) {
			attackTimer = 15;
			decreaseStamina(5);
			((Sword)sword.get(weaponSelect)).effect.play();
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
			
			for(Character character : Frame.enemies) {
				System.out.println(checkHitBox(character));
				if(checkHitBox(character)){
					character.takeDamage(10, direction);
				}
			}
		}
	}
	
	public void shield() {
		if(attackTimer == 0 && rollTimer == 0 && control == true && staggerTimer == 0) {
			blocking = true;
			if(running == true) {
				action = "Walk";
			}
		}
	}
	
	public void stopShield() {
		blocking = false;
		if(running == true) {
			action = "Run";
		}
	}
	
	public void roll() {
		if(control == true && rollTimer == 0 && stamina >= 20) {
			rollTimer = 23;
			leanTimer = 0;
			running = false;
			switch(direction) {
			case "Right":
				xv = 10;
				break;
				
			case "Left":
				xv = -10;	
				break;
							
			case "Up":
				yv = -10;
				break;
				
			case "Down":
				yv = 10;
				break;
			}
			decreaseStamina(20);
		}
	}
	
	public void lean() {
		if(control && leanTimer == 0) {
			leanTimer = 60;
			xv = 0;
			yv = 0;
		}
	}
	
	public void takeDamage(int damage, String direction) {
		boolean blocked = false;
		
		if(blocking == true) {
			switch(this.direction) {
			case "Right":
				if(direction.equals("Left")) {
					blocked = true;
				}
				break;
				
			case "Left":
				if(direction.equals("Right")) {
					blocked = true;
				}
				break;
			
			case "Up":
				if(direction.equals("Down")) {
					blocked = true;
				}
				break;
				
			case "Down":
				if(direction.equals("Up")) {
					blocked = true;
				}
				break;
			}
		}
		if(blocked == true) {
			if(stamina - damage / 2 >= 0) {
				decreaseStamina(damage / 2);
			}else {
				stopShield();
				stamina = 0;
				staminaRegenTimer = 30;
				staggerTimer = 30;
			}
		}else if(invincible == false && health > 0) {
			if(damage >= health) {
				die();
			}else {
				health -= damage;
				hurtTimer = 20;
				running = false;
			}
		}
	}
	
	public void die() {
		health = 0;
		control = false;
		attackTimer = 0;
		hurtTimer = 0;
		rollTimer = 0;
		deathTimer = 30;
		xv = 0;
		yv = 0;
		switch(direction) {
		case "Right":
			direction = "Right";
			break;
			
		case "Left":
			direction = "Left";
			break;
			
		case "Up":
			direction = "Right";
			break;
			
		case "Down":
			direction = "Left";
			break;
		}
		fileType = ".gif";
		action = "Death";
	}

	public void update() {
		if(running == true && blocking == false && rollTimer == 0&& stamina >= .5) {
			y += 2 * yv;
			x += 2 * xv;
			if(xv != 0 || yv != 0) {
				decreaseStamina(.5);
			}
		}else {
			stopRun();
			x += xv;
			y += yv;
		}	
		
		hurtBoxX = x + 16;
		hurtBoxY = y + 6;
		
		if(xv == 0 && yv == 0 && health > 0 && rollTimer == 0) {
			action = "Stand";
			fileType = ".png";
		}
		
		if(rollTimer > 0) {
			if(rollTimer > 8) {
				control = false;
			}
			
			if(rollTimer <= 23 && rollTimer > 18) {
				action = "Roll1";
				fileType = ".png";
			}else if(rollTimer <= 18 && rollTimer > 8) {
				action = "Roll2";
				fileType = ".gif";
			}else if(rollTimer == 8) {
				action = "Stand";
				fileType = ".png";
				control = true;
				xv = 0;
				yv = 0;
			}
			rollTimer --;
		}else {
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
		}
		
		if(hurtTimer > 0) {
			if(hurtTimer == 1) {
				invincible = false;
			}else {
				invincible = true;
			}
			hurtTimer --;
		}
		
		if(deathTimer > 0 && health <= 0) {
			if(deathTimer <= 20 && deathTimer > 1) {
				action = "Dead";
				fileType = ".png";
			}else if(deathTimer == 1){
				action = "Dead2";
				fileType = ".png";
			}
			deathTimer --;
		}
		
		if(leanTimer > 0) {
			leanTimer --;
			if(xv != 0 || yv != 0) {
				leanTimer = 0;
			}
			if(leanTimer == 1) {
				if(health + 40 > maxHealth) {
					health = maxHealth;
				}else {
					health += 40;
				}
			}
		}
		
		if(staggerTimer > 0) {
			staggerTimer --;
		}
		
		sword.get(weaponSelect).copyAction();
		shield.get(shieldSelect).copyAction();
		((Sword)sword.get(weaponSelect)).effect.follow();
		
		if(attackTimer > 0) {
			attackTimer --;
			shield.get(shieldSelect).setAction("Attack");
			shield.get(shieldSelect).setFileType(".png");
			sword.get(weaponSelect).setAction("Attack");
			sword.get(weaponSelect).setFileType(".gif");
		}
		
		if(blocking == true) {
			shield.get(shieldSelect).setAction("Block");
			sword.get(weaponSelect).setAction("Block");
			shield.get(shieldSelect).setFileType(".png");
			sword.get(weaponSelect).setFileType(".png");
		}
		
		regenStamina();
		
		img = getImage("/amogusSprites/amogus" + action + direction + fileType);
		init(x,y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		update();
		
		if(health > 0 && rollTimer < 7)
			switch(direction) {
			case "Right":
				shield.get(shieldSelect).paint(g);
				g2.drawImage(img, tx, null);
				if(leanTimer == 0) {
					sword.get(weaponSelect).paint(g);
				}else {
					lean.paint(g);
				}
				break;
			case "Left":
				if(leanTimer == 0) {
					sword.get(weaponSelect).paint(g);
				}else {
					lean.paint(g);
				}				
				g2.drawImage(img, tx, null);
				shield.get(shieldSelect).paint(g);
				break;
			case "Up":
				shield.get(shieldSelect).paint(g);
				if(leanTimer == 0) {
					sword.get(weaponSelect).paint(g);
				}else {
					lean.paint(g);
				}
				g2.drawImage(img, tx, null);
				break;
			case "Down":
				g2.drawImage(img, tx, null);
				if(leanTimer == 0) {
					sword.get(weaponSelect).paint(g);
				}else {
					lean.paint(g);
				}				
				shield.get(shieldSelect).paint(g);
				break;		
		}else {
			g2.drawImage(img, tx, null);
		}
		
		g.setColor(new Color(0,0,0));
		g.drawRect(hitBoxX, hitBoxY, hitBoxW, hitBoxH);
		g.drawRect(hurtBoxX, hurtBoxY, hurtBoxW, hurtBoxH);
				
		if(health == 0) {
			Frame.deathScreen.fadeIn();
		}
	}
}