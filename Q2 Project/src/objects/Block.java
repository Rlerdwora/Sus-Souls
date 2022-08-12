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

//block class is an obstacle that appears on the map that you cannot move through
public class Block{

	public int x, y;						//integers x and y for location
	
	public static int length = 84;			//integer length is static because every block is 84 pixels wide	
	
	public boolean wall, door;				//there are multiple types of blocks so there is a boolean for wall
											//if it is a solid block or door if it is a door
	
	public String direction1, direction2;	//direction1 is used for which way a block is facing,
											//and direction2 is used for corners, defining the 
											//second direction. (Corners have the image format
											//vertical direction + horizontal direction while walls
											//only use one direction)
	
	public Image img; 						//image variable for sprite
	
	public AffineTransform tx;				//affinetransform vairable for editing sprite

	//Multiple constructors for multiple types of blocks
	//tile block, decoration and can be moved through
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		wall = false;
		door = false;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	//wall block, is solid and can't be moved through
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
	
	//corner block, solid, paramter id 1 for inward corner, 2 for outward corner
	public Block(int x, int y, int id, String vertical, String horizontal) {
		this.x = x;
		this.y = y;
		direction1 = vertical;
		direction2 = horizontal;
		wall = true;
		door = false;
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	//door block, solidness can be toggled on and off
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
	
	public void checkCollision(Character c) {
		//if the block is not near the amogus, do not check for collision to make the game run faster
		if(wall == false || ((Math.abs(x + length/2 - c.hurtBoxX - c.hurtBoxW/2) > 84) && (Math.abs(y + length/2 - c.hurtBoxY - c.hurtBoxH/2) > 84))) {
			return;
		}
		
		//amogus is to the left of wall
		if(c.hurtBoxX + c.hurtBoxW > x  
		&& c.hurtBoxX + c.hurtBoxW < x + length  
		&& c.hurtBoxY + c.hurtBoxH > y  
		&& c.hurtBoxY < y + length 
		&& c.hurtBoxY + c.hurtBoxH - 20 > y  
		&& c.hurtBoxY + 20 < y + length ) {
			while(c.x + 16 + c.hurtBoxW > x ) {
				c.x --;
			}
			return;
		}
		
		//amogus is to the right of wall
		if(c.hurtBoxX < x + length  
		&& c.hurtBoxX > x  
		&& c.hurtBoxY + c.hurtBoxH > y  
		&& c.hurtBoxY < y + length  
		&& c.hurtBoxY + c.hurtBoxH - 20 > y  
		&& c.hurtBoxY + 20 < y + length ) {
			while(c.x + 16 < x + length ) {
				c.x ++;
			}
			return;
		}
				
		//amogus is above wall
		if(c.hurtBoxY + c.hurtBoxH > y  
		&& c.hurtBoxY + c.hurtBoxH < y + length  
		&& c.hurtBoxX + c.hurtBoxX > x  
		&& c.hurtBoxX < x + length 
		&& c.hurtBoxX + c.hurtBoxW - 20 > x  
		&& c.hurtBoxX + 20 < x + length ) {
			while(c.y + 6 + c.hurtBoxH > y) {
				c.y --;
			}
			return;
		}
		//amogus is below wall
		if(c.hurtBoxY < y + length  
		&& c.hurtBoxY > y  
		&& c.hurtBoxX + c.hurtBoxX > x  
		&& c.hurtBoxX < x + length 
		&& c.hurtBoxX + c.hurtBoxW - 20 > x  
		&& c.hurtBoxX + 20 < x + length ) {
			while(c.y + 6 < y + length ) {
				c.y ++;
			}
			return;
		}
	}
	
	//interact method to check if the amogus can interact with the block
	public void interact() {
		//so far only the door is interactable so if the block isn't a door stop the method
		if(door == false) {return;}
		
		//switch statement checks if the block is in front of and close enough to the amogus
		switch(Frame.amogus.direction) {
		case "Right":
			if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x  - 20
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW <= x  + 20
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y 
			&& Frame.amogus.hurtBoxY < y + length ) {
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
			if(Frame.amogus.hurtBoxX < x + length  + 20
			&& Frame.amogus.hurtBoxX >= x + length  - 20
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y 
			&& Frame.amogus.hurtBoxY < y + length ) {
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
			if(Frame.amogus.hurtBoxY < y + length  + 20
			&& Frame.amogus.hurtBoxY >= y + length  - 20
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x 
			&& Frame.amogus.hurtBoxX < x + length ) {
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
			if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y  - 20
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH <= y  + 20
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x 
			&& Frame.amogus.hurtBoxX < x + length ) {
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

	//paint method draws the image
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		init(x , y );
		g2.drawImage(img, tx, null);
	}

	//init method initializes the location of the sprite
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//getimage method to choose the image for the sprite
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
