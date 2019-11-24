package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    TETile[][] currentFrame;
    int numofRooms = 0;
    int index1 = 0;
    int index2 = 1;
    Random seedRandom;
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;
    public static final int ROOM_MAXWIDTH  = WIDTH / 5;
    public static final int ROOM_MAXLENGTH = HEIGHT / 5;
    public static final int ROOM_MINWIDTH = 3;
    public static final int ROOM_MINLENGTH = 3;
    public static final int NUM_OF_ROOMS = 100;
    private ArrayList<Position> listOfRooms = new ArrayList<>();

    /*Character Creation */
    Avatar avatar;
    Zombie[] zombie;

    Audio background;
    Audio winning;



    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        try {
            background = new Audio("./background.wav");
        } catch (LineUnavailableException ex) {
            System.out.println("Error with sound");
        } catch (IOException ex) {
            System.out.println("Error with sound");
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Error with sound");
        }
        ter.initialize(Engine.WIDTH, Engine.HEIGHT);
        KeyboardExtraction keyboardExtraction = new KeyboardExtraction();
        Load load = new Load();

        String message = keyboardExtraction.startAction();
        if (message.equals("quit")) {
            System.out.println("processed");
            System.exit(0);

        } else if (message.equals("load")) {
            StdDraw.setFont();
            currentFrame = interactWithInputString((load.loadString()));
            ter.renderFrame(currentFrame);
            for (char c : load.loadAction().toCharArray()) {
                currentFrame = StringExtraction.charAction(avatar, currentFrame, c,
                        load, zombie, seedRandom);
            }
            ter.renderFrame(currentFrame);

            keyboardExtraction.setGameInProgress(true);

        } else {
            System.out.println("Seed: ");
            StdDraw.setFont();
            System.out.println(message);
            load.addLoadString(message);
            ter.renderFrame(interactWithInputString(message));
            keyboardExtraction.setGameInProgress(true);
        }


        while (keyboardExtraction.gameInProgress && !allZombieDead()) {
            currentFrame = keyboardExtraction.charAction(avatar, currentFrame,
                    load, zombie, seedRandom);
            ter.renderFrame(currentFrame);
            StdDraw.setPenColor(Color.white);
            StdDraw.setXscale(0, WIDTH);
            StdDraw.setYscale(0, HEIGHT);
            StdDraw.setFont();
            StdDraw.text(5, HEIGHT - 1, "Direction: " + avatar.getDirection());
            int mouseX = (int) StdDraw.mouseX();
            int mouseY = (int) StdDraw.mouseY();
            StdDraw.text(25, HEIGHT - 1,
                    "Hovering: " + currentFrame[mouseX][mouseY].description());
            StdDraw.text(50, HEIGHT - 1, "Health: " + avatar.health);
            StdDraw.text(70, HEIGHT - 1,
                    "Zombie In Range: " + avatar.checkZombieAhead(currentFrame));
            StdDraw.show();
        }

        background.pause();
        try {
            winning = new Audio("./winningmusic.wav");
        } catch (LineUnavailableException ex) {
            System.out.println("Error with sound");
        } catch (IOException ex) {
            System.out.println("Error with sound");
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("Error with sound");
        }

        WinningFrame winningFrame = new WinningFrame();
        winningFrame.activate();
        winning.pause();
        System.exit(0);




    }



    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {

        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.


        currentFrame = new TETile[WIDTH][HEIGHT];
        Load load = new Load();
        if (input.charAt(0) == 'n') {
            StringExtraction seed = new StringExtraction(input);
            seedRandom = new Random(seed.seed());
            load.addLoadString(seed.getSeedString());
            load.saveLoadString();
            fillWithNothingTiles(currentFrame);
            for (int i = 0; i < NUM_OF_ROOMS; i++) {
                randomRoom(randomPosition(), currentFrame);
            }
            avatar = new Avatar(currentFrame,
                    listOfRooms.get(seedRandom.nextInt(listOfRooms.size())),
                    Tileset.AVATAR);
            spawnZombies();

            String actions = seed.seedAction();
            for (char c : actions.toCharArray()) {
                currentFrame = StringExtraction.charAction(avatar, currentFrame, c,
                        load, zombie, seedRandom);
            }
            System.out.println(actions);
            if (actions.length() >= 2 && actions.charAt(actions.length() - 1) == 'q'
                    && actions.charAt(actions.length() - 2) == ':') {
                load.saveLoadAction();
            }

        } else if (input.charAt(0) == 'l') {
            currentFrame = interactWithInputString(load.loadString() + load.loadAction());
            String actions = input;
            load.addLoadAction(load.loadAction());
            for (char c : actions.toCharArray()) {
                currentFrame = StringExtraction.charAction(avatar, currentFrame, c,
                        load, zombie, seedRandom);
            }
            if (actions.length() > 2 && actions.charAt(actions.length() - 1) == 'q'
                    && actions.charAt(actions.length() - 2) == ':') {
                load.saveLoadAction();
            }
        }
        //Extract the seed following the format "n******s"


        TETile[][] finalWorldFrame = currentFrame;
        return finalWorldFrame;
    }


    public boolean allZombieDead() {
        for (Zombie z : zombie) {
            if (z.alive) {
                return false;
            }
        }
        return true;
    }

    public void spawnZombies() {
        zombie = new Zombie[listOfRooms.size() - 1];

        for (int i = 0; i < listOfRooms.size() - 1; i++) {
            Position p = listOfRooms.get(seedRandom.nextInt(listOfRooms.size()));
            while (!currentFrame[p.x][p.y].equals(Tileset.FLOOR)) {
                p = listOfRooms.get(seedRandom.nextInt(listOfRooms.size()));
            }
            zombie[i] = new Zombie(currentFrame, p, Tileset.ZOMBIE);
        }
    }

    public Position randomPosition() {
        return new Position(seedRandom.nextInt(WIDTH - 3), seedRandom.nextInt(HEIGHT - 3 - 7));
    }

    public boolean checkOverlap(int width, int length, TETile[][]tiles, Position p) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                if (tiles[p.x + x][p.y + y].equals(Tileset.FLOOR)) {
                    return true;
                }

            }
        }
        return false;
    }

    //Create Room that starts from the left-bottom corner of the room.
    public void createRoom(int width, int length, TETile[][] tiles, Position p) {
        if (width < 3 && length < 3) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        if (!checkOverlap(width, length, tiles, p)) {

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < length; y++) {
                    if (x != 0 && x != width - 1 && y != 0 && y != length - 1) {
                        tiles[p.x + x][p.y + y] = Tileset.FLOOR;
                    } else if (tiles[p.x + x][p.y + y].equals(Tileset.FLOOR)) {
                        tiles[p.x + x][p.y + y] = (Tileset.FLOOR);
                    } else {
                        tiles[p.x + x][p.y + y] = Tileset.WALL;
                    }
                }
            }
            numofRooms += 1;
            listOfRooms.add(getRoomCenter(p, width, length));
            if (numofRooms > 1) {
                connectRooms(listOfRooms.get(index1), listOfRooms.get(index2), tiles);
                index1 += 1;
                index2 += 1;
            }
        }

    }

    //First fill up the entire TETile with a bunch of nothing Tiles to ensure engine runs.
    public void fillWithNothingTiles(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height - 3; y += 1) {
                tiles[x][y] = Tileset.TREE;
            }
        }
        for (int x = 0; x < width; x += 1) {
            for (int y = height - 3; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public void randomRoom(Position p, TETile[][] tiles) {
        int roomWidth = seedRandom.nextInt(WIDTH - p.x);
        int roomLength = seedRandom.nextInt(HEIGHT - p.y);
        if (roomWidth > ROOM_MAXWIDTH) {
            roomWidth = ROOM_MAXWIDTH;
        }
        if (roomLength > ROOM_MAXLENGTH) {
            roomLength = ROOM_MAXLENGTH;
        }
        if (roomWidth < ROOM_MINWIDTH) {
            roomWidth = ROOM_MINWIDTH;
        }
        if (roomLength < ROOM_MINLENGTH) {
            roomLength = ROOM_MINLENGTH;
        }

        if (roomLength == ROOM_MINLENGTH && roomWidth > ROOM_MINWIDTH) {
            roomLength += 1;
        }
        if (roomLength > ROOM_MINLENGTH && roomWidth == ROOM_MINWIDTH) {
            roomWidth += 1;
        }
        createRoom(roomWidth, roomLength, tiles, p);

    }

    private Position getRoomCenter(Position p, int roomWidth, int roomLength) {
        int centerX;
        int centerY;

        centerX = (p.x + roomWidth + p.x) / 2;
        centerY = (p.y + roomLength + p.y) / 2;

        return new Position(centerX, centerY);
    }

    public void connectRooms(Position roomA, Position roomB, TETile[][] tiles) {
        Position yPoint;
        Position xPoint;
        int disX;
        int disY;

        if (roomA.x - roomB.x > 0) {
            xPoint = roomB;
            disX = roomA.x - roomB.x;
        } else {
            xPoint = roomA;
            disX = roomB.x - roomA.x;
        }

        if (roomA.y - roomB.y > 0) {
            yPoint = roomB;
            disY = roomA.y - roomB.y;
        } else {
            yPoint = roomA;
            disY = roomB.y - roomA.y;
        }

        //Edge Case
        if ((roomB.y - roomA.y < 0) && (roomB.x - roomA.x < 0)) {
            disY = roomA.y - roomB.y;
            disX = roomA.x - roomB.x;
            xPoint = roomB;
            yPoint = new Position(roomB.x + disX, roomB.y);
        } else if ((roomA.y - roomB.y < 0) && (roomA.x - roomB.x < 0)) {
            disY = roomB.y - roomA.y;
            disX = roomB.x - roomA.x;
            xPoint = roomA;
            yPoint = new Position(roomA.x + disX, roomA.y);
        }


        createHallwayY(disY, tiles, yPoint);
        createHallwayX(disX, tiles, xPoint);

    }

    public void createHallwayX(int width, TETile[][] tiles, Position p) {
        for (int i = 0; i <= width; i++) {

            if (!tiles[p.x + i][p.y + 1].equals(Tileset.FLOOR)) {
                tiles[p.x + i][p.y + 1] = Tileset.WALL;
            }

            tiles[p.x + i][p.y] = Tileset.FLOOR;

            if (!tiles[p.x + i][p.y - 1].equals(Tileset.FLOOR)) {
                tiles[p.x + i][p.y - 1] = Tileset.WALL;
            }
        }
    }

    public void createHallwayY(int length, TETile[][] tiles, Position p) {
        for (int i = 0; i <= length; i++) {

            if (!tiles[p.x + 1][p.y + i].equals(Tileset.FLOOR)) {
                tiles[p.x + 1][p.y + i] = Tileset.WALL;
            }

            tiles[p.x][p.y + i] = Tileset.FLOOR;

            if (!tiles[p.x - 1][p.y + i].equals(Tileset.FLOOR)) {
                tiles[p.x - 1][p.y + i] = Tileset.WALL;
            }
        }
    }
}
