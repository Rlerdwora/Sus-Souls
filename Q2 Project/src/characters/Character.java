package characters;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

import effects.Effect;
import equipment.Hand;
import equipment.Shield;
import equipment.Shoes;
import equipment.Sword;
import runner.Frame;
import ui.Camera;

public class Character{
	
	//general class for living entities in the game, could be an interface but idk how to use them
	
	public int x, y, xv, yv;							//x and y variables for location, 
														//xv and yv variables for velocity
	
	public int hurtBoxX, hurtBoxY, hurtBoxW, hurtBoxH;	//hurtbox variables for the location
														//and dimensions of character's hurtbox
														//for collision detection
	
	public int health, maxHealth;						//health for current health, maxhealth for
														//the maximum amount of health you can have
	
	public int deathTimer;								//death timer to control the switching of 
														//death animation sprites
	
	public int detectRange, combatRange;				//detectrange is how far enemies can detect
														//the player, combat range is how close the
														//player has to be for the enemy to start attacking
	
	public int weaponSelect, shieldSelect, shoeSelect;	//weaponselect, shieldselect, shoeselect variables
														//to determine which index of the Hand or Shoe objects
														//(refer to equipment package) from the Hand 
														//arraylist to currently hold for each respective
														//equipment type (sword, shield, shoe)
	
	public double stamina, staminaRegen, maxStamina;	//stamina for how much stamina the player currently
														//has, staminaregen for how much the player regenerates
														//stamina, maxstamina for the maximum amount of stamina 
														//player can have
	public int staminaRegenTimer;						//staminaregentimer to tell how long to wait before
														//regenerating stamina

	public boolean blocking, invincible;				//blocking and invincible variables to tell if you take	
														//damage from an attack
	
	public boolean running;								//running variable to tell if character is running or not
	
	public String direction, action, fileType;			//String variables for the variable naming system know
														//what sprite to use
	
	public ArrayList<Hand> shield = new ArrayList<Hand>(), sword = new ArrayList<Hand>();	//equipment arrayLists
	
	public ArrayList<Shoes> shoes = new ArrayList<Shoes>();									//shoe arraylist
	
	public Image img; 									//image object for sprites
	
	public AffineTransform tx;							//tx object to edit image

	//default constructor, instantiates tx
	public Character() {
		tx = AffineTransform.getTranslateInstance(x, y );
	}

	//method to move right
	public void moveRight() {}
	
	//method to move left
	public void moveLeft() {}
	
	//method to move up
	public void moveUp() {}
	
	//method to move down
	public void moveDown() {}
	
	//method to stop moving right
	public void stopMoveRight() {}
	
	//method to stop moving left
	public void stopMoveLeft() {}
	
	//method to stop moving up
	public void stopMoveUp() {}
	
	//method to stop moving down
	public void stopMoveDown() {}
	
	//method to stop moving in all directions
	public void stopMove() {
		xv = 0;
		yv = 0;
	}
	
	//method to start running, making you move faster
	public void run() {}
	
	//method to stop running
	public void stopRun() {}
	
	//method to start shielding
	public void shield() {}
	
	//method to stop shielding
	public void stopShield() {}
	
	//method to drink whatever potion is in hand
	public void drink() {}
	
	//method to attack
	public void attack() {}
	
	//method to take damage with parameter integer damage to tell how much damage should be received,
	//and String direction to tell which direction the attack came from
	public void takeDamage(int damage, String direction) {}
	
	//method to die
	public void die() {}
	
	//method to decrease stamina with parameter double decrease to tell how much stamina is lost
	public void decreaseStamina(double decrease) {
		//if the stamina lost is greater than the current stamina, then set it to zero
		if(stamina - decrease < 0) {
			stamina = 0;
		}else {
			stamina -= decrease;
		}
		//set the stamina regeneration timer to 20, making it so that the character will wait 20 frames
		//before starting to regenerate stamina
		staminaRegenTimer = 20;
	}
	
	//method to regenerate stamina
	public void regenStamina() {
		//checks if the stamina regeneration timer has reached zero before regenerating stamina
		if(staminaRegenTimer > 0) {
			staminaRegenTimer --;
		}
		
		//if the sum of the current stamina and the staminaregeneration variable is greater than
		//the maxstamina variable, then set the current stamina equal to the maximum stamina,
		//otherwise just add as usual
		if(staminaRegenTimer == 0) {
			if(stamina + staminaRegen > maxStamina) {
				stamina = maxStamina;
			}else {
				stamina += staminaRegen;
			}
		}
	}
	
	//update method to calculate variable changes every frame
	public void update() {}
	
	//paint method to draw sprite
	public void paint(Graphics g) {}
	
	//init method to initialize the location of the image
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//getimage method to determine what sprite the image currently is
	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Character.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
