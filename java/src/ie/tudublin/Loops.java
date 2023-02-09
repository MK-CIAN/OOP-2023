package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

	int mode = 0;

	public void settings() {
		size(1000, 1000);
	}

	public void setup() {
		colorMode(HSB);
	}

	public void keyPressed() {

		mode = key - '0';
		println(mode);
	}

	float offset = 0;

	public void draw() {
		background(0);
		fill(255);
		noStroke();

		switch (mode) {
			case 0:
				int numCircles = (int) (mouseX / 10.0f);
				float d = width / (float) numCircles;

				for (int j = 0; j < numCircles; j++) {
					for (int i = 0; i < numCircles; i++) {
						float x = (d * 0.5f) + (d * i);
						float y = (d * 0.5f) + (d * j);
						float c = ((i + j) / ((numCircles - 1 ) * 2.0f)) * 255.0f;
						fill((c + offset) % 256, 255, 255);
						circle(x, y, d);
					}
				}
				offset += (mouseY / 50.0f);
				break;

			case 1:
				break;

			case 2:
				break;
		}

	}
}
