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

						//subclass of effect, is the slashing effect for the murasama
public class MurasamaSlash extends Effect{
	
	//constructor that calls the parent class's constructor
	public MurasamaSlash(Character character) {
		super(character);
	}
	
	//sets the timer
	public void play() {
		timer = 10;
		direction = character.direction;
	}
	
	//update method uses a switch statement for the direction to adjust the xPos and yPos
	public void update() {
		switch(direction) {
		case "Right":
			xPos = -90;
			yPos = -50;
			break;
			
		case "Left":
			xPos = -90;
			yPos = -50;
			break;
			
		case "Up":
			xPos = -80;
			yPos = -60;
			break;
			
		case "Down":
			xPos = -90;
			yPos = -40;
			break;
		}
		
		img = getImage("/effectSprites/murasamaSlash" + direction + ".gif");
		init(x + xPos,y + yPos);					//adjusted xPos and yPos are added to the x and y
	}												//to make the image appear in the appropriate place
}