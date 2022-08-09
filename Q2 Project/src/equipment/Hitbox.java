package equipment;
import characters.Character;
import runner.Frame;

import java.util.ArrayList;

//hitbox class, is used for Hand subclasses that do damage, like weapons
public class Hitbox {
	
	public int x, y, w, h;			//dimensions
	
	public int damage;				//damage
	
	public boolean[] hits;			//boolean array hits so that the hitbox can be active for multiple
									//frames but not hit every single frame
		
	public boolean crewmateHit;		//boolean crewmatehit so that enemy hitboxes can be active for multiple
									//frames but not hit every single frame
	
	//constructor with int parameter for damage
	public Hitbox(int damage) {
		this.damage = damage;
	}
	
	//checkcollision method that returns true if the hitbox is colliding with a character's hurtbox 
	public boolean checkCollision(Character c) {
		if(x + w > c.hurtBoxX
		&& x < c.hurtBoxX + c.hurtBoxW
		&& y + h > c.hurtBoxY
		&& y < c.hurtBoxY + c.hurtBoxH) {			
			return true;
		}
		
		return false;
	}
	
	//startHitBox method to check if it collides with any enemies, and if that enemy has been hit before
	public void startHitBox(int x, int y, int w, int h, String direction) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		for(int i = 0; i < Frame.b.enemies.size(); i ++) {
			if(hits[i] == false && checkCollision(Frame.b.enemies.get(i))) {	//if the enemy's index in the hits
				hits[i] = true;													//array is false, then they havent
				Frame.b.enemies.get(i).takeDamage(damage, direction);			//been hit, so they get hit and set
			}																	//the same index in hits to be true
		}
	}
	
	//startcrewmatehitbox to check if the hitbox collides with the player
	public void startCrewmateHitBox(int x, int y, int w, int h, String direction) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		if(crewmateHit == false && checkCollision(Frame.amogus)) {		//if the player has not been hit then
			crewmateHit = true;											//hurt the player and set crewmatehit
			Frame.amogus.takeDamage(damage, direction);					//to true so they aren't hit again
		}
	}
	
	//resethits method to reset all the boolean values
	public void resetHits() {
		for(boolean b : hits) {
			b = false;
		}
	}
	
	//resetcrewmatehit to reset the crewmate hit value
	public void resetCrewmateHit() {
		crewmateHit = false;
	}
}
