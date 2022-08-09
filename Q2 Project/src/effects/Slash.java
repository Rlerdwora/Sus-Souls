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

			//subclass of effect for the sword class
public class Slash extends Effect{

	//constructor that calls the parent class's constructor
	public Slash(Character character) {
		super(character);
	}
	
	//sets the timer and direction
	public void play() {
		timer = 8;
		direction = character.direction;
	}
	
	//update method uses a switch statement for the direction to adjust the xPos and yPos
	public void update() {
		switch(direction) {
		case "Right":
			xPos = 50;
			yPos = 0;
			break;
			
		case "Left":
			xPos = -50;
			yPos = 0;
			break;
			
		case "Up":
			xPos = 0;
			yPos = -53;
			break;
			
		case "Down":
			xPos = 0;
			yPos = 45;
			break;
		}
		
		img = getImage("/effectSprites/slash" + direction + ".gif");
		init(x + xPos,y + yPos);						//adjusted xPos and yPos are added to the x and y
	}													//to make the image appear in the appropriate place
}