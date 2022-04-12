package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    private Random random;
    private int width;
    private int height;
    private TETile[][] world;



    public WorldGenerator(long seed, int width, int height, TETile t) {
        this.width = width;
        this.height = height;
        this.random = new Random(seed);
        world = new TETile[width][height];
        cleanWorldWith(t);
    }

    public WorldGenerator(long seed, int width, int height) {
        this.width = width;
        this.height = height;
        this.random = new Random(seed);
        world = new TETile[width][height];
        cleanWorldWith(Tileset.NOTHING);
    }

    /**
     * TODO  COMPLETE THE randomBSPWorld method : use BSP ALGO to generator a random world
     */


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

    public static void main(String[] args) {
        int seed = 14566;

    }


}
