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

public class Bar{
	private Color color;
	private int x, y, value, length;
	private ArrayList<BarSegment> barSegments = new ArrayList<BarSegment>();

	public Bar(int x, int y, int value, Color color) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.color = color;
		barSegments.add(new BarSegment(x, y, "start"));
		length = barSegments.get(0).length();
		for(int i = 0; i < 10; i ++) {
			BarSegment barSegment = new BarSegment(x + length, y, "middle");
			barSegments.add(barSegment);
			length += barSegment.length();
		}
		barSegments.add(new BarSegment(x + length, y, "end"));
		length += barSegments.get(barSegments.size() - 1).length();
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, value * length / value, (int) (30 * .75));
		for(BarSegment barSegment : barSegments) {
			barSegment.paint(g);
		}
	}
}
