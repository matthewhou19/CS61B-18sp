package byog.WorldGnerator2D;

import byog.TileEngine.TETile;

public interface container {
    void draw(TETile[][] world);
    boolean isOverlap(container c);
    boolean separate(container c);
}
