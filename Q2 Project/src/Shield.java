import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Shield{
	
	//image related variables
	private Image img; 	
	private AffineTransform tx;
	private int x, y, xPos, yPos;
	private String direction, action, fileType;
	private Amogus a;

	public Shield(Amogus a) {
		this.a = a;
		direction = "Right";
		action = "Stand";
		fileType = ".png";
		img = getImage("/handSprites/handShield" + action + direction + fileType); //load the image for Tree
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y); 				//initialize the location of the image
									//use your variables
	}
	
	public void follow() {
		x = a.x();
		y = a.y();
		direction = a.direction();

		if(a.action().equals("Stand")) {
			fileType = ".png";
		}else {
			fileType = ".gif";
		}
	}
	
	public void copyAction() {
		action = a.action();
	}
	
	public void block() {
		
	}
	
	public void stopBlock() {
		
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	/* update variables here */
	private void update() {
		follow();
		copyAction();
		
		switch(direction) {
			case "Right":
				if(action.equals("Run")) {
				xPos = 0;
				yPos = 0;
			}else if(action.equals("Walk") || action.equals("Stand")) {
				xPos = 10;
				yPos = 0;
			}
			break;
		
		case "Left":
			if(action.equals("Run")) {
				xPos = 0;
				yPos = 0;
			}else if(action.equals("Walk") || action.equals("Stand")) {
				xPos = -10;
				yPos = 0;
			}
			break;
				
		case "Up":
			if(action.equals("Run")) {
				xPos = -20;
				yPos = 0;
			}else if(action.equals("Walk") || action.equals("Stand")) {
				xPos = -20;
				yPos = 0;
			}
			break;
			
		case "Down":
			if(action.equals("Run")) {
				xPos = 20;
				yPos = 0;
			}else if(action.equals("Walk") || action.equals("Stand")) {
				xPos = 20;
				yPos = 0;
			}
			break;
		}
		
		img = getImage("/handSprites/handShield" + action + direction + fileType);
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
			URL imageURL = Shield.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
