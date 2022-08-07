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
import effects.EyeFlash;
import equipment.Hand;
import equipment.Shield;
import equipment.Shoes;
import equipment.Sword;
import runner.Frame;
import ui.Camera;

//enemy class is a subclass of the character class made as a blueprint for enemies, like the skeleton
public class Enemy extends Character{
	
	public int moveTimer, randomTimer, attackTimer;	//movetimer is the duration of random movements
													//randomTimer is the duration between random movements
													//attackTimer is the duration between random attacks
	
	public Effect effect;							//effect object is for the red eye flash shown before
													//attacking to telegraph
	
	//default constructor
	public Enemy() {
		super();
		effect = new EyeFlash(this);				//instantiates the effect to be the eyeflash
		attackTimer = 15 + (int)Math.random() * 40;	//instantiates the attacktimer
	}
	
	//moverandom method to randomly move
		public void moveRandom() {
			if(randomTimer > 0) {			//nested if-statement-timer
				randomTimer --;				//randomtimer ticking down
				if(randomTimer == 0) {		//when randomtiemr is equal to 0, set the moveTimer to a random number		
					moveTimer = (int) (Math.random()*15) + 1;	//and move in a random direction
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
			
			if(moveTimer > 0) {		//nested-if-statement-timer for the duration of the random movement
				moveTimer --;		//movetimer ticking down
				if(moveTimer == 0) {//when the movetimer ends the enemy stops moving and the randomtimer is reset
					stopMove();
					randomTimer = (int)(Math.random() * 20) + 40;
				}
			}
		}
	
	//battle method for battle ai, randomly attacking
	public void battle() {
		switch(attackTimer) {	//switch statement for attacktimer to know what to do when a certain point is reached
		case 10:				//when attacktimer is equal to 10 then start the eye flash
			effect.play();
			break;
			
		case 0:					//when attacktimer is equal to 0 then reset the attacktimer and call the attack method
			attack();
			attackTimer = 30 + ((Sword)sword.get(weaponSelect)).attackTimer + (int)(Math.random() * 40);
			break;
		}
		attackTimer --;			//attacktimer ticking down
	}
	
	//detect method to check if the player is close enough to the enemy so that it can start closing in on the amogus
	public boolean detect() {
		//instantiate two variables xDiff and yDiff to find the distance between the amogus and enemy
		int xDiff = Math.abs((Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW/2) - (hurtBoxX  + hurtBoxW/2));
		int yDiff = Math.abs((Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH/2) - (hurtBoxY  + hurtBoxH/2));
		//use pythagorean theorem to see if the distance between the amogus and enemy
		//is less than or equal to the detectrange, if so return true otherwise return false
		if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= detectRange) {
			return true;
		}else {
			return false;
		}
	}
	
	//follow method, will be called if the detect method returns true
	public void follow() {
		//instantiate two variables xDiff and yDiff to find the distance between the amogus and enemy
		int xDiff = (Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW/2) - (hurtBoxX  + hurtBoxW/2);
		int yDiff = (Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH/2) - (hurtBoxY  + hurtBoxH/2);
		//if amogus is outside of the combat range, follow it
		if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) > combatRange) {
			//if the amogus is outside of the combat range x and to the right, move right
			if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW/2 - combatRange/2 > hurtBoxX  + hurtBoxW/2){
				moveRight();
			//if the amogus is outside of the combat range x and to the left, move left
			}else if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW/2 + combatRange/2 < hurtBoxX  + hurtBoxW/2){
				moveLeft();
			//if the amogus is within the combat range x, stop moving left and right
			}else {
				stopMoveRight();
				stopMoveLeft();
			}
			
			//if the amogus is outside of the combat range y and below, move down
			if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH/2 - combatRange/2 > hurtBoxY  + hurtBoxH/2){
				moveDown();
			//if the amogus is outside of the combat range y and above, move up
			}else if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH/2 + combatRange/2 < hurtBoxY  + hurtBoxH/2){
				moveUp();
			//if the amogus is within the combat range y, stop moving up and down
			}else {
				stopMoveDown();
				stopMoveUp();
			}
		//if amogus is fully within combat range, engage in combat
		}else {
			
			//if else chains to face towards the amogus
			if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW/2 - combatRange/2 > hurtBoxX  + hurtBoxW/2){
				direction = "Right";
			}else if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW/2 + combatRange/2 < hurtBoxX  + hurtBoxW/2){
				direction = "Left";
			}
			if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH/2 - combatRange/2 > hurtBoxY  + hurtBoxH/2){
				direction = "Down";
			}else if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH/2 + combatRange/2 < hurtBoxY  + hurtBoxH/2){
				direction = "Up";
			}
			
			//when attacking stop moving
			stopMove();
			//call battle method
			battle();
		}
	}
	
}