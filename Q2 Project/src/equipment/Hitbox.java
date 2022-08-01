package equipment;
import characters.Character;
import runner.Frame;

import java.util.ArrayList;

public class Hitbox {
	
	public int x, y, w, h, damage;
	public boolean[] hits;
	
	public Hitbox(int damage) {
		this.damage = damage;
	}
	
	public boolean checkCollision(Character c) {
//		if(c.hurtBoxX < x) {
//			if(c.hurtBoxX + c.hurtBoxW - x > 0 && c.hurtBoxY - y - h > 0)
//				return true;
//		}else {
//			if(x + w - c.hurtBoxX > 0 && y - c.hurtBoxY - c.hurtBoxH > 0)
//				return true;
//		}
		
		if(x + w > c.hurtBoxX
		&& x < c.hurtBoxX + c.hurtBoxW
		&& y + h > c.hurtBoxY
		&& y < c.hurtBoxY + c.hurtBoxH) {			
			return true;
		}
		
		return false;
	}
	
	public void checkCollision(int x, int y, int w, int h, String direction) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		for(int i = 0; i < Frame.b.enemies.size(); i ++) {
			if(hits[i] == false && checkCollision(Frame.b.enemies.get(i))) {
				hits[i] = true;
				Frame.b.enemies.get(i).takeDamage(damage, direction);
			}
		}
	}
	
	public void resetHits() {
		for(boolean b : hits) {
			b = false;
		}
	}
}
