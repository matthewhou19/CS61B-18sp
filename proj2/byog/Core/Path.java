package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class Path implements Element{
    Set<Position> set;
    TETile t;

    public Path(Random random, Position p1, Position p2) {
        t = Tileset.FLOOR;
        set = new HashSet<>();
        if (p1 == null) return;
        if (p1.equals(p2)){
            set.add(p1);
        } else {
            int w = Math.abs(p1.getX() - p2.getX());
            int h = Math.abs(p1.getY() - p2.getY());
            int diffx = p2.getX() - p1.getX();
            int diffy = p2.getY() - p1.getY();
            int ox = p1.getX();
            int oy = p1.getY();
            if (w > h) {
                int x0 = RandomUtils.uniform(random, w + 1);

                for(int i = 0; i < x0; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    ox = ox + diffx / w;
                }

                for (int i = 0; i < h + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    oy = oy + diffy / h;
                }

                for (int i = x0; i < w + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    ox = ox + diffx / w;
                }


            } else {
                int y0 = RandomUtils.uniform(random, h + 1);
                for (int i = 0; i < y0; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    oy = oy + diffy / h;
                }
                for (int i = 0; i < w + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    ox = ox + diffx / w;
                }

                for (int i = y0; i < h + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    oy = oy + diffy / h;
                }
            }
        }
    }
    public Path(Random random, Position p1, Position p2, TETile t) {
        this.t = t;
        set = new HashSet<>();
        if (p1 == null) return;
        if (p1.equals(p2)){
            set.add(p1);
        } else {
            int w = Math.abs(p1.getX() - p2.getX());
            int h = Math.abs(p1.getY() - p2.getY());
            int diffx = p2.getX() - p1.getX();
            int diffy = p2.getY() - p1.getY();
            int ox = p1.getX();
            int oy = p1.getY();
            if (w > h) {
                int x0 = RandomUtils.uniform(random, w + 1);

                for(int i = 0; i < x0; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    ox = ox + diffx / w;
                }

                for (int i = 0; i < h + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    oy = oy + diffy / h;
                }

                for (int i = x0; i < w + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    ox = ox + diffx / w;
                }


            } else {
                int y0 = RandomUtils.uniform(random, h + 1);
                for (int i = 0; i < y0; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    oy = oy + diffy / h;
                }
                for (int i = 0; i < w + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    ox = ox + diffx / w;
                }

                for (int i = y0; i < h + 1; i++) {
                    Position np = new Position(ox, oy);
                    set.add(np);
                    oy = oy + diffy / h;
                }
            }
        }

    }

    public Position getKeyPoint(Random random) {
        if (this.set == null || this.set.size() == 0) return null;
        int k = RandomUtils.uniform(random, this.set.size());
        int i = 0;
        for (Position p : this.set) {
            if (i == k) {
                return p;
            }
            i++;
        }
        return null;
    }


    // TODO
    @Override
    public Map<Position, TETile> output() {
        Map<Position, TETile> map = new HashMap<>();
        for (Position p : set) {
            map.put(p, t);
        }
        return map;
    }

    public static void main(String[] args) {
        long seed = 2210000;
        int w = 50;
        int h = 80;
        Position p1 = new Position( 1, 1);
        Position p2 = new Position(40, 70);
        Position p3 = new Position(5, 75);


        Path path1 = new Path(new Random(), p1, p2);
        Position p0 = path1.getKeyPoint(new Random());
        Path path2 = new Path(new Random(), p0, p3);


        WorldGenerator wg = new WorldGenerator(seed, w, h);

        wg.addElements(path1);
        wg.addElements(path2);


        TETile[][] world = wg.outputWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(w, h);
        ter.renderFrame(world);
    }

}
