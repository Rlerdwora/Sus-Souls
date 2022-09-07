package objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import ui.Camera;

public class Bonfire{
		
	public int x, y;			//ints x and y for placement
	
	public int kindleLevel;		//kindlelevel for how much the bonfire has been kindled
	
	public int leanRegen;		//leanregen for how much lean the bonfire regenerates
	
	public Image img;			//img for sprite
	
	public AffineTransform tx;	//tx for editing sprite

	//constructor with parameters x and y for placement, and kindlelevel for kindlelevel
	public Bonfire(int x, int y, int kindleLevel) {
		this.x = x;
		this.y = y;
		this.kindleLevel = kindleLevel;
		if(kindleLevel > 0) {
			img = getImage("/objectSprites/bonfireKindled.gif");	//bonfire is lit only when kindlelevel
		}else {														//is greater than one
			img = getImage("/objectSprites/bonfire.png");
		}
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	//paint method
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		init(x, y);
		g2.drawImage(img, tx, null);
	}

	//kindle method, unused, would be used when the player kindles a bonfire and using one point of suspicion
	public void kindle() {
		if(kindleLevel == 0) {
			img = getImage("/objectSprites/bonfireKindled.gif");
		}
		kindleLevel ++;
	}
	
	//init method to initialize location of sprite
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//getimage method to choose image for sprite
	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Bonfire.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
