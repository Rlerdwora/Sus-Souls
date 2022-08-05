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

public class Enemy extends Character{
	
	public int moveTimer, randomTimer, attackTimer;
	public Effect effect;
	
	public Enemy() {
		super();
		effect = new EyeFlash(this);
		attackTimer = 15 + (int)Math.random() * 40;
	}
	
	public void battle() {
		System.out.println(attackTimer);
		
		switch(attackTimer) {
		case 10:
			effect.play();
			break;
			
		case 0:
			attack();
			attackTimer = 30 + ((Sword)sword.get(weaponSelect)).attackTimer + (int)(Math.random() * 40);
			break;
		}
		attackTimer --;
	}
	
	public boolean detect() {
		int xDiff = Math.abs((Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2) - (hurtBoxX  + hurtBoxW/2));
		int yDiff = Math.abs((Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2) - (hurtBoxY  + hurtBoxH/2));
		if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= detectRange) {
			return true;
		}else {
			return false;
		}
	}
	
	public void follow() {
		int xDiff = (Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2) - (hurtBoxX  + hurtBoxW/2);
		int yDiff = (Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2) - (hurtBoxY  + hurtBoxH/2);
		if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) > combatRange) {
			if(Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2 - combatRange/2 > hurtBoxX  + hurtBoxW/2){
				moveRight();
			}else if(Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2 + combatRange/2 < hurtBoxX  + hurtBoxW/2){
				moveLeft();
			}else {
				stopMoveRight();
				stopMoveLeft();
			}
			
			if(Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2 - combatRange/2 > hurtBoxY  + hurtBoxH/2){
				moveDown();
			}else if(Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2 + combatRange/2 < hurtBoxY  + hurtBoxH/2){
				moveUp();
			}else {
				stopMoveDown();
				stopMoveUp();
			}
		}else {
			if(Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2 - combatRange/2 > hurtBoxX  + hurtBoxW/2){
				direction = "Right";
			}else if(Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2 + combatRange/2 < hurtBoxX  + hurtBoxW/2){
				direction = "Left";
			}
			
			if(Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2 - combatRange/2 > hurtBoxY  + hurtBoxH/2){
				direction = "Down";
			}else if(Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2 + combatRange/2 < hurtBoxY  + hurtBoxH/2){
				direction = "Up";
			}
			stopMove();
			battle();
		}
	}
	
}