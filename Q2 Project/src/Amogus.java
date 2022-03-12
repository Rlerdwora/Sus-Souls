import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Amogus{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	private int x, y, xv, yv, hurtTimer, rollTimer, health, weaponSelect, width, height, HBX, HBY, HBW, HBH;
	private boolean invincible, control;
	private String direction, action, fileType;
	private Hand leftHand, rightHand;

	public Amogus(int x, int y) {
		img = getImage("/imgs/Amogus" + action + direction + fileType); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(this.x, this.y); 				//initialize the location of the image
									//use your variables
	}
	
	public void moveRight() {
		if(control == true) {
			if(yv == 0) {
				xv = 4;
			}else {
				xv = 3;
			}
		}
	}
	
	public void moveLeft() {
		if(control == true) {
			if(yv == 0) {
				xv = -4;
			}else {
				xv = -3;
			}
		}
	}
	
	public void moveUp() {
		if(control == true) {
			if(xv == 0) {
				yv = -4;
			}else {
				yv = -3;
			}
		}
	}
	
	public void moveDown() {
		if(control == true) {
			if(xv == 0) {
				yv = 4;
			}else {
				yv = 3;
			}
		}
	}
	
	public void attack(Enemy e) {
		if(e.x() < x + width && e.x() + e.width() > x
		&& e.y() < y + height && e.y() + e.height() > y) {
			e.takeDamage();
		}
	}
	
	public void shield() {
		
	}
	
	public void parry() {
		
	}
	
	public void roll() {
		
	}
	
	public void takeDamage(int damage, String direction) {
		if(invincible == false) {
			if(damage >= health) {
				die();
			}else {
				health -= damage;
				hurtTimer = 20;
				
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
		}
	}
	
	public void die() {
		control = false;
	}

	
	/* update variables here */
	private void update() {

		
		img = getImage("/imgs/Amogus" + action + "direction" + fileType);
		init(x,y);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		
		//call update to update the actualy picture location
		update();
		
		
		
		
		g2.drawImage(img, tx, null);
		
		

	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(2.7, 2.5);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Amogus.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
