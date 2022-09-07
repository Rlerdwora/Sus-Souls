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

//bar class for ui purposes like displaying health or stamina
public class Bar{
	private Color color;														//color of the bar
	
	private int x, y;															//int x and y for location
	
	public int value, maxValue;													//int value for the current bar value
																				//int maxvalue for the max value
	
	public int length;															//int length for how long the bar is
	
	private ArrayList<BarSegment> barSegments = new ArrayList<BarSegment>();	//arraylist of barsegments to
																				//make up the bar

	//constructor with parameters for the previous variables
	public Bar(int x, int y, int value, int length, Color color) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.color = color;
		maxValue = value;
		barSegments.add(new BarSegment(x, y, "start"));
		this.length = barSegments.get(0).length();
		for(int i = 0; i < length - 1; i ++) {				//makes the bar have the parameter length amount of segments
			BarSegment barSegment = new BarSegment(x + this.length, y, "middle");
			barSegments.add(barSegment);
			this.length += barSegment.length();
		}
		barSegments.add(new BarSegment(x + this.length, y, "end"));
		this.length += barSegments.get(barSegments.size() - 1).length();
	}
	
	//update the value
	public void updateValue(int value) {
		this.value = value;
	}
	
	//paint method
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
