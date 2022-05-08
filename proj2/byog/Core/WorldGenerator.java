package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;
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

    public Random getRandom() {
        return this.random;
    }


    /**
     * TODO  COMPLETE THE randomBSPWorld method : use BSP ALGO to generator a random world
     */


    public void randomBSPWorld() {
        BinarySpacePartition bsp = new BinarySpacePartition(random, new Position(1, 1), width - 2  , height - 2 );
        bsp.BSPGeneration(this);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (world[i][j] == Tileset.NOTHING) {
                    if (i - 1 >= 0 && world[i - 1][j] == Tileset.FLOOR) {
                        world[i][j] = Tileset.WALL;
                    }
                    if (i + 1 < width && world[i + 1][j] == Tileset.FLOOR) {
                        world[i][j] = Tileset.WALL;
                    }
                    if (j - 1 >= 0 && world[i][j - 1] == Tileset.FLOOR) {
                        world[i][j] = Tileset.WALL;
                    }
                    if (j + 1 < height && world[i][j + 1] == Tileset.FLOOR) {
                        world[i][j] = Tileset.WALL;
                    }

                }
            }
        }


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

    public void addElements(Element e) {
        if (e == null) return;
        Map<Position, TETile> map = e.output();
        if (map == null) return;
        for (Position p : map.keySet()) {
            world[p.getX()][p.getY()] = map.get(p);
        }
    }

    /**  return a TETILE[][]
    */
    public TETile[][] outputWorld(){
        return world;
    }

    public static void main(String[] args) {
        long seed = 14566;
        int w = 80;
        int h = 50;
        TETile t = Tileset.MOUNTAIN;

        //WorldGenerator wg = new WorldGenerator(seed, w, h, t);
        WorldGenerator wg = new WorldGenerator(seed, w, h);
        wg.randomBSPWorld();

        TETile[][] world = wg.outputWorld();

        TERenderer ter = new TERenderer();
        ter.initialize(w, h);
        ter.renderFrame(world);
    }
}
