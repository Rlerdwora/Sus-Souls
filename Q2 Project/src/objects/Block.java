package objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import characters.Amogus;
import characters.Skeleton;
import characters.Character;
import runner.Frame;
import ui.Camera;

public class Block{
	
	//image related variables
	public int x, y;
	public static int length = 84;
	public boolean wall, door;
	public String direction1, direction2;
	public Image img; 	
	public AffineTransform tx;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		wall = false;
		door = false;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Block(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		direction1 = direction;
		direction2 = "";
		wall = true;
		door = false;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Block(int x, int y, String vertical, String horizontal) {
		this.x = x;
		this.y = y;
		direction1 = vertical;
		direction2 = horizontal;
		wall = true;
		door = false;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Block(int x, int y, String direction, boolean open) {
		this.x = x;
		this.y = y;
		direction1 = direction;
		direction2 = "";
		wall = !open;
		door = true;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public String material() {return "";}
	
	public void checkCollision() {
		if(wall == false) {
			return;
		}
		
		if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x() 
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW < x + length + Camera.x() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() 
		&& Frame.amogus.hurtBoxY < y + length + Camera.y()
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 20 > y + Camera.y() 
		&& Frame.amogus.hurtBoxY + 20 < y + length + Camera.y()) {
			while(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x()) {
				Frame.amogus.x --;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 20;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
		
		//amogus is to the right of wall
		if(Frame.amogus.hurtBoxX < x + length + Camera.x() 
		&& Frame.amogus.hurtBoxX > x + Camera.x() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() 
		&& Frame.amogus.hurtBoxY < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 20 > y + Camera.y() 
		&& Frame.amogus.hurtBoxY + 20 < y + length + Camera.y()) {
			while(Frame.amogus.hurtBoxX < x + length + Camera.x()) {
				Frame.amogus.x ++;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 20;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
				
		//amogus is above wall
		if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > x + Camera.x() 
		&& Frame.amogus.hurtBoxX < x + length + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 20 > x + Camera.x() 
		&& Frame.amogus.hurtBoxX + 20 < x + length + Camera.x()) {
			while(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y()) {
				Frame.amogus.y --;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 20;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
		//amogus is below wall
		if(Frame.amogus.hurtBoxY < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxY > y + Camera.y() 
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > x + Camera.x() 
		&& Frame.amogus.hurtBoxX < x + length + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 20 > x + Camera.x() 
		&& Frame.amogus.hurtBoxX + 20 < x + length + Camera.x()) {
			while(Frame.amogus.hurtBoxY < y + length + Camera.y()) {
				Frame.amogus.y ++;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 20;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
	}
	
	public void checkCollision(Character c) {
		if(wall == false) {
			return;
		}
		
		if(c.hurtBoxX + c.hurtBoxW > x  
		&& c.hurtBoxX + c.hurtBoxW < x + length  
		&& c.hurtBoxY + c.hurtBoxH > y  
		&& c.hurtBoxY < y + length 
		&& c.hurtBoxY + c.hurtBoxH - 20 > y  
		&& c.hurtBoxY + 20 < y + length ) {
			while(c.hurtBoxX + c.hurtBoxW > x ) {
				c.x --;
				c.hurtBoxX = c.x + 20;
				c.hurtBoxY = c.y + 6;
			}
		}
		
		//amogus is to the right of wall
		if(c.hurtBoxX < x + length  
		&& c.hurtBoxX > x  
		&& c.hurtBoxY + c.hurtBoxH > y  
		&& c.hurtBoxY < y + length  
		&& c.hurtBoxY + c.hurtBoxH - 20 > y  
		&& c.hurtBoxY + 20 < y + length ) {
			while(c.hurtBoxX < x + length ) {
				c.x ++;
				c.hurtBoxX = c.x + 20;
				c.hurtBoxY = c.y + 6;
			}
		}
				
		//amogus is above wall
		if(c.hurtBoxY + c.hurtBoxH > y  
		&& c.hurtBoxY + c.hurtBoxH < y + length  
		&& c.hurtBoxX + c.hurtBoxX > x  
		&& c.hurtBoxX < x + length 
		&& c.hurtBoxX + c.hurtBoxW - 20 > x  
		&& c.hurtBoxX + 20 < x + length ) {
			while(c.hurtBoxY + c.hurtBoxH > y ) {
				c.y --;
				c.hurtBoxX = c.x + 20;
				c.hurtBoxY = c.y + 6;
			}
		}
		//amogus is below wall
		if(c.hurtBoxY < y + length  
		&& c.hurtBoxY > y  
		&& c.hurtBoxX + c.hurtBoxX > x  
		&& c.hurtBoxX < x + length 
		&& c.hurtBoxX + c.hurtBoxW - 20 > x  
		&& c.hurtBoxX + 20 < x + length ) {
			while(c.hurtBoxY < y + length ) {
				c.y ++;
				c.hurtBoxX = c.x + 20;
				c.hurtBoxY = c.y + 6;
			}
		}
	}
	
	public void interact() {
		if(door == false) {return;}
		
		switch(Frame.amogus.direction) {
		case "Right":
			if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x() - 20
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW <= x + Camera.x() + 20
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y()
			&& Frame.amogus.hurtBoxY < y + length + Camera.y()) {
				if(wall) {
					wall = false;
					img = getImage("/objectSprites/" + material() + "DoorOpen" + direction1 + ".png");
				}else {
					wall = true;
					img = getImage("/objectSprites/" + material() + "DoorClosed" + direction1 + ".png");
				}
			}
			break;
			
		case "Left":
			if(Frame.amogus.hurtBoxX < x + length + Camera.x() + 20
			&& Frame.amogus.hurtBoxX >= x + length + Camera.x() - 20
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y()
			&& Frame.amogus.hurtBoxY < y + length + Camera.y()) {
				if(wall) {
					wall = false;
					img = getImage("/objectSprites/" + material() + "DoorOpen" + direction1 + ".png");
				}else {
					wall = true;
					img = getImage("/objectSprites/" + material() + "DoorClosed" + direction1 + ".png");
				}
			}
			break;
			
		case "Up":
			if(Frame.amogus.hurtBoxY < y + length + Camera.y() + 20
			&& Frame.amogus.hurtBoxY >= y + length + Camera.y() - 20
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x()
			&& Frame.amogus.hurtBoxX < x + length + Camera.x()) {
				if(wall) {
					wall = false;
					img = getImage("/objectSprites/" + material() + "DoorOpen" + direction1 + ".png");
				}else {
					wall = true;
					img = getImage("/objectSprites/" + material() + "DoorClosed" + direction1 + ".png");
				}
			}
			break;
			
		case "Down":
			if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() - 20
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH <= y + Camera.y() + 20
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x()
			&& Frame.amogus.hurtBoxX < x + length + Camera.x()) {
				if(wall) {
					wall = false;
					img = getImage("/objectSprites/" + material() + "DoorOpen" + direction1 + ".png");
				}else {
					wall = true;
					img = getImage("/objectSprites/" + material() + "DoorClosed" + direction1 + ".png");
				}
			}
			break;
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		init(x + Camera.x(), y + Camera.y());
		g2.drawImage(img, tx, null);
		checkCollision();
	}

	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Block.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
