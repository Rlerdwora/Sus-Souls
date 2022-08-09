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

//unused

public class LeanAura extends Effect{
	
	public LeanAura(Character character) {
		super(character);
		img = getImage("/effectSprites/leanAura.gif");
	}
	
	/* update variables here */
	public void update() {
		follow();
		init(x, y);
	}
}