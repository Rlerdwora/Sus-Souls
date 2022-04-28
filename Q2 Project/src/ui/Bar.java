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

public class Bar{
	private Color color;
	private int x, y, maxValue, value, length;
	private ArrayList<BarSegment> barSegments = new ArrayList<BarSegment>();

	public Bar(int x, int y, int value, int length, Color color) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.color = color;
		maxValue = value;
		barSegments.add(new BarSegment(x, y, "start"));
		this.length = barSegments.get(0).length();
		for(int i = 0; i < length - 1; i ++) {
			BarSegment barSegment = new BarSegment(x + this.length, y, "middle");
			barSegments.add(barSegment);
			this.length += barSegment.length();
		}
		barSegments.add(new BarSegment(x + this.length, y, "end"));
		this.length += barSegments.get(barSegments.size() - 1).length();
	}
	
	public void updateValue(int value) {
		this.value = value;
	}
	
	public void increase() {
		
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		if(value != 0) {
			g.fillRect(x, y, value * length / maxValue, (int) (30 * .75));
		}
		for(BarSegment barSegment : barSegments) {
			barSegment.paint(g);
		}
	}
}
