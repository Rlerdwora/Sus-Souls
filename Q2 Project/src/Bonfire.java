import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Bonfire{
	
	private int x, y;
	private Image img;
	private AffineTransform tx;

	public Bonfire(int x, int y, boolean kindled) {
		this.x = x;
		this.y = y;
		if(kindled == true) {
			img = getImage("/miscSprites/bonfireKindled.gif");
		}else {
			img = getImage("/miscSprites/bonfire.png");
		}
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x + Camera.x(), y + Camera.y());
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
			URL imageURL = Bonfire.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
