package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet
{

	float playerX, playerY, playerWidth;
	float bugX, bugY, bugWidth;
	int score = 0;
	float halfPlayer, halfBug;

	public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		colorMode(HSB);
		background(0);
		smooth();

		playerX = width * 0.5f;
		playerY = height - 100;
		playerWidth = 50;
		halfPlayer = playerWidth * 0.5f;

		resetBug();
	}

	private void resetBug()
	{
		bugX = random(bugWidth / 2, width - (bugWidth / 2));
		bugY = 50;
		bugWidth = 50;
	}
	
	void drawBug(float x, float y, float w)
	{
		float halfW = w / 2;
		stroke(255);
		noFill();
		triangle(x - halfW, y + halfW, x, y - halfW, x + halfW, y + halfW);
	}

	void drawPlayer(float x, float y, float w)
	{
		noFill();
		stroke(255);
		rectMode(CENTER);
		rect(x, y, w, w);
		line(x, y - halfPlayer, x, y - halfPlayer * 2);
	}

	float playerSpeed = 5;

	public void keyPressed()
	{
		if (keyCode == LEFT)
		{
			playerX -= playerSpeed;
		}
		if (keyCode == RIGHT)
		{
			playerX += playerSpeed;
		}

		if (keyCode == ' ')
		{
			float halfW = bugWidth / 2;
			if (playerX > bugX - halfW && playerX < bugX + halfW)
			{
				score++;
				resetBug();
				line(playerX, playerY - halfPlayer, playerX, 0);
			}
			else
			{
				line(playerX, playerY - halfPlayer, playerX, 0);
			}
		}
	}

	void moveBug()
	{
		bugY ++;
		bugX += random(-20, 20);
	}

	public void draw()
	{	
		background(0);
		drawPlayer(playerX, playerY, playerWidth);
		drawBug(bugX, bugY, bugWidth);
		if(frameCount % 20 == 0)
		{
			moveBug();
		}

		text("Score: " + score, 50, 50);
	}
}
