package ui;
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

public class SuspicionIcon{
	
	private int x, y;
	private Img icon, tenth, ones;

	public SuspicionIcon(int x, int y) {
		icon = new Img(x, y, "/uiSprites/suspicionIcon.png");
		tenth = new Img(x - 1, y + 14, .65, .65, "/uiSprites/" + ((Amogus)Frame.amogus).suspicion / 10 + ".png");
		ones = new Img(x + 30, y + 14, .65, .65, "/uiSprites/" + ((Amogus)Frame.amogus).suspicion % 10 + ".png");
	}
	
	public void paint(Graphics g) {
		tenth.setImg("/uiSprites/" + ((Amogus)Frame.amogus).suspicion / 10 + ".png");
		ones.setImg("/uiSprites/" + ((Amogus)Frame.amogus).suspicion % 10 + ".png");
		Graphics2D g2 = (Graphics2D) g;	
		icon.paint(g2);
		tenth.paint(g2);
		ones.paint(g2);
	}
}
