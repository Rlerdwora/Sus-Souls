package objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

public class Background{
	
	private int x, y;
	public ArrayList<Brick> bricks = new ArrayList<Brick>();
	public ArrayList<Chest> chests = new ArrayList<Chest>();

	public Background(int x, int y, int id) {
		this.x = x;
		this.y = y;
		switch(id) {
		
		case 1:
			for(int i = 0; i < 7; i ++) {
				for(int j = 0; j < 7; j ++) {
					bricks.add(new Brick(x + Brick.length + i * Brick.length, y + Brick.length + j * Brick.length));
				}
			}
			
			for(int i = 0; i < 7; i ++) {
				bricks.add(new Brick(x, y + (i + 1) * Brick.length, "Left"));
				bricks.add(new Brick(x + 8 * Brick.length, y + (i + 1) * Brick.length, "Right"));
				bricks.add(new Brick(x + (i + 1) * Brick.length, y + 8 * Brick.length, "Down"));
				if(i != 3) {
					bricks.add(new Brick(x + (i + 1) * Brick.length, y, "Up"));
				}
			}
			
			bricks.add(new Brick(x, y, "Up", "Left"));
			bricks.add(new Brick(x + 8 * Brick.length, y, "Up", "Right"));
			bricks.add(new Brick(x + 8 * Brick.length, y + 8 * Brick.length, "Down", "Right"));
			bricks.add(new Brick(x, y + 8 * Brick.length, "Down", "Left"));
			bricks.add(new Brick(x + 4 * Brick.length, y, "Up", false));
			
			break;
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(Brick brick : bricks) {
			brick.paint(g);
		}
	}
}
