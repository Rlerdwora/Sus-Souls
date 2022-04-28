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

	public Background(int x, int y, int id) {
		this.x = x;
		this.y = y;
		switch(id) {
		
		case 1:
			for(int i = 0; i < 10; i ++) {
				for(int j = 0; j < 10; j ++) {
					if(i == 0) {
						if(j == 0) {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Up", "Left"));
						}else if(j == 9) {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Down", "Left"));
						}else {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Left"));
						}
					}else if(i == 9) {
						if(j == 0) {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Up", "Right"));
						}else if(j == 9) {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Down", "Right"));
						}else {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Right"));
						}
					}else if(j == 0){
						if(i != 0 && i != 9) {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Up"));
						}
					}else if(j == 9) {
						if(i != 0 && i != 9) {
							bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length, "Down"));
						}
					}else{
						bricks.add(new Brick(x + i * Brick.length, y + j * Brick.length));
					}
				}
			}
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
