import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Equipment{

	private ArrayList<Img> imgs = new ArrayList<Img>();
	
	public Equipment(int x, int y) {
		imgs.add(new Img(x, y, "/UI/shieldIcon.png", 1f));
		imgs.add(new Img(x + 65, y + 42, "/UI/leanIcon.png", 1f));
		imgs.add(new Img(x + 130, y, "/UI/swordIcon.png", 1f));
		imgs.add(new Img(x, y, "/UI/iconFrame1.png", 1f));
		imgs.add(new Img(x + 65, y + 42, "/UI/iconFrame2.png", 1f));
		imgs.add(new Img(x + 65, y - 42, "/UI/iconFrame1.png", 1f));
		imgs.add(new Img(x + 130, y, "/UI/iconFrame2.png", 1f));
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		for(Img img : imgs) {
			img.paint(g);
		}
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
