import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class UI{
	
	//image related variables
	private Image leftHand, rightHand, down; 	
	private AffineTransform tx1, tx2, tx3;

	public UI(int x, int y) {
		leftHand = getImage("/UI/shieldIcon.png");
		rightHand = getImage("/UI/swordIcon.png");
		down = getImage("/UI/leanIcon.png");
		tx1 = AffineTransform.getTranslateInstance(10, 520 );
		tx2 = AffineTransform.getTranslateInstance(120, 520 );
		tx3 = AffineTransform.getTranslateInstance(65, 570 );
		init1(10, 520);
		init2(120, 520);
		init3(65, 570);
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		
		g2.drawImage(leftHand, tx1, null);
		g2.drawImage(rightHand, tx2, null);
		g2.drawImage(down, tx3, null);
	}

	
	private void init1(double a, double b) {
		tx1.setToTranslation(a, b);
		tx1.scale(1, 1);
	}
	
	private void init2(double a, double b) {
		tx2.setToTranslation(a, b);
		tx2.scale(1, 1);
	}
	
	private void init3(double a, double b) {
		tx3.setToTranslation(a, b);
		tx3.scale(1, 1);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = UI.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
