package objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

import characters.Amogus;
import runner.Frame;
import ui.Camera;

public class Brick extends Block{

	public Brick(int x, int y) {
		super(x, y);
		img = getImage("/objectSprites/brickTile.png");
	}
	
	public Brick(int x, int y, String direction) {
		super(x, y, direction);
		img = getImage("/objectSprites/brickWall" + this.direction1 + ".png");
	}
	
	public Brick(int x, int y, String vertical, String horizontal) {
		super(x, y, vertical, horizontal);
		img = getImage("/objectSprites/brickCorner" + this.direction1 + this.direction2 + ".png");
	}
	
	public Brick(int x, int y, String direction, boolean open) {
		super(x, y, direction, open);
		if(open) {
			img = getImage("/objectSprites/brickDoorOpen" + this.direction1 + ".png");
		}else{
			img = getImage("/objectSprites/brickDoorClosed" + this.direction1 + ".png");
		}
	}
	
	public String material() {
		return "brick";
	}
}
