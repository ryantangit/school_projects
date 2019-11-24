package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Character {

    TETile charImage;
    int x;
    int y;


    public Character(TETile[][]tiles, Position p, TETile image) {
        charImage = image;
        tiles[p.x][p.y] = image;
        this.x = p.x;
        this.y = p.y;

    }


    public TETile[][] moveUp(TETile[][] tiles) {
        if (tiles[this.x][this.y + 1].equals(Tileset.FLOOR)) {

            TETile temp = tiles[this.x][this.y + 1];
            int tempX = this.x;
            int tempY = this.y;

            tiles[this.x][this.y + 1] = charImage;
            this.y = this.y + 1;

            tiles[tempX][tempY] = temp;

        }
        return tiles;
    }

    public TETile[][] moveDown(TETile[][] tiles) {
        if (tiles[this.x][this.y - 1].equals(Tileset.FLOOR)) {

            TETile temp = tiles[this.x][this.y - 1];
            int tempX = this.x;
            int tempY = this.y;

            tiles[this.x][this.y - 1] = charImage;
            this.y = this.y - 1;

            tiles[tempX][tempY] = temp;

        }
        return tiles;
    }

    public TETile[][] moveLeft(TETile[][] tiles) {
        if (tiles[this.x - 1][this.y].equals(Tileset.FLOOR)) {

            TETile temp = tiles[this.x - 1][this.y];
            int tempX = this.x;
            int tempY = this.y;

            tiles[this.x - 1][this.y] = charImage;
            this.x = this.x - 1;

            tiles[tempX][tempY] = temp;

        }
        return tiles;
    }

    public TETile[][] moveRight(TETile[][] tiles) {
        if (tiles[this.x + 1][this.y].equals(Tileset.FLOOR)) {

            TETile temp = tiles[this.x + 1][this.y];
            int tempX = this.x;
            int tempY = this.y;

            tiles[this.x + 1][this.y] = charImage;
            this.x = this.x + 1;

            tiles[tempX][tempY] = temp;

        }
        return tiles;
    }


}
