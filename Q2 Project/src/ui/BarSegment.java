package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

//barsegment is what makes up a bar
public class BarSegment{
	
	public int x, y;				//location of the bar segment
	
	public Image img;				//image for sprite
	
	public AffineTransform tx;		//tx to edit sprite

	//constrcutor with parameters x and y 
	public BarSegment(int x, int y, String position) {
		this.x = x;
		this.y = y;
		img = getImage("/uiSprites/" + position + "Bracket.png");
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	//returns length of the bar, used to attach multiple barsegments together cleanly
	public int length() {return 30 * 3/4;}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		init(x,y);
		g2.drawImage(img, tx, null);
	}

	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.75, .75);
	}

	public Image getImage(String path) {
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
