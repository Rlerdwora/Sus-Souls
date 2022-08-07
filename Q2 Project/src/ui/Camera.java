package ui;
import characters.Character;
import objects.Block;
import objects.Bonfire;
import objects.Chest;
import runner.Frame;

public class Camera {
	
	private Character character;
	private static int x, y;
	
	public Camera(Character focus) {
		character = focus;
	}
	
	public void focus() {
		while(character.x != 395) {
			if(character.x < 395) {
				character.x ++;
				for(Block b : Frame.b.bricks) {
					b.x ++;
				}
				for(Chest c : Frame.b.chests) {
					c.x ++;
				}
				for(Bonfire b : Frame.b.bonfires) {
					b.x ++;
				}
				for(Character c : Frame.b.enemies) {
					c.x ++;
				}
			}else if(character.x > 395){
				character.x --;
				for(Block b : Frame.b.bricks) {
					b.x --;
				}
				for(Chest c : Frame.b.chests) {
					c.x --;
				}
				for(Bonfire b : Frame.b.bonfires) {
					b.x --;
				}
				for(Character c : Frame.b.enemies) {
					c.x --;
				}
			}
		}
		
		while(character.y != 270) {
			if(character.y < 270) {
				character.y ++;
				for(Block b : Frame.b.bricks) {
					b.y ++;
				}
				for(Chest c : Frame.b.chests) {
					c.y ++;
				}
				for(Bonfire b : Frame.b.bonfires) {
					b.y ++;
				}
				for(Character c : Frame.b.enemies) {
					c.y ++;
				}
			}else if(character.y > 270){
				character.y --;
				for(Block b : Frame.b.bricks) {
					b.y --;
				}
				for(Chest c : Frame.b.chests) {
					c.y --;
				}
				for(Bonfire b : Frame.b.bonfires) {
					b.y --;
				}
				for(Character c : Frame.b.enemies) {
					c.y --;
				}
			}
		}
	}
	
	public static int x() {return x;}

	public static int y() {return y;}
}
