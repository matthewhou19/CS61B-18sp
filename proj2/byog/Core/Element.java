package byog.Core;

import byog.TileEngine.TETile;

import java.util.Map;


public interface Element {

    public Map<Position, TETile> output();
}
