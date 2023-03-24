package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Audio1 extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    int numStars = 250;
    float[] starX = new float[numStars];
    float[] starY = new float[numStars];
    float[] starReact = new float[numStars];

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

    public void settings() {
        size(1024, 800);
        // fullScreen(P3D, SPAN);
    }

    public void setup() {
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix;

        // And comment the next two lines out
        ap = minim.loadFile("starryeyed.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        for(int i = 0; i < numStars; i++) {
            starX[i] = random(width);
            starY[i] = random(height / 1.5f);
            //setting a ranom limit between 50 and 250
            starReact[i] = random(20, 100);
        }
    }

    float off = 0;

    float lerpedBuffer[] = new float[1024];
    
    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        float cx = width / 2;
        float cy = height / 2;

        float halfW = width / 2;
        float topOffset = height * 0.2f;


        switch (mode) {
            case 0:
                colorMode(HSB, 255);
                background(0, 10, 20);

                for(int i = 0; i < numStars; i++)
                {
                    stroke(0);
                    strokeWeight(1);
                    fill(255);
                    circle(starX[i], starY[i], smoothedAmplitude * starReact[i]);
                }

                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), 50, 255);
                    float amplitude = ab.get(i);
                    float f = lerpedBuffer[i] * halfW * 1.0f;

                    // Map amplitude to stroke color in HSB format
                    float hue = map(amplitude, -1, 1, 100, 120);
                    float saturation = map(amplitude, -1, 1, 200, 255);
                    float brightness = map(amplitude, -1, 1, 150, 255);

                    // Draw lines with varying stroke weight and color
                    if (f > 0) {
                        float weight = map(amplitude, -1, 1, 0.5f, 3);
                        strokeWeight(f / 50);
                        stroke(hue, saturation, brightness, c);
                        line(i, topOffset + f, i, topOffset - f);

                        float alpha = map(amplitude, -1, 1, 20, 80);
                        strokeWeight(weight * 0.5f);
                        stroke(hue, saturation, brightness, alpha);
                        line(i, topOffset + (f * 0.5f), i, topOffset - (f * 0.5f));
                    }
                }
                break;
            case 1:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    // float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, ab.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(i, halfH + f, halfH - f, i);
                    line(i, halfH + f, halfH - f, i);
                    line(i, halfH + f, halfH - f, i);
                }
                break;
            case 2:
                background(0);
                for (int i = 0; i < ab.size(); i++) {
                    float c = map(i, 0, ab.size(), mouseX / 2, mouseY / 2);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    line(0, i, f, i);
                    line(0, i, f, i);
                    line(0, i, f, i);
                    line(width, i, width - f, i);
                    line(width, i, width - f, i);
                    line(width, i, width - f, i);
                    line(i, 0, i, f);
                    line(i, 0, i, f);
                    line(i, 0, i, f);
                    line(i, height, i, height - f);
                    line(i, height, i, height - f);
                    line(i, height, i, height - f);
                }
                break;
            case 3:
                // Other examples we made in the class
                background(0);
                stroke(0);
                fill(255);
                fill(255);
                fill(255);

                // drawing stars at random x and y positions
               // for (int i = 0; i < 10; i++) {
                //    circle(Cx, Cy, smoothedAmplitude * 100);
                //}

                break;
        }
    }
}
