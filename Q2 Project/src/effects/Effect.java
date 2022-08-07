package effects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import characters.Character;
import objects.Background;

public class Effect{
	
	//image related variables
	public Image img; 	
	public AffineTransform tx;
	public int x, y, xPos, yPos, timer;
	public String direction;
	public Character character;
	
	public Effect(Character character) {
		this.character = character;
		timer = 0;
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	public void follow() {
		 x = character.x;
		 y = character.y;
		 direction = character.direction;
	}
	
	public void play() {}
	
	public void stop() {
		timer = 0;
	}
	
	public void update() {}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		follow();
		if(timer > 0) {
			update();
			g2.drawImage(img, tx, null);
			timer --;
		}
	}
	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	public Image getImage(String path) {
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