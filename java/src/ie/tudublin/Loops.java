package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

	int mode = 0;

	public void settings() {
		size(1000, 1000);
		//fullScreen(SPAN);
	}

	public void setup() {
		colorMode(HSB);
	}

	public void keyPressed() {

		mode = key - '0';
		println(mode);
	}

	float off = 0;

	public void draw() {
		background(0);
		fill(255);
		noStroke();

		switch (mode) {
			case 0:
				background(135, 206, 235); // set the background color to light blue

				// draw the mountains
				stroke(150);
				fill(180);
				triangle(0, 300, 200, 100, 400, 300);
				triangle(300, 300, 500, 100, 700, 300);
			
				// draw the trees
				stroke(0, 100, 0);
				fill(0, 150, 0);
				rect(100, 400, 50, 150);
				rect(200, 350, 50, 200);
				rect(300, 400, 50, 150);
				rect(400, 350, 50, 200);
				rect(500, 400, 50, 150);
				rect(600, 350, 50, 200);
			
				// draw the person
				stroke(0);
				fill(255, 204, 0);
				ellipse(400, 300, 50, 100); // head
				rect(385, 350, 30, 80); // body
				line(400, 430, 380, 480); // left arm
				line(400, 430, 420, 480); // right arm
				line(400, 430, 400, 500); // torso
			
				// draw the text
				fill(0);
				textSize(32);
				text("A person walking through the forest with mountains in the background", 50, 50);
				break;
			case 1:
				break;
			default:
				break;
		}

	}
}
