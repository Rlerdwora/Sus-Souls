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

			//lean is equipment, so it is a subclass of hand
public class Lean extends Hand{

	//constructor that initializes the direction and tx
	public Lean(Character character) {
		super(character);
		direction = "Right";
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	//overrides the update method
	public void update() {
		follow();
		
		switch(direction) {						//switch statement for direction to adjust xPos and yPos
		case "Right":
			xPos = 21;
			yPos = 0;
			break;
			
		case "Left":
			xPos = -21;
			yPos = 0;
			break;
			
			
		case "Up":
			xPos = 6;
			yPos = -24;
			break;
			
		case "Down":
			xPos = 0;
			yPos = 10;
			break;
		}
		
		//when the direction variable changes the sprite changes as well
		img = getImage("/equipmentSprites/handLeanDrink" + direction + ".gif");
		//the xPos and yPos are to adjust the x and y in the sprite
		init(x + xPos, y + yPos);
	}
	
	//paint method to call update and draw the sprite
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
	}
}