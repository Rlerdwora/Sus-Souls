package equipment;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import characters.Character;
import effects.Effect;

public class Shoes{
	
	//image related variables
	public int x, y;
	public Character character;
	public String action, direction, fileType, toString;
	public Image img;
	public AffineTransform tx;

	public Shoes(Character character) {
		this.character = character;
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	public String toString() {
		return toString;
	}
	
	public void follow() {
		x = character.x;
		y = character.y;
		direction = character.direction;
		action = character.action;
		fileType = character.fileType;
	}
	
	public void update() {
		follow();
		img = getImage("/dripShoesSprites/dripShoes" + action + direction + fileType);
		init(x, y);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
	}
	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	public Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Hand.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
