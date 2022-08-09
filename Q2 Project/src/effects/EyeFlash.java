package effects;
import java.awt.AlphaComposite;
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

						//subclass of effect
public class EyeFlash extends Effect{
	
	public EyeFlash(Character character) {
		super(character);
		img = getImage("/effectSprites/eyeFlash.gif");	//instantiates the parent class's img variable
	}													//to be an eyeFlash	
	
	//follow method has been overridden to make the image follow the middle of a character and not the top left corner
	public void follow() {
		 x = character.hurtBoxX + character.hurtBoxW/2;
		 y = character.hurtBoxY + character.hurtBoxH/4;
	}
	
	//sets the timer
	public void play() {
		timer = 8;
	}
	
	//the top left corner of the eyeFlash gif is positioned in the center of its character, so
	//this call of init makes the center of the eyeFlash gif in the center of its character
	public void update() {
		init(x - 120/2, y - 45/2);
	}
	
	//overridden paint method to add an additional line of code to make the eyeFlash gif a little transparent
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		follow();
		if(timer > 0) {
			update();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));	//transparency line here
			g2.drawImage(img, tx, null);
			timer --;
		}
	}
}