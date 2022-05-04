package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Room implements Element{
    private final Position p;
    private final int width;
    private final int height;
    private final TETile t;
    // every random room generation must have off = 2
    private final static int off = 1;

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

    // TODO
    public static Room randomRoom(Random random, Position sp, int w, int h)  {
        if (w <= off || h <= off) return null;
        int px = RandomUtils.uniform(random, w / 2);
        int py = RandomUtils.uniform(random, h/ 2);
        Position p = new Position(sp.getX() + px, sp.getY() + py);
        int pw = RandomUtils.uniform(random, 1, w - px + 1);
        int ph = RandomUtils.uniform(random, 1, h - py + 1);
        return new Room(p, pw, ph);

    }

    public static Room randomRoom(Position sp, int w, int h) {
        Random r =new Random();
        return randomRoom(r, sp, w, h);
    }

    // TODO
    @Override
    public Map<Position, TETile> output() {
        Map<Position, TETile> map = new HashMap<>();
        int x = this.p.getX();
        int y = this.p.getY();
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                map.put(new Position(x + i, y + j), this.t);
            }
        }
        return map;
    }




    /** test for the room class
       */
    public static void main(String[] args) {
        long seed = 221000;
        int w = 10;
        int h = 10;
        creatRandomRoomInAWorldGenerator(seed, w, h);

    }

    private static void creatRandomRoomInAWorldGenerator(long seed, int w, int h) {
        WorldGenerator wg = new WorldGenerator(seed, w, h);
        Room  room = Room.randomRoom(new Position(0, 0), w, h);
        wg.addElements(room);
        TETile[][] world = wg.outputWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(w, h);
        ter.renderFrame(world);
    }

}
