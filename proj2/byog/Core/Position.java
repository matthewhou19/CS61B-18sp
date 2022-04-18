package byog.Core;

import java.util.Random;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position randomPosition(Random random, int x1, int x2, int y1, int y2) {
        int x = RandomUtils.uniform(random,x1, x2 + 1);
        int y = RandomUtils.uniform(random, y1, y2 + 1);


        // TODO HAVENT FINISHED
        return new Position(x, y);
    }

    public static Position randomPosition(Random random, int w, int h) {
        return randomPosition(random,0, w, 0, h);
    }

    public static Position randomPosition(int x1, int x2, int y1, int y2) {
        Random r = new Random();
        return randomPosition(r, x1, x2, y1, y2);
    }

    public static Position randomPosition(int w, int h) {
        return randomPosition(0, w, 0, h);
    }

    @Override
    public boolean equals(Object x) {
        if (x == this) return true;
        if (x == null) return false;
        if (x.getClass() != this.getClass()) return false;
        Position a = (Position) x;
        return a.getX() == this.getX() && a.getY() == this.getY();
    }

    @Override
    public int hashCode(){
        return this.x * 99999 + y;
    }


}
