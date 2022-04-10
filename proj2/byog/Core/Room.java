package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Map;

public class Room implements Element{
    private final Position p;
    private final int width;
    private final int height;
    private final TETile terrain = Tileset.FLOOR;

    public Room(Position p, int width, int height) {
        this.p = p;
        this.width = width;
        this.height = height;
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


    @Override
    public Map<Position, TETile> output() {
        return null;
    }


    /** test for the room class
       */
    public static void main(String[] args) {

    }

}
