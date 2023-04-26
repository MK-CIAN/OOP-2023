package ie.tudublin;

import java.util.ArrayList;


import processing.core.PApplet;
import processing.data.TableRow;
import processing.data.Table;

import processing.data.*;


public class StarMap extends PApplet
{
	ArrayList <Star> stars = new ArrayList <Star>();

	public float border;

    public void settings()
	{
		size(800, 800);
	}

	public void setup() {
		colorMode(HSB);
		loadStars();
		printStars();

		border = width * 0.1f;
	}

	public void drawGrid()
	{
		stroke(255);
		float border = 50.0f;

		int count = 10;
		float gap = (width - (border * 2.0f)) / (float) count;

		for (int i = -5; i <= 5; i++) 
		{
			float x = border + (gap * (i + 5));
			text(i, x, border / 2);
			line(x, border, x, height - border);
			text(i, border / 2, x);
			line(border, x, width - border, x);
		}

		//float f = map(5, 0, 10, 100, 200);
		//float f1 = map1(a:5, b:0, c:10)
	}

	public void loadStars()
	{
		Table table = loadTable("HabHYG15ly.csv", "header");
		for (TableRow row : table.rows()) {
			Star s = new Star(row);
			stars.add(s);
		}
	}

	public void printStars()
	{
		for (Star s : stars) {
			System.out.println(s);
		}
	}

	Star first = null;
	Star second = null;

	public void mouseClicked()
	{
		for(Star s:stars)
		{
			float x = map(s.getxG(), -5, 5, border, width - border);
			float y = map(s.getyG(), -5, 5, border, height - border);

			if (dist(mouseX, mouseY, x, y) < 20)
			{
				if(first == null)
				{
					first = s;
					break;
				}
				else if(second == null)
				{
					second = s;
					break;
				}
				else 
				{
					first = s;
					second = null;
					break;
				}
			}
		}
	}

	public void drawStars()
	{
		for(Star s:stars)
		{
			s.render(this);
		}
	}

	public void draw()
	{	
		strokeWeight(1);	
		drawGrid();
		drawStars();

		if (first != null)
		{
			float x = map(first.getxG(), -5, 5, border, width - border);
			float y = map(first.getyG(), -5, 5, border, height - border);

			if (second != null)
			{
				float x2 = map(second.getxG(), -5, 5, border, width - border);
				float y2 = map(second.getyG(), -5, 5, border, height - border);

				stroke(255, 255, 0);
				line(x, y, x2, y2);

				float dist = dist(first.getxG(), first.getyG(), second.getxG(), second.getyG());
				fill(255);
				textAlign(CENTER, CENTER);
				text("Distance betweem: " + first.getDisplayName() + " and " + second.getDisplayName() + " is " + dist + "parsecs", width / 2, height - border / 2);
			}
			else
			{
				stroke(255, 0, 0);
				line(x, y, mouseX, mouseY);
			}
		}
	}
}
