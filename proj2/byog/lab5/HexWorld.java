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
}
