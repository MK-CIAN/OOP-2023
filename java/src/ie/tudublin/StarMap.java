package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class StarMap extends PApplet
{

	ArrayList<Star> stars = new ArrayList<Star>();

	public void settings()
	{
		size(800, 800);
	}

	public void setup() {
		colorMode(RGB);
		background(0);

		smooth();
		
		loadStars();
		printStars();
	}

	void printStars()
	{
		for(int i = 0 ; i < stars.size() ; i ++)
		{
			println(stars.get(i));
		}
	}

	void displayStars()
	{
		for(int i = 0 ; i < stars.size() ; i ++)
		{
			stars.get(i).render(this);
		}
	}

	public void loadStars()
	{
		Table table = loadTable("HabHYG15ly.csv", "header");
		for(TableRow tr:table.rows())
		{
			Star s = new Star(tr);
			stars.add(s);
		}
	}

	public void drawGrid()
	{
		stroke(0, 255, 255);
		float border = width * 0.1f;


		//int count = 10;
		//float gap = (width - (border * 2.0f)) / (float) count;

		for (int i = -5; i <= 5; i++) 
		{
			float x = map(i, -5, 5, border, width - border);
			line(x, border, x, height - border);
			line(border, x, width - border, x);

			textAlign(CENTER, CENTER);
			text(i, x, border * 0.5f);
			text(i, border * 0.5f, x);
		}

		//float f = map(5, 0, 10, 100, 200);
		//float f1 = map1(5, 0, 10, 100, 200);
	}

	float map1(float a, float b, float c, float d, float e)
	{
		float r1 = c - b;
		float r2 = e - d;

		float howFar = a - b;

		return d + ((howFar / r1) * r2);
	}
	
	//Selecting the stars and displaying the distance between them
	int selected1 = -1;
	int selected2 = -1;

	public void mousePressed()
	{
		for(int i = 0; i < stars.size(); i++)
		{
			Star s = stars.get(i);
			if(dist(mouseX, mouseY, s.getxG(), s.getyG()) < 10)
			{
				if(selected1 == -1)
				{
					selected1 = i;
				}
				else if (selected2 == -1)
				{
					selected2 = i;
				}
				else
				{
					selected1 = i;
					selected2 = -1;
				}
			}
		}
	}

	public void draw()
	{	
		strokeWeight(1);	

		drawGrid();	
		displayStars();

		//If I have selected a star or more
		if(selected1 != -1 && selected2 == -1)
		{
			Star s1 = stars.get(selected1);
			stroke(255, 255, 0);
			line(s1.getxG(), s1.getyG(), mouseX, mouseY);
		}
		else if (selected1 != -1 && selected2 != -1)
		{
			Star s1 = stars.get(selected1);
			Star s2 = stars.get(selected2);
			stroke(255, 255, 0);
			line(s1.getxG(), s1.getyG(), s2.getxG(), s2.getyG());
			fill(255);
			float distance = dist(s1.getxG(), s1.getyG(), s2.getxG(), s2.getyG());
			text("Distance from" + s1.getDisplayName() + " to " + s2.getDisplayName() + " is " + distance + "Parsecs", 10, 10);
		}
	}
}
