package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class BarSegment{
	
	private int x, y;
	private String position;
	private Image img;
	private AffineTransform tx;

	public BarSegment(int x, int y, String position) {
		this.x = x;
		this.y = y;
		this.position = position;
		img = getImage("/uiSprites/" + position + "Bracket.png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public int length() {return 30 * 3/4;}
	
	public int x() {return x;}
	
	public void setX(int x) {this.x = x;}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		init(x,y);
		g2.drawImage(img, tx, null);
	}

	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = BarSegment.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
