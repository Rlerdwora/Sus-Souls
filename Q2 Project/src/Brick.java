import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

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
		
		//amogus is to the left of wall
		if(Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW > x + Camera.x() && Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW < x + length + Camera.x() && Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() && Frame.amogus.hurtBoxY < y + length + Camera.y()
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 3 > y + Camera.y() && Frame.amogus.hurtBoxY + 3 < y + length + Camera.y()) {
			if(Frame.amogus.xv > 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.x = Frame.amogus.x - Frame.amogus.xv;
				}else {
					Frame.amogus.x = Frame.amogus.x - 2 * Frame.amogus.xv;
				}
			}
		}
		
		//amogus is to the right of wall
		if(Frame.amogus.hurtBoxX < x + length + Camera.x() && Frame.amogus.hurtBoxX > x + Camera.x() && Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() && Frame.amogus.hurtBoxY < y + length + Camera.y() 
		&& Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH - 3 > y + Camera.y() && Frame.amogus.hurtBoxY + 3 < y + length + Camera.y()) {
			if(Frame.amogus.xv < 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.x = Frame.amogus.x - Frame.amogus.xv;
				}else {
					Frame.amogus.x = Frame.amogus.x - 2 * Frame.amogus.xv;
				}
			}
		}
		
		//amogus is above wall
		if(Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH > y + Camera.y() && Frame.amogus.hurtBoxY + Frame.amogus.hurtBoxH < y + length + Camera.y() && Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > x + Camera.x() && Frame.amogus.hurtBoxX < x + length + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 3 > x + Camera.x() && Frame.amogus.hurtBoxX + 3 < x + length + Camera.x()) {
			if(Frame.amogus.yv > 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.y = Frame.amogus.y - Frame.amogus.yv;
				}else {
					Frame.amogus.y = Frame.amogus.y - 2 * Frame.amogus.yv;
				}
			}
		}
		
		//amogus is above wall
		if(Frame.amogus.hurtBoxY < y + length + Camera.y() && Frame.amogus.hurtBoxY > y + Camera.y() && Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxX > x + Camera.x() && Frame.amogus.hurtBoxX < x + length + Camera.x()
		&& Frame.amogus.hurtBoxX + Frame.amogus.hurtBoxW - 3 > x + Camera.x() && Frame.amogus.hurtBoxX + 3 < x + length + Camera.x()) {
			if(Frame.amogus.yv < 0) {
				if(((Amogus)Frame.amogus).running() == false) {
					Frame.amogus.y = Frame.amogus.y - Frame.amogus.yv;
				}else {
					Frame.amogus.y = Frame.amogus.y - 2 * Frame.amogus.yv;
				}
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		init(x + Camera.x(), y + Camera.y());
		g2.drawImage(img, tx, null);
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
