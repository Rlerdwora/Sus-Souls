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

		//shoes class, is armor
public class Shoes{
	
	public int x, y;								//integers x and y for location
	
	public Character character;						//character class for it to attach to
	
	public String action, direction, fileType;		//String variables for variable naming system
	
	public int defense;								//defense integer for how much damage reduction it has
	
	public Image img;								//image variable for sprite
	
	public AffineTransform tx;						//tx variable to edit image
	
	
	//toString method for identification
	public String toString() {
		return "dripShoes";
	}

	//constructor with character parameter to assign
	public Shoes(Character character) {
		this.character = character;
		tx = AffineTransform.getTranslateInstance(x, y );
		defense = 8;
	}
	
	//follow method to match the x, y, direction, action, and filetype of its character
	public void follow() {
		x = character.x;
		y = character.y;
		direction = character.direction;
		action = character.action;
		fileType = character.fileType;
	}
	
	//update method that updates the variables every frame
	public void update() {
		follow();
		img = getImage("/dripShoesSprites/dripShoes" + action + direction + fileType);
		init(x, y);
	}

	//paint method to draw the image
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
	}
	
	//init method to adjust the location of the image
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//getimage method to make the image the correct sprite
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
