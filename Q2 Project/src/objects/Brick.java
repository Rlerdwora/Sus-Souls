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
import runner.Frame;
import ui.Camera;

public class Brick{
	
	//image related variables
	private int x, y;
	public static int length = 84;
	private boolean wall;
	private Image img; 	
	private AffineTransform tx;

	public Brick(int x, int y) {
		this.x = x;
		this.y = y;
		wall = false;
		img = getImage("/objectSprites/brickTile.png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Brick(int x, int y, String side) {
		this.x = x;
		this.y = y;
		wall = true;
		img = getImage("/objectSprites/brickWall" + side + ".png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Brick(int x, int y, String vertical, String horizontal) {
		this.x = x;
		this.y = y;
		wall = true;
		img = getImage("/objectSprites/brickCorner" + vertical + horizontal + ".png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public void checkCollision() {
		if(wall == false) {
			return;
		}
		
		if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x() 
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW < x + length + Camera.x() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() 
		&& Frame.amogus.hurtBoxY < y + length + Camera.y()
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 10 > y + Camera.y() 
		&& Frame.amogus.hurtBoxY + 10 < y + length + Camera.y()) {
			while(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x()) {
				Frame.amogus.x --;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 16;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
		
		//amogus is to the right of wall
		if(Frame.amogus.hurtBoxX < x + length + Camera.x() 
		&& Frame.amogus.hurtBoxX > x + Camera.x() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() 
		&& Frame.amogus.hurtBoxY < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 10 > y + Camera.y() 
		&& Frame.amogus.hurtBoxY + 10 < y + length + Camera.y()) {
			while(Frame.amogus.hurtBoxX < x + length + Camera.x()) {
				Frame.amogus.x ++;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 16;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
				
		//amogus is above wall
		if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > x + Camera.x() 
		&& Frame.amogus.hurtBoxX < x + length + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 10 > x + Camera.x() 
		&& Frame.amogus.hurtBoxX + 10 < x + length + Camera.x()) {
			while(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y()) {
				Frame.amogus.y --;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 16;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
				
		//amogus is below wall
		if(Frame.amogus.hurtBoxY < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxY > y + Camera.y() 
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > x + Camera.x() 
		&& Frame.amogus.hurtBoxX < x + length + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 10 > x + Camera.x() 
		&& Frame.amogus.hurtBoxX + 10 < x + length + Camera.x()) {
			while(Frame.amogus.hurtBoxY < y + length + Camera.y()) {
				Frame.amogus.y ++;
				Frame.amogus.hurtBoxX = Frame.amogus.x + 16;
				Frame.amogus.hurtBoxY = Frame.amogus.y + 6;
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		init(x + Camera.x(), y + Camera.y());
		g2.drawImage(img, tx, null);
		checkCollision();
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Brick.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
