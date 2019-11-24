package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;
public class Zombie extends Character {

    boolean alive = true;

    public Zombie(TETile[][]tiles, Position p, TETile image) {
        super(tiles, p, image);
    }

    public void action(Avatar character, TETile[][] tiles, Random seedRandom) {
        checkAlive(tiles);
        if (alive) {
            if (detected(tiles)) {
                System.out.println("DETECTED!");
            } else {
                wander(tiles, seedRandom);
            }
        }
    }

    public void wander(TETile[][] tiles, Random seedRandom) {
        int direction = seedRandom.nextInt(4);
        switch (direction) {
            case 0: this.moveUp(tiles);
                    break;
            case 1: this.moveDown(tiles);
                    break;
            case 2: this.moveLeft(tiles);
                    break;
            case 3: this.moveRight(tiles);
                    break;
            default: break;

        }

    }

    public boolean detected(TETile[][] tiles) {
        boolean detected = false;
        int left = 0;
        int right = 0;
        int up = 0;
        int down = 0;

        while (!tiles[this.x - left][this.y].equals(Tileset.WALL)) {
            if (tiles[this.x - left][this.y].equals(Tileset.AVATAR)) {
                moveLeft(tiles);
                moveLeft(tiles);
                return true;
            }
            left += 1;
        }

        while (!tiles[this.x + right][this.y].equals(Tileset.WALL)) {
            if (tiles[this.x + right][this.y].equals(Tileset.AVATAR)) {
                moveRight(tiles);
                moveRight(tiles);
                return true;
            }
            right += 1;
        }

        while (!tiles[this.x][this.y + up].equals(Tileset.WALL)) {
            if (tiles[this.x][this.y + up].equals(Tileset.AVATAR)) {
                moveUp(tiles);
                moveUp(tiles);
                return true;
            }
            up += 1;
        }

        while (!tiles[this.x][this.y - down].equals(Tileset.WALL)) {
            if (tiles[this.x][this.y - down].equals(Tileset.AVATAR)) {
                moveDown(tiles);
                moveDown(tiles);
                return true;
            }
            down += 1;
        }


        return detected;
    }

    public void checkAlive(TETile[][] tiles) {
        if (tiles[this.x][this.y].equals(Tileset.FLOOR)) {
            this.alive = false;
        }
    }





}
