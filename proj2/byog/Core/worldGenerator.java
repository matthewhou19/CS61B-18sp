package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class worldGenerator {
    private Random random;
    private int width;
    private int height;
    TETile[][] world;

    public worldGenerator(long seed, int width, int height) {
        this.width = width;
        this.height = height;
        this.random = new Random(seed);
        world = new TETile[width][height];
        cleanWorldWith(Tileset.FLOWER);
    }

    public void randomBSPWorld() {
        cleanWorldWith(Tileset.MOUNTAIN);
    }


    /* cover the world with one kind of TETile
    * */
    private void cleanWorldWith(TETile t) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = t;
            }
        }
    }
    /* cover the world with Tileset.NOTHING
    * */
    private void cleanWorld() {
        cleanWorldWith(Tileset.NOTHING);
    }


    /**  return a TETILE[][]
    */
    public TETile[][] outputWorld(){
        return world;
    }
}
