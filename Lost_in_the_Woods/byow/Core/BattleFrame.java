package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;

public class BattleFrame {
    int TILE_SIZE = 16;
    int width = Engine.WIDTH;
    int height = Engine.HEIGHT;


    public void missActivate() {
        createWindow();
        drawMissedFrame1();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawMissedFrame2();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawMissedFrame3();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawMissedFrame4();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
    }
    public void activate() {
        createWindow();

        drawFrame1();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawFrame2();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawFrame3();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawFrame4();
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        drawFrame5();
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

    public void drawMissedFrame1() {
        person();
    }

    public void drawMissedFrame2() {
        person();
        bullet1();

    }

    public void drawMissedFrame3() {
        person();
        bullet2();

    }

    public void drawMissedFrame4() {
        person();
        bullet3();
    }

    public void drawFrame1() {
        person();
        zombie();
    }

    public void drawFrame2() {
        person();
        zombie();
        bullet1();
    }

    public void drawFrame3() {
        person();
        zombie();
        bullet2();
    }

    public void drawFrame4() {
        person();
        zombie();
        bullet3();
    }

    public void drawFrame5() {
        person();
        deadZombie();
        blood();
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

    public void bullet1() {
        StdDraw.setPenColor(Color.gray);
        StdDraw.filledRectangle(20, 10.5, .5, .5);
    }

    public void bullet2() {
        StdDraw.setPenColor(Color.gray);
        StdDraw.filledRectangle(40, 10.5, .5, .5);
    }
    public void bullet3() {
        StdDraw.setPenColor(Color.gray);
        StdDraw.filledRectangle(60, 10.5, .5, .5);
    }
    
    public void blood() {
        StdDraw.setPenColor(Color.red);
        StdDraw.filledEllipse(75, 2.25, 2, .75);
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

    public void deadZombie() {
        StdDraw.setPenColor(Color.green);
        StdDraw.rectangle(75, 5, 2, 2);
        StdDraw.line(70, 13, 70, 6);
        StdDraw.line(70, 6, 73, 3);
        StdDraw.line(70, 6, 67, 3);
        StdDraw.line(67, 9, 70, 9);
        StdDraw.line(67, 9, 66, 8);
        StdDraw.line(65, 11, 70, 11);
        StdDraw.line(65, 11, 65, 10);
    }
}
