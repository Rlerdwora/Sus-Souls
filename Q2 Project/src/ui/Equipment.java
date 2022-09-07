package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

import objects.Background;
import runner.Frame;

//the equipment class is what displays what equipment you are carrying in the bottom left corner
public class Equipment{

	private ArrayList<Img> frames = new ArrayList<Img>();										//frames
	
	private Img shield, sword, lean = new Img(10 + 65, 520 + 42, "/uiSprites/leanIcon.png");	//images to go in frames
	
	//default constructor initializes the empty frames
	public Equipment() {
		frames.add(new Img(10,520, "/uiSprites/iconFrame1.png"));
		frames.add(new Img(10 + 65, 520 + 42, "/uiSprites/iconFrame2.png"));
		frames.add(new Img(10 + 130, 520, "/uiSprites/iconFrame2.png"));
	}
	
	//update method to fill the frames with the equipped equipment after exiting the item menu
	public void update() {
		if(Frame.amogus.shield.get(Frame.amogus.shieldSelect) != null){
			shield = new Img(10, 520, "/uiSprites/" + Frame.amogus.shield.get(Frame.amogus.shieldSelect).toString() + "Icon.png");
		}else {
			shield = null;
		}
		
		if(Frame.amogus.sword.get(Frame.amogus.weaponSelect) != null){
			sword = new Img(140, 520, "/uiSprites/" + Frame.amogus.sword.get(Frame.amogus.weaponSelect).toString() + "Icon.png");
		}else {
			sword = null;
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		if(shield != null) {
			shield.paint(g2);
		}
		
		if(sword != null) {
			sword.paint(g2);
		}
		
		lean.paint(g2);
		
		for(Img img : frames) {
			img.paint(g);
		}
	}
}
