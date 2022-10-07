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
import characters.Character;
import equipment.Hand;
import equipment.Shield;
import equipment.Shoes;
import equipment.Sword;
import runner.Frame;
import ui.Camera;

public class Chest{
	
	/* chest class for the chest object
	 * solid object meaning you cannot walk through it
	 * has loot inside of it that you can collect by interacting with the chest
	 */
	
	public int x, y;				//integers x and y for location
	
	public int hurtBoxX, hurtBoxY;	//integers hurtboxX and hurtboxy for hurtbox location
	
	public int width, height;		//hurtbox dimensions
	
	public String direction;		//direction for variable naming system
	
	public boolean opened;			//boolean for if you have opened
	
	public Hand loot;				//arraylist for the loot contained
	
	public Shoes shoeLoot;			//another arraylist for specifically shoes since
									//they are not a subclass of hand
	
	public Image img;				//img for sprite
	
	public AffineTransform tx;		//tx for sprite editing
	
	//hand loot constructor
	public Chest(int x, int y, String direction, Hand loot) {
		this.x = x;
		this.y = y;
		this.loot = loot;
		this.direction = direction;
		opened = false;
		switch(direction) {
		case "Right":
			hurtBoxX = x + 20;
			hurtBoxY = y + 8;
			width = 40;
			height = 66;
			break;
		
		case "Left":
			hurtBoxX = x + 23;
			hurtBoxY = y + 8;
			width = 40;
			height = 66;
			break;
			
		case "Up":
			hurtBoxX = x + 3;
			hurtBoxY = y + 15;
			width = 77;
			height = 50;
			break;
			
		case "Down":
			hurtBoxX = x + 3;
			hurtBoxY = y + 15;
			width = 77;
			height = 50;
			break;
		}
		img = getImage("/objectSprites/chest" + direction +".png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	//shoe loot constructor
	public Chest(int x, int y, String direction, Shoes shoeLoot) {
		this.x = x;
		this.y = y;
		this.shoeLoot = shoeLoot;
		this.direction = direction;
		opened = false;
		switch(direction) {
		case "Right":
			hurtBoxX = x + 20;
			hurtBoxY = y + 8;
			width = 40;
			height = 66;
			break;
		
		case "Left":
			hurtBoxX = x + 23;
			hurtBoxY = y + 8;
			width = 40;
			height = 66;
			break;
			
		case "Up":
			hurtBoxX = x + 3;
			hurtBoxY = y + 15;
			width = 77;
			height = 50;
			break;
			
		case "Down":
			hurtBoxX = x + 3;
			hurtBoxY = y + 15;
			width = 77;
			height = 50;
			break;
		}
		img = getImage("/objectSprites/chest" + direction +".png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	//collision method
	public void checkCollision(Character c) {
		//if the block is not near the amogus, do not check for collision to make the game run faster
		if((Math.abs(x + width/2 - c.hurtBoxX - c.hurtBoxW/2) > 84) && (Math.abs(y + height/2 - c.hurtBoxY - c.hurtBoxH/2) > 84)) {
			return;
		}
		
		//amogus is to the left of wall
		if(c.hurtBoxX + c.hurtBoxW > hurtBoxX  
		&& c.hurtBoxX + c.hurtBoxW < hurtBoxX + width  
		&& c.hurtBoxY + c.hurtBoxH > hurtBoxY  
		&& c.hurtBoxY < hurtBoxY + height 
		&& c.hurtBoxY + c.hurtBoxH - 20 > hurtBoxY  
		&& c.hurtBoxY + 20 < hurtBoxY + height ) {
//			while(c.x + 16 + c.hurtBoxW > hurtBoxX ) {
//				c.x --;
//			}
			c.x = hurtBoxX - c.hurtBoxW - 16;
			return;
		}
		
		//amogus is to the right of wall
		if(c.hurtBoxX < hurtBoxX + width  
		&& c.hurtBoxX > hurtBoxX
		&& c.hurtBoxY + c.hurtBoxH > hurtBoxY  
		&& c.hurtBoxY < hurtBoxY + height  
		&& c.hurtBoxY + c.hurtBoxH - 20 > hurtBoxY  
		&& c.hurtBoxY + 20 < hurtBoxY + height ) {
			while(c.x + 16 < hurtBoxX + width ) {
				c.x ++;
			}
			return;
		}
		
		//amogus is above wall
		if(c.hurtBoxY + c.hurtBoxH > hurtBoxY
		&& c.hurtBoxY + c.hurtBoxH < hurtBoxY + height  
		&& c.hurtBoxX + c.hurtBoxX > hurtBoxX  
		&& c.hurtBoxX < hurtBoxX + width 
		&& c.hurtBoxX + c.hurtBoxW - 20 > x  
		&& c.hurtBoxX + 20 < hurtBoxX + width ) {
			while(c.y + 6 + c.hurtBoxH > hurtBoxY) {
				c.y --;
			}
			return;
		}
		//amogus is below wall
		if(c.hurtBoxY < hurtBoxY + height  
		&& c.hurtBoxY > hurtBoxY
		&& c.hurtBoxX + c.hurtBoxX > hurtBoxX  
		&& c.hurtBoxX < hurtBoxX + width 
		&& c.hurtBoxX + c.hurtBoxW - 20 > hurtBoxX  
		&& c.hurtBoxX + 20 < hurtBoxX + width ) {
			while(c.y + 6 < hurtBoxY + height ) {
				c.y ++;
			}
			return;
		}
	}
	
	//interact method that checks if the chest is in front of and close to the player
	public void interact() {
		switch(Frame.amogus.direction) {
		case "Right":
			if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX  - 10
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW <= hurtBoxX  + 10
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY 
			&& Frame.amogus.hurtBoxY < hurtBoxY + height ) {
				open();
			}
			break;
			
		case "Left":
			if(Frame.amogus.hurtBoxX < hurtBoxX + width  + 10
			&& Frame.amogus.hurtBoxX >= hurtBoxX + width  - 10
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY 
			&& Frame.amogus.hurtBoxY < hurtBoxY + height ) {
				open();
			}
			break;
			
		case "Up":
			if(Frame.amogus.hurtBoxY < hurtBoxY + height  + 10
			&& Frame.amogus.hurtBoxY >= hurtBoxY + height  - 10
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX 
			&& Frame.amogus.hurtBoxX < hurtBoxX + width) {
				open();
			}
			break;
			
		case "Down":
			if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY - 10
			&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH <= hurtBoxY + 10
			&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX
			&& Frame.amogus.hurtBoxX < hurtBoxX + width) {
				open();
			}			
			break;
		}
	}
	
	//open method to give the player the loot
	public void open() {
		if(opened == true) {return;}
		
		opened = true;
		img = getImage("/objectSprites/chestOpen" + direction + ".png");
		if(shoeLoot == null) {
			switch(loot.type()) {
			case "sword":
				Frame.amogus.sword.add(loot);
				break;
				
			case "shield":
				Frame.amogus.shield.add(loot);
				break;
			}
		}else {
			((Amogus)Frame.amogus).shoes.add(shoeLoot);
		}
	}
	
	//paint method
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		init(x, y);
		switch(direction) {
		case "Right":
			hurtBoxX = x + 20;
			hurtBoxY = y + 8;
			break;
		
		case "Left":
			hurtBoxX = x + 23;
			hurtBoxY = y + 8;
			break;
			
		case "Up":
			hurtBoxX = x + 3;
			hurtBoxY = y + 15;
			break;
			
		case "Down":
			hurtBoxX = x + 3;
			hurtBoxY = y + 15;
			break;
		}
		g2.drawImage(img, tx, null);
		g.drawRect(hurtBoxX, hurtBoxY, width, height);
	}

	//initializes sprite location
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//initializes sprite image
	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Chest.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
