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

public class Shield extends Hand{

	public Shield(Character character) {
		super(character);
		direction = "Right";
		action = "Stand";
		fileType = ".png";
		type = "shield";
		tx = AffineTransform.getTranslateInstance(x, y );
		staminaReduction = 5;
	}
	
	public String toString() {
		return "shield";
	}
	
	public void update() {
		super.follow();
		
		switch(direction) {
			case "Right":
				switch(action) {
				case "Run":
					xPos = 0;
					yPos = 0;
					break;
				
				case "Walk":
					xPos = 10;
					yPos = 0;
					break;
					
				case "Stand":
					xPos = 10;
					yPos = 0;
					break;
					
				case "Block":
					xPos = 25;
					yPos = 0;
					break;
					
				case "Attack":
					xPos = 5;
					yPos = 0;
					break;
				}
				
				break;
		
			case "Left":
				switch(action) {
				case "Run":
					xPos = 0;
					yPos = 0;
					break;
				
				case "Walk":
					xPos = -10;
					yPos = 0;
					break;
					
				case "Stand":
					xPos = -10;
					yPos = 0;
					break;
					
				case "Block":
					xPos = -25;
					yPos = 0;
					break;
					
				case "Attack":
					xPos = -5;
					yPos = 0;
					break;
				}
				break;
					
			case "Up":			
				switch(action) {
				case "Run":
					xPos = -20;
					yPos = 0;
					break;
				
				case "Walk":
					xPos = -20;
					yPos = 0;
					break;
					
				case "Stand":
					xPos = -20;
					yPos = 0;
					break;
					
				case "Block":
					xPos = -5;
					yPos = -10;
					break;
					
				case "Attack":
					xPos = -20;
					yPos = 0;
					break;
				}
				break;
				
			case "Down":
				switch(action) {
				case "Run":
					xPos = 20;
					yPos = 0;
					break;
				
				case "Walk":
					xPos = 20;
					yPos = 0;
					break;
					
				case "Stand":
					xPos = 20;
					yPos = 0;
					break;
					
				case "Block":
					xPos = 5;
					yPos = 10;
					break;
					
				case "Attack":
					xPos = 20;
					yPos = 0;
					break;
				}
				break;
			}
		
		img = getImage("/equipmentSprites/handShield" + action + direction + fileType);
		init(x + xPos, y + yPos);
	}
}