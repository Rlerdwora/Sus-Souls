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

import characters.Character;
import characters.Skeleton;
import equipment.Murasama;
import equipment.Shield;
import equipment.Shoes;
import equipment.Sword;
import runner.Frame;

public class Background{
	
	private int x, y;
	public ArrayList<Block> bricks = new ArrayList<Block>();
	public ArrayList<Chest> chests = new ArrayList<Chest>();
	public ArrayList<Bonfire> bonfires = new ArrayList<Bonfire>();
	public ArrayList<Character> enemies = new ArrayList<Character>();

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
			
			bricks.add(new Brick(x, y, 1, "Up", "Left"));
			bricks.add(new Brick(x + 8 * Brick.length, y, 1, "Up", "Right"));
			bricks.add(new Brick(x + 8 * Brick.length, y + 8 * Brick.length, 1, "Down", "Right"));
			bricks.add(new Brick(x, y + 8 * Brick.length, 1, "Down", "Left"));
			bricks.add(new Brick(x + 4 * Brick.length, y, "Up", false));
			
			bonfires.add(new Bonfire(x + 4 * Brick.length, y + 6 * Brick.length, 1));
			
			for(int i = 0; i < 10; i ++) {
				if(i != 6) {
					bricks.add(new Brick(x + 5 * Brick.length, y - (1 + i) * Brick.length, "Right"));
				}
				if(i != 9) {
					bricks.add(new Brick(x + 3 * Brick.length, y - (1 + i) * Brick.length, "Left"));
				}else {
					bricks.add(new Brick(x + 3 * Brick.length, y - (1 + i) * Brick.length, 2, "Up", "Right"));
				}
				bricks.add(new Brick(x + 4 * Brick.length, y - (1 + i) * Brick.length));
			}
			bricks.add(new Brick(x + 5 * Brick.length, y - 7 * Brick.length, "Right", true));
			
			bricks.add(new Brick(x + 6 * Brick.length, y - 8 * Brick.length, "Up"));
			bricks.add(new Brick(x + 6 * Brick.length, y - 6 * Brick.length, "Down"));
			bricks.add(new Brick(x + 7 * Brick.length, y - 6 * Brick.length, 2, "Up", "Right"));
			bricks.add(new Brick(x + 7 * Brick.length, y - 8 * Brick.length, 2, "Down", "Right"));
			bricks.add(new Brick(x + 6 * Brick.length, y - 7 * Brick.length));
			bricks.add(new Brick(x + 7 * Brick.length, y - 7 * Brick.length));
			
			for(int i = 0; i < 9; i ++) {
				if(i != 3 && i != 4 && i != 5) {
					bricks.add(new Brick(x + 7 * Brick.length, y - (i + 3) * Brick.length, "Left"));
				}
				bricks.add(new Brick(x + 13 * Brick.length, y - (i + 3) * Brick.length, "Right"));
			}
			
			for(int i = 0; i < 5; i ++) {
				bricks.add(new Brick(x + (i + 8) * Brick.length, y - 12 * Brick.length, "Up"));
				bricks.add(new Brick(x + (i + 8) * Brick.length, y - 2 * Brick.length, "Down"));
			}
			
			for(int i = 0; i < 9; i ++) {
				for(int j = 0; j < 5; j ++) {
					bricks.add(new Brick(x + (j + 8) * Brick.length, y - (i + 3) * Brick.length));
				}
			}
			
			bricks.add(new Brick(x + 7 * Brick.length, y - 2 * Brick.length, 1, "Down", "Left"));
			bricks.add(new Brick(x + 13 * Brick.length, y - 2 * Brick.length, 1, "Down", "Right"));
			bricks.add(new Brick(x + 7 * Brick.length, y - 12 * Brick.length, 1, "Up", "Left"));
			bricks.add(new Brick(x + 13 * Brick.length, y - 12 * Brick.length, 1, "Up", "Right"));
			
			chests.add(new Chest(x + 12 * Brick.length, y - 7 * Brick.length, "Left", new Shield(Frame.amogus)));
			
			enemies.add(new Skeleton(x + 10 * Brick.length, y - 11 * Brick.length));
			enemies.add(new Skeleton(x + 10 * Brick.length, y - 3 * Brick.length));
			
			bricks.add(new Brick(x + 4 * Brick.length, y - 11 * Brick.length));
			bricks.add(new Brick(x + 3 * Brick.length, y - 11 * Brick.length));
			bricks.add(new Brick(x + 2 * Brick.length, y - 11 * Brick.length));
			bricks.add(new Brick(x + 5 * Brick.length, y - 11 * Brick.length, "Right"));
			
			bricks.add(new Brick(x + 5 * Brick.length, y - 12 * Brick.length, 1, "Up", "Right"));
			bricks.add(new Brick(x + 4 * Brick.length, y - 12 * Brick.length, "Up"));
			bricks.add(new Brick(x + 3 * Brick.length, y - 12 * Brick.length, "Up"));
			bricks.add(new Brick(x + 2 * Brick.length, y - 12 * Brick.length, "Up"));
			bricks.add(new Brick(x + 2 * Brick.length, y - 10 * Brick.length, "Down"));
			bricks.add(new Brick(x + 2 * Brick.length, y - 10 * Brick.length, 2, "Up", "Left"));
			bricks.add(new Brick(x + 2 * Brick.length, y - 12 * Brick.length, 2, "Down", "Left"));
			break;
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		for(Block brick : bricks) {
			brick.paint(g);
		}
		for(Chest c : chests) {
			c.paint(g2);
		}
		for(Character c : enemies) {
			c.paint(g);
			for(Block block : bricks) {
				block.checkCollision(c);
			}
		}
		for(Bonfire b : bonfires) {
			b.paint(g2);
		}
	}
}
