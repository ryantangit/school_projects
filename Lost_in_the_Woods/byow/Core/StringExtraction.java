package byow.Core;

import byow.TileEngine.TETile;

import java.util.Random;

public class StringExtraction implements InputExtraction {

    private String string;
    String seedString;
    private int index = 0;


    public StringExtraction(String s) {
        if (s.charAt(0) != 'n') {
            throw new IllegalArgumentException("How did you get here without having to press N?");
        } else {
            this.string = s;

        }
    }


    @Override
    public char getNext() {
        return string.charAt(index);

    }

    @Override
    public boolean hasNext() {
        if (index != string.length() - 1) {
            index += 1;
            return true;
        } else {
            return false;
        }
    }

    public Long seed() {
        String seedLong = "";
        this.seedString = "n";
        while (hasNext() && getNext() != 's') {
            char c = getNext();
            if (c != 'n') {
                seedLong += c;
                this.seedString += c;
            }
        }
        this.seedString += 's';
        return Long.parseLong(seedLong);
    }

    public String getSeedString() {
        return seedString;
    }


    public String seedAction() {
        String actionString = "";
        while (hasNext()) {
            actionString += getNext();
        }
        return  actionString;
    }

    public static TETile[][] charAction(Avatar character, TETile[][] tiles, char c,
                                         Load load, Zombie[] zombies, Random seedRandom) {
        char getNext = c;
        if (getNext == 'w') {
            tiles = character.moveUp(tiles);
            for (Zombie zombie : zombies) {
                zombie.action(character, tiles, seedRandom);
            }
            load.addLoadAction(getNext);
            character.zombieSurrounding(tiles);

            if (character.health <= 0) {
                System.out.println("You died");
            }
        } else if (getNext == 's') {
            tiles = character.moveDown(tiles);
            for (Zombie zombie : zombies) {
                zombie.action(character, tiles, seedRandom);
            }
            load.addLoadAction(getNext);
            character.zombieSurrounding(tiles);
            if (character.health <= 0) {
                System.out.println("You died");
            }
        } else if (getNext == 'a') {
            tiles = character.moveLeft(tiles);
            for (Zombie zombie : zombies) {
                zombie.action(character, tiles, seedRandom);
            }
            load.addLoadAction(getNext);
            character.zombieSurrounding(tiles);
            if (character.health <= 0) {
                System.out.println("You died");
            }
        } else if (getNext == 'd') {
            tiles = character.moveRight(tiles);
            for (Zombie zombie : zombies) {
                zombie.action(character, tiles, seedRandom);
            }
            load.addLoadAction(getNext);
            character.zombieSurrounding(tiles);
            if (character.health <= 0) {
                System.out.println("You died");
            }
        } else if (getNext == 'f') {
            if (character.checkZombieAhead(tiles)) {
                character.shootZombie(tiles);
                for (Zombie zombie : zombies) {
                    zombie.action(character, tiles, seedRandom);
                }
                load.addLoadAction(getNext);
                character.zombieSurrounding(tiles);
                if (character.health <= 0) {
                    System.out.println("You died");
                }
            } else {
                for (Zombie zombie : zombies) {
                    zombie.action(character, tiles, seedRandom);
                }
                load.addLoadAction(getNext);
                character.zombieSurrounding(tiles);
                if (character.health <= 0) {
                    System.out.println("You died");
                }
            }
        } else if (getNext == 'j') {
            character.changeDirection();
            System.out.println(character.getDirection());
            for (Zombie zombie : zombies) {
                zombie.action(character, tiles, seedRandom);
            }
            load.addLoadAction(getNext);
            character.zombieSurrounding(tiles);
            if (character.health <= 0) {
                System.out.println("You died");
            }
        }
        return tiles;
    }
}
