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
import equipment.Hand;
import equipment.Shield;
import equipment.Sword;
import runner.Frame;
import ui.Camera;

public class Chest{
	
	//image related variables
	private int x, y, hurtBoxX, hurtBoxY, width, height;
	private String direction;
	private boolean wall, opened;
	private Hand loot;
	private Image img;
	private AffineTransform tx;

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
	
	public Chest(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		opened = false;
		this.direction = direction;
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
	
	public void checkCollision() {
		//amogus is to the left of wall
		if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW < hurtBoxX + width + Camera.x() && Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY < hurtBoxY + height + Camera.y()
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 3 > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY + 3 < hurtBoxY + height + Camera.y()) {
			if(Frame.amogus.xv > 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.x = Frame.amogus.x - Frame.amogus.xv;
				}else {
					Frame.amogus.x = Frame.amogus.x - 2 * Frame.amogus.xv;
				}
			}
		}
		
		//amogus is to the right of wall
		if(Frame.amogus.hurtBoxX < hurtBoxX + width + Camera.x() && Frame.amogus.hurtBoxX > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY < hurtBoxY + height + Camera.y() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 3 > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY + 3 < hurtBoxY + height + Camera.y()) {
			if(Frame.amogus.xv < 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.x = Frame.amogus.x - Frame.amogus.xv;
				}else {
					Frame.amogus.x = Frame.amogus.x - 2 * Frame.amogus.xv;
				}
			}
		}
		
		//amogus is above wall
		if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH < hurtBoxY + height + Camera.y() && Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX < hurtBoxX + width + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 3 > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX + 3 < hurtBoxX + width + Camera.x()) {
			if(Frame.amogus.yv > 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.y = Frame.amogus.y - Frame.amogus.yv;
				}else {
					Frame.amogus.y = Frame.amogus.y - 2 * Frame.amogus.yv;
				}
			}
		}
		
		//amogus is above wall
		if(Frame.amogus.hurtBoxY < hurtBoxY + height + Camera.y() && Frame.amogus.hurtBoxY > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX < hurtBoxX + width + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 3 > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX + 3 < hurtBoxX + width + Camera.x()) {
			if(Frame.amogus.yv < 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.y = Frame.amogus.y - Frame.amogus.yv;
				}else {
					Frame.amogus.y = Frame.amogus.y - 2 * Frame.amogus.yv;
				}
			}
		}
	}
	
	public void open() {
		if(opened == false) {
			boolean openable = false;
			
			switch(direction) {
			case "Right":
				if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX < hurtBoxX + 10 + width + Camera.x()
				&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY < hurtBoxY + height + Camera.y()) {
					openable = true;
				}
				break;
			
			case "Left":
				if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX - 10 + Camera.x() && Frame.amogus.hurtBoxX < hurtBoxX + width + Camera.x()
				&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY < hurtBoxY + height + Camera.y()) {
					openable = true;
				}
				break;
				
			case "Up":
				if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX < hurtBoxX + width + Camera.x()
				&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY - 10 + Camera.y() && Frame.amogus.hurtBoxY < hurtBoxY + height + Camera.y()) {
					openable = true;
				}
				break;
				
			case "Down":
				if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > hurtBoxX + Camera.x() && Frame.amogus.hurtBoxX < hurtBoxX + width + Camera.x()
				&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > hurtBoxY + Camera.y() && Frame.amogus.hurtBoxY < hurtBoxY + 10 + height + Camera.y()) {
					openable = true;
				}
				break;
			}
			
			if(openable) {
				switch(loot.toString()) {
				case"shield":
					Frame.amogus.shield.add((Shield) loot);
					break;
					
				case"sword":
					Frame.amogus.sword.add((Sword) loot);
					break;
				}
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		checkCollision();
		init(x + Camera.x(), y + Camera.y());
		g2.drawImage(img, tx, null);
		g.drawRect(hurtBoxX + Camera.x(), hurtBoxY + Camera.y(), width, height);
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
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
