package ui;
import characters.Character;
import objects.Block;
import objects.Bonfire;
import objects.Chest;
import runner.Frame;

//camera class to keep the player on the center of the screen
public class Camera {
	
	public Character character;
	
	//constructor with character parameter to focus on
	public Camera(Character focus) {
		character = focus;
	}
	
	//focus method detects if the character is not on the center of the screen and moves everything if so
	public void focus() {
		while(character.x != 395) {					//x centering
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
		
		while(character.y != 270) {					//y centering		
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
}
