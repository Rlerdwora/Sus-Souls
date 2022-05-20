package ui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;

import characters.Amogus;
import equipment.Hand;
import equipment.Shoes;
import runner.Frame;

public class ItemMenu{

	public Img menu = new Img(230, 100, "/uiSprites/itemMenu.png");
	public Img swordFrame = new Img(320, 217, "/uiSprites/iconFrame1.png");
	public Img shieldFrame = new Img(320, 316, "/uiSprites/iconFrame1.png");
	public Img shoeFrame = new Img(320, 409, "/uiSprites/iconFrame1.png");
	public Img swordIcon, shieldIcon, shoeIcon, itemCursor = new Img(320, 214, "/uiSprites/itemCursor.png");
	public ArrayList<Hand> swords, shields;
	public ArrayList<Shoes> shoes;
	public int typeSelect = 0, itemSelect = 0;
	public boolean selecting = false;
	
	public ItemMenu() {
		swords = Frame.amogus.sword;
		shields = Frame.amogus.shield;
		shoes = ((Amogus)Frame.amogus).shoes;
	}
	
	public void update() {
		swords = Frame.amogus.sword;
		shields = Frame.amogus.shield;
		shoes = ((Amogus)Frame.amogus).shoes;
	}
	
	public void moveUp() {
		if(selecting == false) {
			if(typeSelect == 0) {
				typeSelect = 2;
			}else {
				typeSelect --;
			}
			
			switch(typeSelect) {
			case 0:
				itemCursor.y = 214;
				break;
				
			case 1:
				itemCursor.y = 313;
				break;
				
			case 2:
				itemCursor.y = 406;
				break;
			}
		}else {
			int limit = 0;
			
			switch(typeSelect) {
			case 0:
				if(swords.size() > 1) {
					limit = 1;
				}
				
				if(itemSelect == limit) {
					itemSelect = swords.size() - 1;
				}else {
					itemSelect --;
				}
				break;
				
			case 1:
				if(shields.size() > limit) {
					limit = 1;
				}
				
				if(itemSelect == 0) {
					itemSelect = shields.size() - 1;
				}else {
					itemSelect --;
				}
				break;
				
			case 2:
				if(shoes.size() > limit) {
					limit = 1;
				}
				
				if(itemSelect == 0) {
					itemSelect = shoes.size() - 1;
				}else {
					itemSelect --;
				}
				break;
			}
		}
	}
	
	public void moveDown() {
		if(selecting == false) {
			if(typeSelect == 2) {
				typeSelect = 0;
			}else {
				typeSelect ++;
			}
			
			switch(typeSelect) {
			case 0:
				itemCursor.y = 214;
				break;
				
			case 1:
				itemCursor.y = 313;
				break;
				
			case 2:
				itemCursor.y = 406;
				break;
			}
		}else {
			int start = 0;
			
			switch(typeSelect) {
			case 0:
				if(swords.size() > 1) {
					start = 1;
				}
				
				if(itemSelect == swords.size() - 1) {
					itemSelect = start;
				}else {
					itemSelect ++;
				}
				break;
				
			case 1:
				if(shields.size() > 1) {
					start = 1;
				}
				
				if(itemSelect == shields.size() - 1) {
					itemSelect = start;
				}else {
					itemSelect ++;
				}
				break;
				
			case 2:
				if(shoes.size() > 1) {
					start = 1;
				}
				
				if(itemSelect == shoes.size()) {
					itemSelect = start;
				}else {
					itemSelect ++;
				}
				break;
			}
		}
	}
	
	public void select() {
		if(selecting) {
			switch(typeSelect) {
			case 0:
				if(swords.get(itemSelect) != null) {
					swordIcon = new Img(320, 217, "/uiSprites/" + swords.get(itemSelect).toString() + "Icon.png");
					Frame.amogus.weaponSelect = itemSelect;
				}
				break;
				
			case 1:
				if(shields.get(itemSelect) != null) {
					shieldIcon = new Img(320, 313, "/uiSprites/" + shields.get(itemSelect).toString() + "Icon.png");
					Frame.amogus.shieldSelect = itemSelect;
				}
				break;
			
			case 2:
				if(shoes.get(itemSelect) != null) {
					shoeIcon = new Img(320, 406, "/uiSprites/" + shoes.get(itemSelect).toString() + "Icon.png");
					((Amogus)Frame.amogus).shoeSelect = itemSelect;
				}
				break;
			}
			itemCursor.x = 320;
			typeSelect = 0;
		}else {
			itemCursor.x = 420;
			itemCursor.y = 214;
		}
		selecting = !selecting;
		switch(typeSelect) {
		case 0:
			if(swords.size() > 1) {
				itemSelect = 1;
			}else {
				itemSelect = 0;
			}
			break;
			
		case 1:
			if(shields.size() > 1) {
				itemSelect = 1;
			}else {
				itemSelect = 0;
			}
			break;
		
		case 2:
			if(shoes.size() > 1) {
				itemSelect = 1;
			}else {
				itemSelect = 0;
			}
			break;
		}
	}
	
	public void deselect() {
		if(selecting == true) {
			selecting = false;
			itemCursor.x = 320;
			typeSelect = 0;
		}else {
			switch(typeSelect) {
			case 0:
				swordIcon = null;
				Frame.amogus.weaponSelect = 0;
				break;
				
			case 1:
				shieldIcon = null;
				Frame.amogus.shieldSelect = 0;
				break;
			
			case 2:
				shoeIcon = null;
				((Amogus)Frame.amogus).shoeSelect = 0;
				break;
			}
		}
	}
	
	public void exit() {
		selecting = false;
		
	}
	
	public void paintShields(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		int y = 0, count = 0;
		for(int i = itemSelect; count < 3 && i < shields.size(); i ++) {
			if(shields.get(i) != null) {
				Img shieldImg = new Img(420, 217 + 84 * y, "/uiSprites/" + shields.get(i).toString() + "Icon.png");
				shieldImg.paint(g2);
				y ++;
				count ++;
			}
		}
	}
	
	public void paintSwords(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		int y = 0, count = 0;
		for(int i = itemSelect; count < 3 && i < swords.size(); i ++) {
			if(swords.get(i) != null) {
				Img swordImg = new Img(420, 217 + 84 * y, "/uiSprites/" + swords.get(i).toString() + "Icon.png");
				swordImg.paint(g2);
				y ++;
				count ++;
			}
		}
	}
	
	public void paintShoes(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		int y = 0, count = 0;
		for(int i = itemSelect; count < 3 && i < shoes.size(); i ++) {
			if(shoes.get(i) != null) {
				Img shoeImg = new Img(420, 217 + 84 * y, "/uiSprites/" + shoes.get(i).toString() + "Icon.png");
				shoeImg.paint(g2);
				y ++;
				count ++;
			}
		}
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		menu.paint(g2);
		swordFrame.paint(g2);
		shieldFrame.paint(g2);
		shoeFrame.paint(g2);
		if(swordIcon != null) {	swordIcon.paint(g2);}
		if(shieldIcon != null) {	shieldIcon.paint(g2);}
		if(shoeIcon != null) {	shoeIcon.paint(g2);}
		itemCursor.paint(g2);
		if(selecting == true) {
			switch(typeSelect) {
			case 0:
				paintSwords(g);
				break;
			
			case 1:
				paintShields(g);
				break;
			
			case 2:
				paintShoes(g);
				break;
			}
		}
	}
}
