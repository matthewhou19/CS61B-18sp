package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Map;

public class Room implements Element{
    private final Position p;
    private final int width;
    private final int height;
    private final TETile t;

    public Room(Position p, int width, int height) {
        this.p = p;
        this.width = width;
        this.height = height;
        this.t = Tileset.FLOOR;
    }

    public Room(Position p, int width, int height, TETile T) {
        this.p = p;
        this.width = width;
        this.height = height;
        this.t = T;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Position getPosition() {
        return p;
    }

    public int getArea(){
        return height * width;
    }


    @Override
    public Map<Position, TETile> output() {
        return null;
    }


    /** test for the room class
       */
    public static void main(String[] args) {

    }

}
