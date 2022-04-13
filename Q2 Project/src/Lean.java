import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Lean extends Hand{

	public Lean(Character character) {
		super(character);
		direction = "Right";
		tx = AffineTransform.getTranslateInstance(x, y );
	}
	
	public void update() {
		follow();
		
		switch(direction) {
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
				
		img = getImage("/handSprites/handLeanDrink" + direction + ".gif");
		init(x + xPos, y + yPos);
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		update();
		g2.drawImage(img, tx, null);
	}
}