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
	private ArrayList<Brick> tiles = new ArrayList<Brick>();

	public Background(int x, int y, int id) {
		this.x = x;
		this.y = y;
		switch(id) {
		
		case 1:
			
			break;
		}
	}
	
	/* Drawing commands */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
	}
}
