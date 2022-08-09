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

//hand class is equipment used by enemies and the player. It could be an interface
public class Hand{
	
	public int x, y, xPos, yPos;			//integers x and y for location, xPos and yPos for position tweaking
	
	public Character character;				//character class for the hand class to be assigned to
	
	public String action, direction, fileType;	//String variables for variable naming system
	
	public String type;							//String type so subclasses can return what type of equipment they are
	
	public int damage, staminaReduction;	//integers damage for how much damage the equipment does,
											//staminareduction for how much stamina it takes to use
	
	public Image img;						//image variable for sprite
	
	public AffineTransform tx;				//tx for image editing

	//constructor with a character parameter 
	public Hand(Character character) {
		this.character = character;
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	//returns type, will be used in times like opening a chest and determining which arraylist the equipment goes in
	public String type() {return type;}
	
	//follow method to match the equipment's x, y and direction with its character's
	public void follow() {
		x = character.x;
		y = character.y;
		direction = character.direction;
	}
	
	//copyaction method to copy the action of its character
	public void copyAction() {
		action = character.action;
	}
	
	//update method to update the variables each frame
	public void update() {}

	//paint method to draw the sprite
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
	}
	
	//init method to choose where the sprite is
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1, 1);
	}

	//getImage method to choose what image the sprite is
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
