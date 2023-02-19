package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {

	int mode = 0;
	int rectSize = 100;
	int rectX = width / 2 - rectSize / 2;
	int rectY = height / 2 - rectSize / 2;

	public void settings() {
		size(800, 800);
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
				// Creating coloured circles that react to mouse position
				int numCircles = (int) (mouseX / 10.0f);
				float d = width / (float) numCircles;

				for (int j = 0; j < numCircles; j++) {
					for (int i = 0; i < numCircles; i++) {
						float x = (d * 0.5f) + (d * i);
						float y = (d * 0.5f) + (d * j);
						float c = ((i + j) / ((numCircles - 1) * 2.0f)) * 255.0f;
						fill((c + offset) % 256, 255, 255);
						circle(x, y, d);
					}
				}
				offset += (mouseY / 50.0f);
				break;

			case 1:
				// Creating coloured bars that react to mouse position, like in case 0
				int bars = (int) (mouseX / 10.0f);
				float barWidth = width / (float) bars;

				for (int i = 0; i < bars; i++) {
					noStroke();
					fill(map(i, 0, bars, 0, 255), 255, 255);
					rect(map(i, 0, bars, 0, width), 0, barWidth, height);
				}
				break;

			case 2:
				// Creating coloured squares that react to mouse position and meet in the
				// middle.
				int squares = (int) (mouseX / 10.0f);
				float squareHeigth = width / (float) squares;

				for (int i = 0; i < squares; i++) {
					for (int j = 0; j < squares; j++) {
						noStroke();
						fill(map(i, 0, squares, 0, 255), 255, 255);
						float x = map(i, 0, squares, 0, width);
						rect(x, x, squareHeigth, squareHeigth);
						rect((width - squareHeigth) - x, x, squareHeigth, squareHeigth);
					}
				}
				break;

			case 3:
				// Using the grid I created while doing the previous year's lab test
				stroke(255);
				float border = 50.0f;

				int count = 10;
				float gap = (width - (border * 2.0f)) / (float) count;

				for (int i = -5; i <= 5; i++) {
					float x = border + (gap * (i + 5));
					text(i, x, border / 2);
					line(x, border, x, height - border);
					text(i, border / 2, x);
					line(border, x, width - border, x);
				}
				break;

			case 4:
				stroke(255, 255, 255);
				float cx = width / 2.0f;
				float cy = height / 2.0f;
				float radius = 200.0f;
				int points = (int) map(mouseX, 1, width, 5, 20);
				int sides = points * 2;
				float px = cx;
				float py = cy - radius;
				for (int i = 0; i <= sides; i++) {
					float r = (i % 2 == 0) ? radius : radius / 2.0f;
					float theta = map(i, 0, sides, 0, TWO_PI);

					float x = cx + sin(theta) * r;
					float y = cy + cos(theta) * r;
					line(px, py, x, y);
					px = x;
					py = y;
				}
				break;
			case 5:
				// Creating a checkerboard
				background(255);
				noStroke();

				int rows = 20;
				int cols = 20;
				float cellWidth = width / (float) cols;
				float cellHeight = height / (float) rows;

				int darkBlue = color(0, 0, 139);
				int lightBlue = color(135, 206, 250);

				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						if ((i + j) % 2 == 0) {
							fill(darkBlue);
						} else {
							fill(lightBlue);
						}
						rect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
					}
				}
				break;

			case 6:
				// Creating a rectangle that will move posistion when the mouse enters the
				// rectangle
				fill(100, 100, 100);
				rect(rectX, rectY, rectSize, rectSize);

				if (mouseX >= rectX && mouseX <= rectX + rectSize && mouseY >= rectY && mouseY <= rectY + rectSize) {
					rectX = (int) random(width - rectSize);
					rectY = (int) random(height - rectSize);
					println("Mouse is inside the rectangle");
				} else {
					println("Mouse is outside the rectangle");
				}
				break;
		}

	}
}
