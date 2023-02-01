package ie.tudublin;

import processing.core.PApplet;

public class HelloProcessing extends PApplet
{
	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		//colorMode(HSB);
		background(0);
		
	}
	
	public void draw()
	{	
		fill(255, 0, 0);
		rect(0, 00, 500, 500); //tlx, tly, w, h
		fill(255, 255, 0);
		circle(250, 290, 450); //cx, cy, diameter
		fill(0, 255, 255);
		triangle(25, 475, 250, 0, 475, 475); //x1, y1, x2, y2, x3, y3
		fill(0, 0, 255);
		circle(25, 290, 100);
	}
}
