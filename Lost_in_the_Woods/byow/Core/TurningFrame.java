package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

public class TurningFrame {
    int TILE_SIZE = 16;
    int width = Engine.WIDTH;
    int height = Engine.HEIGHT;
    String dir;

    public TurningFrame(String dir) {
        this.dir = dir;
    }

    public void activate(boolean near) {
        createWindow();
        drawDirection();
        if (near) {
            try {
                Audio zombie = new Audio("./zombiemoan.wav");
                drawFrame1();
                zombie.pause();
            } catch (LineUnavailableException ex) {
                System.out.println("Error with sound");
            } catch (IOException ex) {
                System.out.println("Error with sound");
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("Error with sound");
            }
        } else {
            drawFrame0();
        }
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);

    }

    public void createWindow() {
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        StdDraw.setPenColor(Color.white);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.black);

    }

    public void drawFrame0() {
        person();
    }

    public void drawFrame1() {
        person();
        zombie();
    }

    public void drawDirection() {
        StdDraw.setPenColor(Color.white);
        Font font = new Font("Comic Sans", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width / 2, height * 2 / 3, dir);
        StdDraw.setFont();
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
        StdDraw.rectangle(70, 15, 2, 2);
        StdDraw.line(70, 13, 70, 6);
        StdDraw.line(70, 6, 73, 3);
        StdDraw.line(70, 6, 67, 3);
        StdDraw.line(67, 9, 70, 9);
        StdDraw.line(67, 9, 66, 8);
        StdDraw.line(65, 11, 70, 11);
        StdDraw.line(65, 11, 65, 10);
    }
}
