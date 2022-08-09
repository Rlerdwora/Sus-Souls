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
import java.util.ArrayList;

import effects.Slash;
import equipment.Hand;
import equipment.Lean;
import equipment.Murasama;
import equipment.Shield;
import equipment.Shoes;
import equipment.Sword;
import runner.Frame;

public class Amogus extends Character{
	
	//image related variables
	public int rollTimer, leanTimer;	//rolltimer to determine the duration of a roll,
										//leantimer to determine the duration of a potion heal
	
	public int suspicion;				//suspicion vaiable similar to humanity in dark souls, unused
	
	public boolean control;				//control boolean to determine if you are able to control
										//the amogus during certian actions
	
	public int leanCount;				//number of potions currently held
	public Hand lean;					//potion variable unique to the amogus

	//initializes all the variables from the character parent class
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
		staminaRegen = .8;
		staminaRegenTimer = 0;
		maxHealth = health;
		maxStamina = stamina;
		control = true;
		blocking = false;
		deathTimer = 0;
		leanTimer = 0;
		weaponSelect = 0;
		action = "Stand";
		direction = "Down";
		fileType = ".png";
		weaponSelect = 0;
		shieldSelect = 0;
		shoeSelect = 0;
		//every equipment arraylist is filled with a null so the amogus starts out with nothing
		shield.add(null);
		sword.add(null);
		lean = new Lean(this);
		shoes.add(null);
	}
		
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
		if(health > 0) {
			running = true;
			stopShield();
			if(xv != 0 || yv != 0) {
				action = "Run";
			}
		}
	}
	
	public void stopRun() {
		running = false;
		if(xv != 0 || yv != 0) {
			action = "Walk";
		}
	}
	
	public void attack() {
		if(sword.get(weaponSelect) == null || ((Sword)sword.get(weaponSelect)).attackTimer != 0)
			return;
		
		((Sword)sword.get(weaponSelect)).attack();
		decreaseStamina(sword.get(weaponSelect).staminaReduction);
		leanTimer = 0;
		blocking = false;
	}
	
	public void shield() {
		if((sword.get(weaponSelect) == null || ((Sword)sword.get(weaponSelect)).attackTimer == 0) && rollTimer == 0 && control == true && shield.size() > 0) {
			if(shield.get(shieldSelect) == null) {return;}
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
	
	//roll method to initiate the roll
	public void roll() {
		if(control == true && rollTimer == 0 && stamina >= 20) {
			rollTimer = 23;
			leanTimer = 0;
			running = false;
			invincible = true;		//makes you invincible while rolling
			switch(direction) {		//switch statement with direction parameter to determine how the adjust the velocities
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
			decreaseStamina(20);	//rolling decreases stamina
		}
	}
	
	//method to initiate the potion drinking
	public void lean() {
		if(control && leanTimer == 0) {
			leanTimer = 60;
			//drinking makes you unable to move
			xv = 0;
			yv = 0;
		}
	}
	
	//method to take damage
	public void takeDamage(int damage, String direction) {
		if(invincible == true) {return;}						//if you are invincible you do not take any damage
		
		int defense = 0;										//defense variable to determine how much damage is
																//absorbed by the currently held shoe's defense value
		
		if(shoes.size() > 1 && shoes.get(shoeSelect) != null) {	//only changes the defense variable if you are wearing
			defense = shoes.get(shoeSelect).defense;			//a shoe
		}
		
		boolean blocked = false;				//blocked variable to determine if you successfully blocked the damage
		
		if(blocking == true) {					//if your shield is up, and your direction is opposite of
			switch(this.direction) {			//of the attack's direction, blocked is true and you do
			case "Right":						//not take any damage
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
				decreaseStamina(shield.get(shieldSelect).staminaReduction);
			}else {
				stopShield();
				stamina = 0;
				staminaRegenTimer = 30;
			}
		}else if(health > 0) {					//if the player did not block the damage, then they take damage
			if(damage - defense >= health) {
				die();
			}else {
				health -= damage - defense;
				running = false;				//if they were running, they get interrupted
			}
		}
	}
	
	//method to die, initializing the deathtimer variable and changing the sprite to the death sprite
	public void die() {
		health = 0;
		control = false;
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
		//if running is true then you move twice as fast, but lose stamina
		if(running == true && blocking == false && rollTimer == 0&& stamina >= .5) {
			y += 2 * yv;
			x += 2 * xv;
			if(xv != 0 || yv != 0) {
				decreaseStamina(.5);
			}
		}else {				//otherwise move normally
			stopRun();
			x += xv;
			y += yv;
		}	
		
		//hurtbox location is adjusted to fit into the sprite using the x and y variables
		hurtBoxX = x + 16;
		hurtBoxY = y + 6;
		
		//if the player is not moving, then the sprite is standing
		if(xv == 0 && yv == 0 && health > 0 && rollTimer == 0) {
			action = "Stand";
			fileType = ".png";
		}
		
		//nested if statement for the rolltimer decides what rolling sprite is used
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
				invincible = false;
				xv = 0;
				yv = 0;
			}
			rollTimer --;
		}else {						//if not rolling then detect the direction of movement and make tweaks
			if(xv == 0) {			//to xv and yv, making sure that you do not move faster diagonally
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
		
		//nested if statement for the deathtimer so the appropriate sprite is chosen at the right time
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
		
		//nested if statement for leantimer that counts down
		if(leanTimer > 0) {
			leanTimer --;
			if(xv != 0 || yv != 0) {
				leanTimer = 0;
			}
			if(leanTimer == 1) {				//when the timer reaches zero then heal
				if(health + 40 > maxHealth) {
					health = maxHealth;
				}else {
					health += 40;
				}
			}
		}
		
		//when blocking change the images of equipment to be blocking
		if(blocking == true) {
			if(shield.get(shieldSelect) != null){
				shield.get(shieldSelect).action = "Block";
				shield.get(shieldSelect).fileType = ".png";
			}
			
			if(sword.get(weaponSelect) != null) {
				sword.get(weaponSelect).action = "Block";
				sword.get(weaponSelect).fileType = ".png";
			}
		}else {				//if not blocking then make the equipment match the action performed by the amogus
			if(shield.size() > 0 && shield.get(shieldSelect) != null) {
				shield.get(shieldSelect).copyAction();
			}
			
			if(sword.get(weaponSelect) != null) {
				sword.get(weaponSelect).copyAction();
				if(shield.get(shieldSelect) != null && ((Sword)sword.get(weaponSelect)).attackTimer > 0) {
					shield.get(shieldSelect).action = "Attack";
					shield.get(shieldSelect).fileType = ".png";
				}
			}
		}
		
		//constantly call the regenstamina method
		regenStamina();
		
		//variable naming system here, when certain methods are called or nested if-statement timers
		//reach a certain point, they change parts of the string path
		img = getImage("/amogusSprites/amogus" + action + direction + fileType);
		//init method called, making the sprite aligned with the x and y variables
		init(x,y);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//update method called to adjust variables every frame
		update();
		
		//when the player is alive, 
		//facing different directions will cause the amogus and equipment to be drawn
		//in different orders, in other words, the layers of sprites are swapped
		if(health > 0 && rollTimer < 7)
			switch(direction) {
			case "Right":	//in case of facing right, order of drawing is shield, amogus, shoes, sword
				if(shield.size() > 0 && shield.get(shieldSelect) != null) {
					shield.get(shieldSelect).paint(g);
				}	
				g2.drawImage(img, tx, null);
				if(shoes.size() > 0 && shoes.get(shoeSelect) != null) {
					shoes.get(shoeSelect).paint(g2);
				}	
				if(leanTimer == 0) {
					if(sword.size() > 0 && sword.get(weaponSelect) != null) {
						sword.get(weaponSelect).paint(g);
					}
				}else {
					lean.paint(g);
				}
				break;
			case "Left":	//in case of facing left, order of drawing is sword, amogus, shoes, shield
				if(leanTimer == 0) {
					if(sword.size() > 0 && sword.get(weaponSelect) != null) {
						sword.get(weaponSelect).paint(g);
					}
				}else {
					lean.paint(g);
				}				
				g2.drawImage(img, tx, null);
				if(shoes.size() > 0 && shoes.get(shoeSelect) != null) {
					shoes.get(shoeSelect).paint(g2);
				}				
				if(shield.size() > 0 && shield.get(shieldSelect) != null) {
					shield.get(shieldSelect).paint(g);
				}					
				break;
			case "Up":		//in case of facing up, order of drawing is shield, sword, amogus, shoes
				if(shield.size() > 0 && shield.get(shieldSelect) != null) {
					shield.get(shieldSelect).paint(g);
				}					
				if(leanTimer == 0) {
					if(sword.size() > 0 && sword.get(weaponSelect) != null) {
						sword.get(weaponSelect).paint(g);
					}
				}else {
					lean.paint(g);
				}
				g2.drawImage(img, tx, null);
				if(shoes.size() > 0 && shoes.get(shoeSelect) != null) {
					shoes.get(shoeSelect).paint(g2);
				}	
				break;
			case "Down":	//in case of facing down, order of drawing is amogus, shoes, sword, shield
				g2.drawImage(img, tx, null);
				if(shoes.size() > 0 && shoes.get(shoeSelect) != null) {
					shoes.get(shoeSelect).paint(g2);
				}	
				if(leanTimer == 0) {
						if(sword.size() > 0 && sword.get(weaponSelect) != null) {
							sword.get(weaponSelect).paint(g);
						}
				}else {
					lean.paint(g);
				}				
				if(shield.size() > 0 && shield.get(shieldSelect) != null) {
					shield.get(shieldSelect).paint(g);
				}				
				break;
		}else {	//when you are dead only the amogus and shoes are drawn
			g2.drawImage(img, tx, null);
			if(shoes.size() > 0 && shoes.get(shoeSelect) != null) {
				shoes.get(shoeSelect).paint(g2);
			}	
		}
		/*
		g.setColor(new Color(0,0,0));
		g.drawRect(hitBoxX, hitBoxY, hitBoxW, hitBoxH);
		g.drawRect(hurtBoxX, hurtBoxY, hurtBoxW, hurtBoxH);*/
		
		//when the player is dead the death screen will fade in
		if(health == 0) {
			Frame.deathScreen.fadeIn();
		}
	}
}