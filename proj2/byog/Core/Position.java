package byog.Core;

import java.awt.*;

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
    public static Position randomPosition(int xScale, int yScale) {
        return null;
    }
}
