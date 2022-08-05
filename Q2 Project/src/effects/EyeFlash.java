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

public class EyeFlash extends Effect{
	
	public EyeFlash(Character character) {
		super(character);
		img = getImage("/effectSprites/eyeFlash.gif");
	}
	
	public void follow() {
		 x = character.hurtBoxX + character.hurtBoxW/2;
		 y = character.hurtBoxY + character.hurtBoxH/4;
	}
	
	public void play() {
		timer = 8;
	}
	
	public void update() {
		init(x - 120/2, y - 45/2);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;		
		follow();
		if(timer > 0) {
			update();
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
			g2.drawImage(img, tx, null);
			timer --;
		}
	}
}