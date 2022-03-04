package byog.WorldGnerator2D;

import byog.Core.RandomUtils;

import java.util.Random;

public class Position {
    private int x;
    private int y;
    public Position(int index1, int index2) {
        x = index1;
        y = index2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // a random position in the scale
    public static Position randomPosition(int xScale, int yScale, Random random) {
        int x = RandomUtils.uniform(random, xScale);
        int y = RandomUtils.uniform(random, yScale);
        return new Position(x, y);
    }
}
