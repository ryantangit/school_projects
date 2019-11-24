package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;


public class DeadFrame {
    int TILE_SIZE = 16;
    int width = Engine.WIDTH;
    int height = Engine.HEIGHT;

    public void activate() {
        createWindow();
        youDied();
        StdDraw.show();
        StdDraw.pause(2500);
    }

    public void createWindow() {
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        StdDraw.setPenColor(Color.white);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.black);

    }

    public void youDied() {
        StdDraw.clear(Color.RED);
        StdDraw.setPenColor(Color.green);
        Font font = new Font("Chiller", Font.PLAIN, 70);
        StdDraw.setFont(font);
        StdDraw.text(width / 2, height * 2 / 3, "YOU DIED");
        StdDraw.setPenColor(Color.black);
        StdDraw.circle(width / 2, height / 3, 5);

        StdDraw.line(width / 2 - 2, height / 3, width / 2 - 1, height / 3 + 2);
        StdDraw.line(width / 2 - 1, height / 3, width / 2 - 2, height / 3 + 2);
        StdDraw.line(width / 2 + 1, height / 3, width / 2 + 2, height / 3 + 2);
        StdDraw.line(width / 2 + 2, height / 3, width / 2 + 1, height / 3 + 2);
        StdDraw.line(width / 2 - 2, height / 3 - 2, width / 2 + 2, height / 3 - 2);
    }
}
