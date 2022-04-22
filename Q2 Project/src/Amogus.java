import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Amogus extends Character{
	
	//image related variables
	private int rollTimer, leanCount, leanTimer;
	private boolean blocking, invincible, control, running;
	private Hand lean;

	public Amogus(int x, int y) {
		this.x = x;
		this.y = y;
		hurtBoxW = 50;
		hurtBoxH = 70;
		health = 100;
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
		effect.add(new Slash(this));
	}
	
	public int stamina() {return stamina;}
	
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
	
	/*
	public void attack(Enemy e) {
		if(attackTimer <= 0 && rollTimer == 0 && health > 0) {
			if(e.x() < HBX + HBW && e.x() + e.width() > HBX
			&& e.y() < HBY + HBH && e.y() + e.height() > HBY) {
				e.takeDamage();
			}
			attackTimer = 15;
			//slash.slash(direction);
		}
	}*/
	
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
			for(Character character : Frame.enemies) {
				System.out.println(checkHitBox(character));
				if(checkHitBox(character)){
					character.takeDamage(10, direction);
				}
			}
		}
	}
	
	public void shield() {
		if(attackTimer == 0 && rollTimer == 0 && control == true) {
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
		if(control == true && rollTimer == 0) {
			rollTimer = 23;
			leanTimer = 0;
			xv = 0;
			yv = 0;
		}
	}
	
	public void lean() {
		if(control) {
			leanTimer = 60;
			xv = 0;
			yv = 0;
		}
	}
	
	public void takeDamage(int damage, String direction) {
		if(invincible == false && health > 0) {
			if(damage >= health) {
				die();
			}else {
				health -= damage;
				hurtTimer = 20;
				running = false;
				
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

	/* update variables here */
	public void update() {
		if(running == true && blocking == false) {
			y += 2 * yv;
			x += 2 * xv;
		}else {
			x += xv;
			y += yv;
		}	
		
		hurtBoxX = x + 16;
		hurtBoxY = y + 6;
		
		if(xv == 0 && yv == 0 && health > 0 && rollTimer == 0) {
			action = "Stand";
			fileType = ".png";
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
		
		if(rollTimer > 0) {
			if(rollTimer <= 8) {
				control = true;
			}else {
				control = false;
				
				switch(direction) {
				case "Right":
					x += 10;
					break;
					
				case "Left":
					x -= 10;	
					break;
								
				case "Up":
					y -= 10;
					break;
					
				case "Down":
					y += 10;
					break;
				}
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
			}

			rollTimer --;
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
		}
		
		sword.get(weaponSelect).copyAction();
		shield.get(shieldSelect).copyAction();
		effect.get(weaponSelect).follow();
		
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
		
		effect.get(weaponSelect).paint(g);
	}
}