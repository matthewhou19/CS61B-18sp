package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    private Random random;
    private TETile[][] world;
    private int WIDTH;
    private int HEIGHT;
    public void instantiate(int s, int w, int h) {
        random = new Random(s);
        WIDTH = w;
        HEIGHT = h;
        // initial the world with nothing
       world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        TileMap map = new TileMap();

        Position p = new Position(10, 5);
        Room roomDemo= new Room();

        roomDemo.specificCreate(p, random);

        roomDemo.draw(world);

        Room randomPositionRoom = new Room();
        randomPositionRoom.randomPositionCreate(WIDTH, HEIGHT, random);
        randomPositionRoom.draw(world);

        for(int i = 0; i < 10; i++) {
            randomPositionRoom.randomPositionCreate(WIDTH, HEIGHT, random);
            randomPositionRoom.draw(world);
        }

        // get all the TETile needed for the world generation



    }

    public TETile[][] getWorld(){
        return world;
    }
}
