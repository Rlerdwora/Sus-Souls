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
	
	public int x;
	public int y;
	public int kindleLevel;
	public int leanRegen;
	public boolean kindled;
	public Image img;
	public AffineTransform tx;

	public Bonfire(int x, int y, int kindleLevel) {
		this.x = x;
		this.y = y;
		this.kindleLevel = kindleLevel;
		if(kindleLevel > 0) {
			img = getImage("/objectSprites/bonfireKindled.gif");
		}else {
			img = getImage("/objectSprites/bonfire.png");
		}
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x + Camera.x(), y + Camera.y());
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		init(x + Camera.x(), y + Camera.y());
		g2.drawImage(img, tx, null);
	}

	public void kindle() {
		if(kindleLevel == 0) {
			img = getImage("/objectSprites/bonfireKindled.gif");
		}
		if(kindleLevel < 4) {
			kindleLevel ++;
		}
	}
	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

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
