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
        int x =


        // TODO HAVENT FINISHED
        return new Position(0, 0);
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
