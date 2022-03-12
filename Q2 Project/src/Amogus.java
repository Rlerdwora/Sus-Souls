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
	private int x, y, xv, yv, hurtTimer, attackTimer, rollTimer, health, 
	stamina, weaponSelect, width, height, HBX, HBY, HBW, HBH;
	private boolean invincible, control, running;
	private String direction, action, fileType;
	private Hand leftHand, rightHand;

	public Amogus(int x, int y) {
		this.x = x;
		this.y = y;
		control = true;
		hurtTimer = 0;
		attackTimer = 0;
		weaponSelect = 0;
		action = "Stand";
		direction = "Down";
		fileType = ".png";
		
		img = getImage("/amogusSprites/amogus" + action + direction + fileType); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(this.x, this.y); 				//initialize the location of the image
									//use your variables
	}
	
	public void moveRight() {
		if(control == true) {
			if(running == false) {
				action = "Walk";
			}else {
				action = "Run";
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
			if(running == false) {
				action = "Walk";
			}else {
				action = "Run";
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
			if(running == false) {
				action = "Walk";
			}else {
				action = "Run";
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
			if(running == false) {
				action = "Walk";
			}else {
				action = "Run";
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
	
	public void attack(Enemy e) {
		if(attackTimer <= 0) {
			if(e.x() < HBX + HBW && e.x() + e.width() > HBX
			&& e.y() < HBY + HBH && e.y() + e.height() > HBY) {
				e.takeDamage();
			}
			attackTimer = 15;
		}
	}
	
	public void shield() {
		
	}
	
	public void roll() {
		rollTimer = 40;
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
		attackTimer = 0;
		hurtTimer = 0;
		rollTimer = 0;
		xv = 0;
		yv = 0;
	}

	/* update variables here */
	private void update() {
		if(running == false) {
			x += xv;
			y += yv;
		}else {
			x += 2 * xv;
			y += 2 * yv;
		}
		
		if(xv == 0 && yv == 0) {
			action = "Stand";
			fileType = ".png";
		}
		
		if(yv == 0 && xv < 0) {
			xv = -4;
		}else if(yv == 0 && xv > 0) {
			xv = 4;
		}else if(yv < 0 && xv == 0) {
			yv = -4;
		}else if(yv > 0 && xv == 0) {
			yv = 4;
		}else if(yv < 0 && xv < 0) {
			yv = -3;
			xv = -3;
		}else if(yv < 0 && xv > 0) {
			yv = -3;
			xv = 3;
		}else if(yv > 0 && xv < 0) {
			yv = 3;
			xv = -3;
		}else if(yv > 0 && xv > 0) {
			yv = 3;
			xv = 3;
		}
		
		if(rollTimer > 0) {
			if(rollTimer == 1) {
				control = true;
			}else {
				control = false;
			}
			xv = 0;
			yv = 0;
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
			rollTimer --;
		}
		
		img = getImage("/amogusSprites/amogus" + action + direction + fileType);
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
		tx.scale(1, 1);
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
