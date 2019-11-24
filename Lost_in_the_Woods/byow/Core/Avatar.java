package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Avatar extends Character {

    int health = 3;
    private String[] directions = new String[]{"North", "South", "East", "West"};
    private int directionIndex;
    private String direction = "North";

    public Avatar(TETile[][]tiles, Position p, TETile image) {
        super(tiles, p, image);
    }

    public void changeDirection() {
        directionIndex = (directionIndex + 1) % directions.length;
        direction = getDirection();
    }

    public String getDirection() {
        return directions[directionIndex];
    }



    public void deductHealth() {
        health -= 1;
    }


    public void zombieSurroundingK(TETile[][]tiles) {
        if (tiles[this.x][this.y + 1].equals(Tileset.ZOMBIE)) {
            deductHealth();
            TouchedFrame touchedFrame = new TouchedFrame(this.health);
            touchedFrame.activate();
        }
        if (tiles[this.x][this.y - 1].equals(Tileset.ZOMBIE)) {
            deductHealth();
            TouchedFrame touchedFrame = new TouchedFrame(this.health);
            touchedFrame.activate();
        }
        if (tiles[this.x + 1][this.y].equals(Tileset.ZOMBIE)) {
            deductHealth();
            TouchedFrame touchedFrame = new TouchedFrame(this.health);
            touchedFrame.activate();
        }
        if (tiles[this.x - 1][this.y].equals(Tileset.ZOMBIE)) {
            deductHealth();
            TouchedFrame touchedFrame = new TouchedFrame(this.health);
            touchedFrame.activate();
        }
    }


    public void zombieSurrounding(TETile[][]tiles) {
        if (tiles[this.x][this.y + 1].equals(Tileset.ZOMBIE)) {
            deductHealth();
        }
        if (tiles[this.x][this.y - 1].equals(Tileset.ZOMBIE)) {
            deductHealth();
        }
        if (tiles[this.x + 1][this.y].equals(Tileset.ZOMBIE)) {
            deductHealth();
        }
        if (tiles[this.x - 1][this.y].equals(Tileset.ZOMBIE)) {
            deductHealth();
        }


    }

    public boolean checkZombieAhead(TETile[][]tiles) {
        if (direction.equals("North")) {
            int i = 0;
            while (!tiles[this.x][this.y + i].equals(Tileset.WALL) && i < 5) {
                if (tiles[this.x][this.y + i].equals(Tileset.ZOMBIE)) {
                    System.out.println("North");
                    return true;
                }
                i++;
            }
        } else if (direction.equals("South")) {
            int i = 0;
            while (!tiles[this.x][this.y - i].equals(Tileset.WALL) && i < 5) {
                if (tiles[this.x][this.y - i].equals(Tileset.ZOMBIE)) {
                    System.out.println("South");
                    return true;
                }
                i++;
            }
        } else if (direction.equals("East")) {
            int i = 0;
            while (!tiles[this.x + i][this.y ].equals(Tileset.WALL) && i < 5) {
                if (tiles[this.x + i][this.y ].equals(Tileset.ZOMBIE)) {
                    System.out.println("East");
                    return true;
                }
                i++;
            }
        } else if (direction.equals("West")) {
            int i = 0;
            while (!tiles[this.x - i][this.y ].equals(Tileset.WALL) && i < 5) {
                if (tiles[this.x - i][this.y ].equals(Tileset.ZOMBIE)) {
                    System.out.println("West");
                    return true;
                }
                i++;
            }
        }

        System.out.println("Nope");
        return false;
    }

    public void shootZombie(TETile[][] tiles) {
        if (direction.equals("North")) {
            int i = 0;
            while (i < 5) {
                if (tiles[this.x][this.y + i].equals(Tileset.ZOMBIE)) {
                    tiles[this.x][this.y + i] = Tileset.FLOOR;
                }
                i++;
            }

        } else if (direction.equals("South")) {
            int i = 0;
            while (i < 5) {
                if (tiles[this.x][this.y - i].equals(Tileset.ZOMBIE)) {
                    tiles[this.x][this.y - i] = Tileset.FLOOR;
                }
                i++;
            }

        } else if (direction.equals("East")) {
            int i = 0;
            while (i < 5) {
                if (tiles[this.x + i][this.y].equals(Tileset.ZOMBIE)) {
                    tiles[this.x + i][this.y] = Tileset.FLOOR;
                }
                i++;
            }

        } else if (direction.equals("West")) {
            int i = 0;
            while (i < 5) {
                if (tiles[this.x - i][this.y].equals(Tileset.ZOMBIE)) {
                    tiles[this.x - i][this.y] = Tileset.FLOOR;
                }
                i++;
            }
        }

    }



}
