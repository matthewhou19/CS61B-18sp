package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    public static void creatHex(TETile[][] world, Position p, int s) {
        // draw five vertical hex
        drawVerticalHex(world, verticalPosition(p, s, 0), s, 3);
        drawVerticalHex(world, verticalPosition(p, s, 1), s, 4);
        drawVerticalHex(world, verticalPosition(p, s, 2), s, 5);
        drawVerticalHex(world, verticalPosition(p, s, 3), s, 4);
        drawVerticalHex(world, verticalPosition(p, s, 4), s, 3);
    }

    private static Position verticalPosition (Position p, int s, int v) {
        int x = p.getX() + v * (s * 2 - 1);
        int y = 0;
        switch (v) {
            case 0: return new Position(x, p.getY() + 2 * s);
            case 1: return new Position(x, p.getY() + s);
            case 2: return new Position(x, p.getY());
            case 3: return new Position(x, p.getY() + s);
            default: return new Position(x, p.getY() + 2 * s);
        }

    }

    private static void drawVerticalHex(TETile[][] world, Position p, int s, int n) {
        for (int i = 0; i < n; i++) {
            TETile t = randomTile();
            Position pi = new Position(p.getX(), p.getY() + 2 * s * i);
            addHexagon(world, pi, s, t);
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.MOUNTAIN;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            default: return Tileset.GRASS;
        }
    }

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int row = p.getY(); row< p.getY() + 2 * s; row++) {
            int rowlength = hexRowLength(s, row - p.getY());
            int rowOff = hexRowOff(s, row - p.getY());
            int col = p.getX() + rowOff;
            for (int  i = 0; i < rowlength; i++) {
                world[col][row] = t;
                col++;
            }
        }

    }

    private static int hexRowLength(int s, int r) {
        if (s < 2) {
            String massage = "Hexagon size cannot less than 2, actural size: " + s;
            throw new IllegalArgumentException(massage);
        }

        if (r < 0) {
            throw  new IllegalArgumentException("Henagon row can not be negative, actural row: " + r);
        }

        if (r >= s * 2) {
            throw new IllegalArgumentException("Henagon row must less than double s, actural row: " + r);
        }

        if(r < s) {
            return s + 2 * r;
        }else {
            return 5 * s - 2 * r - 2;
        }
    }

    private static int hexRowOff(int s, int r) {
        if (s < 2) {
            String massage = "Hexagon size cannot less than 2, actural size: " + s;
            throw new IllegalArgumentException(massage);
        }

        if (r < 0) {
            throw  new IllegalArgumentException("Henagon row can not be negative, actural row: " + r);
        }

        if (r >= s * 2) {
            throw new IllegalArgumentException("Henagon row must less than double s, actural row: " + r);
        }
        if(r < s) {
            return s - r - 1;
        }else {
            return r - s;
        }
    }

    @Test
    public void testHexRowLength() {
        assertEquals(hexRowLength(2, 0), 2);
        assertEquals(hexRowLength(2, 1), 4);
        assertEquals(hexRowLength(2, 2), 4);
        assertEquals(hexRowLength(2, 3), 2);
        assertEquals(hexRowLength(5, 0), 5);
        assertEquals(hexRowLength(5, 1), 7);
        assertEquals(hexRowLength(5, 2), 9);
        assertEquals(hexRowLength(5, 4), 13);
        assertEquals(hexRowLength(5, 5), 13);
        assertEquals(hexRowLength(5, 6), 11);
        assertEquals(hexRowLength(5, 9), 5);
    }

    @Test
    public void testHexRowOff() {
        assertEquals(hexRowOff(2, 0), 1);
        assertEquals(hexRowOff(2, 1), 0);
        assertEquals(hexRowOff(2, 2), 0);
        assertEquals(hexRowOff(2, 3), 1);
        assertEquals(hexRowOff(5, 0), 4);
        assertEquals(hexRowOff(5, 1), 3);
        assertEquals(hexRowOff(5, 4), 0);
        assertEquals(hexRowOff(5, 5), 0);
        assertEquals(hexRowOff(5, 6), 1);
        assertEquals(hexRowOff(5, 7), 2);
        assertEquals(hexRowOff(5, 9), 4);
    }
}
