package ui;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Img{
	
	public int x, y;
	public double scaleX, scaleY;
	public float alpha;
	public Image img;
	public AffineTransform tx;

	public Img(int x, int y, String url) {
		this.x = x;
		this.y = y;
		scaleX = 1;
		scaleY = 1;
		alpha = 1f;
		img = getImage(url);
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Img(int x, int y, double scaleX, double scaleY, String url) {
		this.x = x;
		this.y = y;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		alpha = 1f;
		img = getImage(url);
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Img(int x, int y, String url, float alpha) {
		this.x = x;
		this.y = y;
		this.alpha = alpha;
		scaleX = 1;
		scaleY = 1;
		img = getImage(url);
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}
	
	public Img(int x, int y, double scaleX, double scaleY, String url, float alpha) {
		this.x = x;
		this.y = y;
		this.alpha = alpha;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		img = getImage(url);
		tx = AffineTransform.getTranslateInstance(x, y );
		init(x, y);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		init(x,y);
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2.drawImage(img, tx, null);
	}
	
	public void setImg(String path) {
		img = getImage(path);
	}

	public void fadeIn() {
		if(alpha < 1f - 0.01f) {
			alpha += 0.01f;
		}
	}
	
	public void fadeOut() {
		if(alpha > 0f + 0.01f) {
			alpha -= 0.01f;
		}
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleX, scaleY);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Img.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
