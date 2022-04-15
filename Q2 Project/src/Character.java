import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Character{
	
	//image related variables
	public int x, y, xv, yv, hurtBoxX, hurtBoxY, hurtBoxW, hurtBoxH, hitBoxX, hitBoxY, hitBoxW, hitBoxH, health,
	weaponSelect, shieldSelect, hurtTimer, attackTimer, deathTimer, detectRange;
	public boolean blocking, invincible;
	public String direction, action, fileType;
	public ArrayList<Hand> shield = new ArrayList<Hand>(), sword = new ArrayList<Hand>();
	public ArrayList<Effect> effect = new ArrayList<Effect>();
	public Image img; 	
	public AffineTransform tx;

	public Character() {
		tx = AffineTransform.getTranslateInstance(x, y );
	}

	public void moveRight() {}
	
	public void moveLeft() {}
	
	public void moveUp() {}
	
	public void moveDown() {}
	
	public void stopMoveRight() {}
	
	public void stopMoveLeft() {}
	
	public void stopMoveUp() {}
	
	public void stopMoveDown() {}
	
	public void stopMove() {
		xv = 0;
		yv = 0;
	}
	
	public void run() {}
	
	public void stopRun() {}
	
	public void shield() {}
	
	public void stopShield() {}
	
	public void drink() {}
	
	public void attack() {}
	
	public void update() {}
	
	public void takeDamage() {}
	
	public void die() {}
	
	public int x() {return x;}
	
	public int y() {return y;}
	
	public int hurtBoxX() {return hurtBoxX;}
	
	public int hurtBoxY() {return hurtBoxY;}
	
	public int hurtBoxW() {return hurtBoxW;}
	
	public int hurtBoxH() {return hurtBoxH;}
	
	public String action() {return action;}
	
	public String direction() {return direction;}
	
	public void setX(int x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	
	public void paint(Graphics g) {}
	
	public boolean detect() {
		int xDiff = Math.abs((Frame.amogus.hurtBoxX() + Frame.amogus.hurtBoxW()/2) - (hurtBoxX + Camera.x() + hurtBoxW/2));
		int yDiff = Math.abs((Frame.amogus.hurtBoxY() + Frame.amogus.hurtBoxH()/2) - (hurtBoxY + Camera.y() + hurtBoxH/2));
		if(Math.sqrt(xDiff * xDiff + yDiff * yDiff) <= detectRange) {
			return true;
		}else {
			return false;
		}
	}
	
	public void follow(Character focus) {
		if(focus.hurtBoxX() > hurtBoxX + hurtBoxW) {
			moveRight();
		}else if(hurtBoxX() + hurtBoxW() < hurtBoxX) {
			moveLeft();
		}else {
			xv = 0;
		}
		
	}
	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

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
