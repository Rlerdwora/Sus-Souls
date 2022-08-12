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

				//brick sublcass of block, just changes the folder of images
public class Brick extends Block{

	//tile constructor
	public Brick(int x, int y) {
		super(x, y);
		img = getImage("/objectSprites/brickTile.png");
	}
	
	//wall constructor
	public Brick(int x, int y, String direction) {
		super(x, y, direction);
		img = getImage("/objectSprites/brickWall" + this.direction1 + ".png");
	}
	
	//corner constructor
	public Brick(int x, int y, int id, String vertical, String horizontal) {
		super(x, y, id, vertical, horizontal);
		img = getImage("/objectSprites/brickCorner" + id + this.direction1 + this.direction2 + ".png");
	}
	
	//door constructor
	public Brick(int x, int y, String direction, boolean open) {
		super(x, y, direction, open);
		if(open) {
			img = getImage("/objectSprites/brickDoorOpen" + this.direction1 + ".png");
		}else{
			img = getImage("/objectSprites/brickDoorClosed" + this.direction1 + ".png");
		}
	}
	
	//material is brick
	public String material() {
		return "brick";
	}
}
