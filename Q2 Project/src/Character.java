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
	public int x, y, xv, yv, width, height, HBx, HBy, HBw, HBh, health, weaponSelect, shieldSelect,
			   hurtTimer, attackTimer, deathTimer;
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
	
	public void run() {}
	
	public void stopRun() {}
	
	public void shield() {}
	
	public void stopShield() {}
	
	public void drink() {}
	
	public void attack() {}
	
	public void update() {}
	
	public int x() {return x;}
	
	public int y() {return y;}
	
	public String action() {return action;}
	
	public String direction() {return direction;}
	
	public void setX(int x) {this.x = x;}
	
	public void setY(int y) {this.y = y;}
	
	public void paint(Graphics g) {}
	
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
