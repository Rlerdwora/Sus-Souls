import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Enemy{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	private int x, y, xv, yv, xPos, yPos, HBX, HBY, width, height, health;
	private String direction, action, fileType;
	
	public Enemy(int x, int y) {
		img = getImage("/imgs/bg.png"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public int x() {
		return x;
	}
	
	public int y() {
		return y;
	}
	
	public int xPos() {
		return xPos;
	}
	
	public int yPos() {
		return yPos;
	}
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public void moveRight() {
		
	}

	public void moveLeft() {
		
	}

	public void moveUp() {
	
	}

	public void moveDown() {
	
	}
	
	public void attack() {
		
	}

	public void takeDamage() {
		
	}
	
	public void die() {
		
	}
	
	/* update variables here */
	private void update() {

		
		
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
			URL imageURL = Enemy.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
