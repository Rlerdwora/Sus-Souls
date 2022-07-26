package objects;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void buildLevel() {
		String path = ".\\src\\levels\\";
		File text = new File(path);
		try {
			try (Scanner scnnr = new Scanner(text)) {
				
				//level text files have dimensions at the top
				int xDimension = scnnr.nextInt();
				int yDimension = scnnr.nextInt();
				
				int xPlacement = 0, yPlacement = 0;
				//uses the dimensions to place the read id's in the correct spot
				for(int i = 0; i < xDimension; i ++) {
					for(int j = 0; j < yDimension; j ++) {
						/* Block Id Table
						 * 
						 * 001: tile
						 * 002: right wall
						 * 003: left wall
						 * 004: up wall
						 * 005: down wall
						 * 006: inward top right corner
						 * 007: inward top left corner
						 * 008: inward bottom right corner
						 * 009: inward bottem left corner
						 * 010: outward top right corner
						 * 011: outward top left corner
						 * 012: outward bottom right corner
						 * 013: outward bottem left corner
						 * 014: right door
						 * 015: left door
						 * 016: up door
						 * 017: down door
						 * 018: bonfire
						 * 019: crewmate spawnpoint
						 * 020 - 023: chest with shield
						 * 024 - 27: chest with sword
						 * 028 - 31: chest with drip
						 * 032 - 35: chest with murasama
						 */
						xPlacement = 0;
						int blockId = scnnr.nextInt();
						
						switch(blockId) {
						case 1: //tile
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							break;
							
						case 2: //rght wall
							bricks.add(new Brick(xPlacement *84, yPlacement * 84, "Right"));
							break;
							
						case 3: //left wall
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Left"));
							break;
							
						case 4: //up wall
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Up"));
							break;
							
						case 5: //down wall
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Down"));
							break;
							
						case 6: //inward top right corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 1, "Up", "Right"));
							break;
							
						case 7: //inward top left corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 1, "Up", "Left"));
							break;
							
						case 8: //inward bottom right corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 1, "Down", "Right"));
							break;
							
						case 9: //inward bottom left corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 1, "Down", "Left"));
							break;
							
						case 10: //outward top right corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 2, "Up", "Right"));
							break;
							
						case 11: //outward top left corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 2, "Up", "Left"));
							break;
							
						case 12: //outward bottom right corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 2, "Down", "Right"));
							break;
							
						case 13: //outward bottom left corner
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, 2, "Down", "Left"));
							break;
						
						case 14: //right door
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Right", false));
							break;
						
						case 15: //left door
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Left", false));
							break;
							
						case 16: //up door
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Up", false));
							break;
							
						case 17: //down door
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84, "Down", false));
							break;
							
						case 18: //bonfire
							bonfires.add(new Bonfire(xPlacement * 84, yPlacement * 84, 1));
							break;
							
						case 19: //crewmate spawnpoint
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							Frame.amogus.x = xPlacement * 84;
							Frame.amogus.y = yPlacement * 84;
							break;
						
						case 20: //right chest with shield
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Right", new Shield(Frame.amogus)));
							break;
							
						case 21: //left chest with shield
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Left", new Shield(Frame.amogus)));
							break;
							
						case 22: //up chest with shield
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Up", new Shield(Frame.amogus)));
							break;
							
						case 23: //down chest with shield
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Down", new Shield(Frame.amogus)));
							break;
							
						case 24: //right chest with sword
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Right", new Sword(Frame.amogus)));
							break;
							
						case 25: //left chest with sword
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Left", new Sword(Frame.amogus)));
							break;
							
						case 26: //up chest with sword
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Up", new Sword(Frame.amogus)));
							break;
							
						case 27: //down chest with sword
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Down", new Sword(Frame.amogus)));
							break;
							
						case 28: //right chest with drip
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Right", new Shoes(Frame.amogus)));
							break;
							
						case 29: //left chest with drip
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Left", new Shoes(Frame.amogus)));
							break;
							
						case 30: //up chest with drip
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Up", new Shoes(Frame.amogus)));
							break;
							
						case 31: //down chest with drip
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Down", new Shoes(Frame.amogus)));
							break;
							
						case 32: //right chest with murasama
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Right", new Murasama(Frame.amogus)));
							break;
							
						case 33: //left chest with murasama
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Left", new Murasama(Frame.amogus)));
							break;
							
						case 34: //up chest with murasama
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Up", new Murasama(Frame.amogus)));
							break;
							
						case 35: //down chest with murasama
							bricks.add(new Brick(xPlacement * 84, yPlacement * 84));
							chests.add(new Chest(xPlacement * 84, yPlacement * 84, "Down", new Murasama(Frame.amogus)));
							break;
						}
					}
					yPlacement ++;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
