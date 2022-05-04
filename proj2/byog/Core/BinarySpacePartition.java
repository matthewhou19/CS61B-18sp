package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;

import java.util.*;

public class BinarySpacePartition {
    private int width;
    private int hight;
    private BinarySpacePartition left;
    private BinarySpacePartition right;
    private final static int minSpace = 10;
    private Position p;
    private Path path;
    private Room room;
    private Random random;


    // constructor
    public BinarySpacePartition(Random r,Position position, int w, int h) {

        this.random = r;
        this.width = w;
        this.hight = h;
        this.p = position;

        if (width > minSpace && hight > minSpace) {
            if (width > hight) {
                int w0 = RandomUtils.uniform(random, 3, width);
                this.left = new BinarySpacePartition(random, p, w0, h);
                this.right = new BinarySpacePartition(random, new Position(p.getX() + w0,p.getY()), w - w0, h);
            } else {
                int h0 = RandomUtils.uniform(random, 3, h);
                this.left = new BinarySpacePartition(random, p, w, h0);
                this.right = new BinarySpacePartition(random, new Position(p.getX(),p.getY() + h0), w, h - h0);
            }
        }

    }

    public void createRooms() {
        if (left == null && right == null) {
            room = new Room(new Position(p.getX() + 1, p.getY() + 1), width - 2 , hight - 2);
        }

        if (left != null) {
            left.createRooms();
        }
        if (right != null) {
            right.createRooms();
        }

    }




    public List<Room> getAllRooms() {
        List<Room> ans = new ArrayList<>();
        if (left != null) {
            List<Room> leftRooms = left.getAllRooms();
            for (Room room : leftRooms) {
                ans.add(room);
            }
        }
        if ( right!= null) {
            List<Room> rightRooms = right.getAllRooms();
            for (Room room : rightRooms) {
                ans.add(room);
            }
        }

        if (this.room != null) {
            ans.add(this.room);
        }
        return ans;
    }



    public static void main(String[] args) {
        long seed = 2210000;
        int w = 80;
        int h = 50;
        Random random = new Random(seed);
        /**
        BinarySpacePartition bsp = new BinarySpacePartition(random, new Position(0, 0), w, h);
        bsp.createRooms();
        List<Room> listOfRooms = bsp.getAllRooms();
        **/

        WorldGenerator wg = new WorldGenerator(seed, w, h);
        wg.randomBSPWorld();

        /**
        for (Room room : listOfRooms) {
            wg.addElements(room);
        }
        */

        TETile[][] world = wg.outputWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(w, h);
        ter.renderFrame(world);
    }



}
