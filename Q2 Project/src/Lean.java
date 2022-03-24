import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Lean{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	private int x, y, xPos, yPos;
	private String direction, action;
	private Amogus a;

	public Lean(Amogus a) {
		this.a = a;
		direction = "Right";
		img = getImage("/handSprites/handShieldDrink" + direction + ".gif"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void follow() {
		x = a.x();
		y = a.y();
		direction = a.direction();
	}
	
	public void copyAction() {
		action = a.action();
	}
	
	/* update variables here */
	private void update() {
		follow();
		
		switch(direction) {
		case "Right":
			xPos = 21;
			yPos = 0;
			break;
			
		case "Left":
			xPos = -21;
			yPos = 0;
			break;
			
			
		case "Up":
			xPos = 6;
			yPos = -24;
			break;
			
		case "Down":
			xPos = 0;
			yPos = 10;
			break;
		}
				
		img = getImage("/handSprites/handLeanDrink" + direction + ".gif");
		init(x + xPos, y + yPos);
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
			URL imageURL = Lean.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
