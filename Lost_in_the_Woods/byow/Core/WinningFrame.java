package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

public class WinningFrame {
    int TILE_SIZE = 16;
    int width = Engine.WIDTH;
    int height = Engine.HEIGHT;

    public void createWindow() {
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        StdDraw.setPenColor(Color.white);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.black);

    }

    public void activate() {
        createWindow();
        startingText();
        StdDraw.show();
        StdDraw.pause(3000);

    }

    public void startingText() {
        //Title of the Game
        Font font = new Font("Comic Sans", Font.BOLD, 50);
        StdDraw.clear(Color.yellow);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.black);
        StdDraw.text(width / 2, height * 2 / 3, "YOU SURVIVED");

        StdDraw.text(width / 2, height * 1 / 3, "Good Job!");

        font = new Font("Comic Sans", Font.BOLD, 30);
        StdDraw.setFont(font);

    }
}
