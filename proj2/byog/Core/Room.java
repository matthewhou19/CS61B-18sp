package byog.Core;

import byog.TileEngine.TETile;

import java.util.Map;

public class Room implements Element{
    private Position p;
    private int width;
    private int height;

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
    public void main(String[] args) {

    }

}
