package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;


public class StartingScreen {
    int TILE_SIZE = 16;
    int width;
    int height;
    int seedDigit;


    public StartingScreen(int width, int height) {
        this.width = width;
        this.height = height;
        seedDigit = 0;
    }

    public void activate() {
        createWindow();
        startingText();

        StdDraw.show();

    }

    public void createWindow() {
        StdDraw.setCanvasSize(width * TILE_SIZE, height * TILE_SIZE);
        StdDraw.setPenColor(Color.white);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.clear(Color.black);

    }

    public void startingText() {
        //Title of the Game
        Font font = new Font("Comic Sans", Font.BOLD, 50);
        StdDraw.setFont(font);
        //
        StdDraw.text(width / 2, height * 2 / 3, "Lost in the Woods");

        font = new Font("Comic Sans", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(width / 2, height / 2, "New Game [N]");
        StdDraw.text(width / 2, height / 2 - 5, "Load Game [L]");
        StdDraw.text(width / 2, height / 2 - 10, "Quit Game [Q]");
    }

    public void seedScreen() {
        rulesScreen();

        createWindow();
        Font font = new Font("Comic Sans", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.green);
        StdDraw.text(width / 2, height * 4 / 5, "Defend yourself against the Zombies!");
        StdDraw.setPenColor(Color.white);
        StdDraw.text(width / 2, height * 2 / 3, "SEED");
        StdDraw.text(width / 2, height / 3, "Press 's' to end");
        StdDraw.text(width / 2, height / 5, "Type in numbers to generate a random map.");
        StdDraw.setPenColor(Color.green);
        StdDraw.text(width / 2, height / 8, "Don't move into their sight!");
        seedDigit = 0;
        StdDraw.show();
    }

    public void rulesScreen() {
        createWindow();
        Font font = new Font("Comic Sans", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);

        StdDraw.text(width / 2, height * 9 / 10, "Controls");

        StdDraw.text(width / 3, height * 4 / 5, "w/a/s/d");
        StdDraw.text(width * 2 / 3, height * 4 / 5, "Move");

        StdDraw.text(width / 3, height * 3 / 5, "j");
        StdDraw.text(width * 2 / 3, height * 3 / 5, "Turn to Aim in A Direction");

        StdDraw.text(width / 3, height / 2, ":q");
        StdDraw.text(width * 2 / 3, height / 2, "Save and quit the game");


        StdDraw.text(width / 3, height * 2 / 5, "f");
        StdDraw.text(width * 2 / 3, height * 2 / 5, "Fire when Zombies are in range");



        StdDraw.setPenColor(Color.green);
        StdDraw.text(width / 2, height * 1 / 5, "You get three lives.");
        StdDraw.text(width / 2, height * 2 / 7,
                "Zombies run faster when you are in their line of sight!");
        StdDraw.text(width / 2, height * 1 / 8, "Tread Carefully");
        StdDraw.setPenColor(Color.red);
        StdDraw.text(width / 2, height * 1 / 11, "KILL THEM ALL!");

        StdDraw.show();
        StdDraw.pause(10000);
        StdDraw.clear(Color.black);


    }

    public void updateSeed(char c) {
        if (c != 's') {
            StdDraw.text(width / 3 + seedDigit * 2, height / 2, "" + c);
            seedDigit += 1;
        }

    }

    public void happyBirthdayScreen() {

        StdDraw.setCanvasSize(1700, 1000);
        StdDraw.clear(Color.black);
        StdDraw.clear(Color.pink);
        Font font = new Font("Comic Sans", Font.BOLD, 150);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.yellow);
        StdDraw.text(.5, .7, "Happy Birthday");
        StdDraw.text(.5, .5, "Lisa ! ! !");
        StdDraw.show();
        StdDraw.pause(5000);
        System.exit(0);

    }

}
