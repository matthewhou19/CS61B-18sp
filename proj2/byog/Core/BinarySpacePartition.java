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

    public void BSPGeneration(WorldGenerator wg) {
        this.createRooms();

        List<Room> rooms = this.getAllRooms();

        for (Room room : rooms) {
            wg.addElements(room);
        }
        this.path = creatPath();
        wg.addElements(this.path);

    }


    private void createRooms() {
        if (left == null && right == null) {
            //room = new Room(new Position(p.getX() + 1, p.getY() + 1), width - 2 , hight - 2);
            room = Room.randomRoom(random,new Position(p.getX() + 1, p.getY() + 1), width - 2 , hight - 2);
            if (room != null) {
                int sq = room.getArea();
                if (sq <=4) {
                    room = null;
                }
            }

        }

        if (left != null) {
            left.createRooms();
        }
        if (right != null) {
            right.createRooms();
        }

    }




    private List<Room> getAllRooms() {
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

    private Path creatPath() {
        if (this.left == null && this.right == null) {
            if (this.room == null) {
                return null;
            }
            return new Path(this.room.getPosition());
        }

        Path leftPath = null;
        Path rightPath = null;
        if (this.left != null) {
            leftPath = this.left.creatPath();
        }
        if (this.right != null) {
            rightPath = this.right.creatPath();
        }
        if (leftPath == null) {
            return rightPath;
        } else if (rightPath == null) {
            return leftPath;
        } else  {
            Path path = new Path(random, leftPath.getKeyPoint(random), rightPath.getKeyPoint(random));
            path.add(leftPath);
            path.add(rightPath);
            return path;
        }
    }



    public static void main(String[] args) {
        long seed = 231110000;
        int w = 80;
        int h = 50;

        WorldGenerator wg = new WorldGenerator(seed, w, h);
        wg.randomBSPWorld();

        TETile[][] world = wg.outputWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(w, h);
        ter.renderFrame(world);
    }



}
