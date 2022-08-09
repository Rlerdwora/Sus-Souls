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
	
	public Image img; 					//Image object for the sprite
	public AffineTransform tx;			//tx for image editing
	public int x, y, xPos, yPos;		//int x and y for positioning, xPos and yPos for tweaking
	public int timer;					//timer int for how long to show an effect
	public String direction;			//direction String for varible naming system
	public Character character;			//character object to follow
	
	public Effect(Character character) {	//constructor with a character parameter to know what to follow
		this.character = character;
		timer = 0;
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	//follow method that aligns the x and y with the character's x and y and matches their directions
	public void follow() {
		 x = character.x;
		 y = character.y;
		 direction = character.direction;
	}
	
	//play method that will set the timer
	public void play() {}
	
	//stop method that will stop playing the animation of the effect
	public void stop() {
		timer = 0;
	}
	
	//update method to update variables every frame
	public void update() {}
	
	//paint method to draw the sprite
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		follow();
		if(timer > 0) {						//the sprite is only visible when the timer is greater than zero
			update();
			g2.drawImage(img, tx, null);
			timer --;						//timer ticking down
		}
	}
	
	//init method to set the x and y of the sprite
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}
	
	//getImage method to set the correct sprite to the image
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