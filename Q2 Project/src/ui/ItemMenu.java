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

public class ItemMenu{

	public Img menu = new Img(230, 100, "/uiSprites/itemMenu.png");
	public Img swordFrame = new Img(320, 217, "/uiSprites/iconFrame1.png");
	public Img shieldFrame = new Img(320, 316, "/uiSprites/iconFrame1.png");
	public Img shoeFrame = new Img(320, 409, "/uiSprites/iconFrame1.png");
	
	public ItemMenu() {
		
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;	
		menu.paint(g2);
		swordFrame.paint(g2);
		shieldFrame.paint(g2);
		shoeFrame.paint(g2);
	}
}
