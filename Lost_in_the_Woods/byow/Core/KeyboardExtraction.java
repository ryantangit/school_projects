package byow.Core;


import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;


public class KeyboardExtraction implements InputExtraction {



    private static boolean SHOW_TYPED_CHAR = false;
    int seedDigits = 0;
    boolean gameInProgress = false;
    StartingScreen startingScreen;

    public KeyboardExtraction() {

        startingScreen = new StartingScreen(Engine.WIDTH, Engine.HEIGHT);
        startingScreen.activate();

    }

    @Override
    public char getNext() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (SHOW_TYPED_CHAR) {
                    System.out.print(c);
                }
                return c;
            }
        }
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    public String startAction() {
        String returned = "";
        while (hasNext()) {
            char getNext = getNext();
            if (getNext == 'n') {
                returned = getSeed();
                break;
            } else if (getNext == 'l') {
                returned = ("load");
                break;
            } else if (getNext == 'q') {
                returned = "quit";
                break;
            } else if (getNext == 'b') {
                returned = null;
                startingScreen.happyBirthdayScreen();
            }
        }
        return returned;
    }

    public String getSeed() {
        //seedScreen()
        startingScreen.seedScreen();
        String seed = "n";
        char lastChar = '-';
        while (hasNext() && lastChar != 's') {
            char next = getNext();
            if (next == 's' && seedDigits == 0) {
                System.out.println("boop");
            } else if (seedDigits > 18 && next != 's') {
                System.out.println("boop");
            } else if (next == 's' || next == '0' || next == '1'
                    || next == '2' || next == '3' || next == '4'
                    || next == '5' || next == '6' || next == '7'
                    || next == '8' || next == '9') {

                lastChar = next;
                seed += next;
                seedDigits += 1;

                startingScreen.updateSeed(next);
                StdDraw.show();
            }
        }
        return seed;
    }

    public void setGameInProgress(boolean status) {
        gameInProgress = status;
    }


    public TETile[][] charAction(Avatar character, TETile[][] tiles,
                                  Load load, Zombie[] zombies, Random seedRandom) {
        char getNext = getNext();
        if (getNext == 'w') {
            tiles = character.moveUp(tiles);
            upDate(load, getNext, zombies, tiles, character, seedRandom);

        } else if (getNext == 's') {
            tiles = character.moveDown(tiles);
            upDate(load, getNext, zombies, tiles, character, seedRandom);


        } else if (getNext == 'a') {
            tiles = character.moveLeft(tiles);
            upDate(load, getNext, zombies, tiles, character, seedRandom);


        } else if (getNext == 'd') {
            tiles = character.moveRight(tiles);
            upDate(load, getNext, zombies, tiles, character, seedRandom);

        } else if (getNext == 'f') {
            try {
                Audio gun = new Audio("./gunshot.wav");
                BattleFrame battleFrame = new BattleFrame();
                if (character.checkZombieAhead(tiles)) {
                    Audio zombie = new Audio("./zombiepain.wav");
                    character.shootZombie(tiles);
                    battleFrame.activate();
                    upDate(load, getNext, zombies, tiles, character, seedRandom);
                    zombie.pause();
                } else {
                    battleFrame.missActivate();
                    upDate(load, getNext, zombies, tiles, character, seedRandom);
                }
                gun.pause();

            } catch (LineUnavailableException ex) {
                System.out.println("Error with sound");
            } catch (IOException ex) {
                System.out.println("Error with sound");
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("Error with sound");
            }
        } else if (getNext == 'j') {
            character.changeDirection();
            TurningFrame tf = new TurningFrame(character.getDirection());
            tf.activate(character.checkZombieAhead(tiles));
            upDate(load, getNext, zombies, tiles, character, seedRandom);
        } else if (getNext == ':') {
            if (getNext() == 'q') {
                load.saveLoadAction();
                System.exit(0);
            }
        }

        return tiles;
    }


    private void upDate(Load load, char getNext, Zombie[] zombies,
                        TETile[][] tiles, Avatar character, Random seedRandom) {

        load.addLoadAction(getNext);
        for (Zombie zombie: zombies) {
            if (zombie.alive) {
                zombie.action(character, tiles, seedRandom);
            }
        }
        character.zombieSurroundingK(tiles);

        if (character.health <= 0) {
            DeadFrame deadFrame = new DeadFrame();
            try {
                Audio lost = new Audio("./lost.wav");
                deadFrame.activate();
                lost.pause();
            } catch (LineUnavailableException ex) {
                System.out.println("Error with sound");
            } catch (IOException ex) {
                System.out.println("Error with sound");
            } catch (UnsupportedAudioFileException ex) {
                System.out.println("Error with sound");
            }
            System.exit(0);
        }

    }

}
