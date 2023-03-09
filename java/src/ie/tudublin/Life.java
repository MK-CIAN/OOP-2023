package ie.tudublin;

import processing.core.PApplet;

public class Life extends PApplet
{

	LifeBoard board;

	public void settings()
	{
		size(800, 800);
	}

	public void setup() {
		colorMode(RGB);
		background(0);
		board = new LifeBoard(50, this);
		board.randomise();
	}

	
	
	public void draw()
	{	
		background(0);
		board.render();
		board.applyRules();

        if (keyCode == 32)
        {
            if (isLooping())
            {
                noLoop();
                //debug message
                System.out.println("Paused");
            }
            else{
                loop();
                //debug message
                System.out.println("Unpaused");
            }
        }

		//Radnomising the boaord when 1 is pressed
		if (key == '1')
		{
			board.randomise();
			board.applyRules();
		}
    }
}
