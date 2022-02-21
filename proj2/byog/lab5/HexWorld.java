package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {


    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int x = p.getX(); x < 45; x += 1) {
            for (int y = p.getY(); y < 10; y += 1) {
                world[x][y] = t;
            }
        }
    }

    private static int hexRowLength(int s, int r) {

        if(r < s) {
            return s + 2 * r;
        }
        if (r > s) {
            return
        }

        return 0;
    }

    private static int hexRowOff(int s, int r) {
        return 0;
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
