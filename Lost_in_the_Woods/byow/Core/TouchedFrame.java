package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;


public class TouchedFrame {
    int TILE_SIZE = 16;
    int width = Engine.WIDTH;
    int height = Engine.HEIGHT;
    int health;

    public TouchedFrame(int h) {
        this.health = h;
    }

    public void activate() {
        createWindow();
        drawHealth();
        person();
        zombie();

        try {
            Audio pain = new Audio("./humanpain.wav");
            StdDraw.show();
            StdDraw.pause(500);
            pain.pause();
            StdDraw.pause(1000);
        } catch (LineUnavailableException ex) {
            System.out.println("Error with sound");
        } catch (IOException ex) {
            System.out.println("Error with sound");
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Error with sound");
        }

        StdDraw.clear(Color.black);

    }

    public void drawHealth() {
        StdDraw.setPenColor(Color.white);
        Font font = new Font("Comic Sans", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width / 2, height * 2 / 3, "Health remaining: " + health + " !");
        StdDraw.setFont();
    }

    public void createWindow() {
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        StdDraw.setPenColor(Color.white);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.black);

    }

    public void person() {
        StdDraw.setPenColor(Color.white);
        StdDraw.circle(10, 15, 2);
        StdDraw.line(10, 13, 10, 6);
        StdDraw.line(10, 6, 13, 3);
        StdDraw.line(10, 6, 7, 3);
        StdDraw.line(7, 9, 13, 9);
        StdDraw.setPenColor(Color.yellow);

        StdDraw.filledRectangle(13.5, 9, .5, 1);
        StdDraw.filledRectangle(15.5, 10.5, 2, .5);
        StdDraw.circle(14, 9.75, .5);

    }

    public void zombie() {
        StdDraw.setPenColor(Color.green);
        StdDraw.rectangle(17, 15, 2, 2);
        StdDraw.line(17, 13, 17, 6);
        StdDraw.line(17, 6, 20, 3);
        StdDraw.line(17, 6, 14, 3);
        StdDraw.line(14, 9, 17, 9);
        StdDraw.line(14, 9, 13, 8);
        StdDraw.line(12, 11, 17, 11);
        StdDraw.line(12, 11, 12, 10);
    }
}
