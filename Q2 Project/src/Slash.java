import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Slash{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	private int x, y, xPos, yPos, timer;
	private String direction;
	Amogus amogus;

	public Slash(Amogus amogus) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.amogus = amogus;
		timer = 0;
		img = getImage("/effects/slashRight.gif"); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void follow() {
		 x = amogus.x();
		 y = amogus.y();
	}
	
	public void slash(String direction) {
		timer = 8;
		this.direction = direction;
	}
	
	/* update variables here */
	private void update() {
		follow();
		
		switch(direction) {
		case "Right":
			xPos = 50;
			yPos = 0;
			break;
			
		case "Left":
			xPos = -50;
			yPos = 0;
			break;
			
		case "Up":
			xPos = 0;
			yPos = -45;
			break;
			
		case "Down":
			xPos = 0;
			yPos = 45;
			break;
		}
		
		img = getImage("/effects/slash" + direction + ".gif");
		init(x + xPos, y + yPos);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		
		if(timer > 0) {
			update();
			g2.drawImage(img, tx, null);
			timer --;
		}
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
