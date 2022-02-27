package byog.Core;
import byog.TileEngine.Tileset;
import byog.TileEngine.TETile;

import java.util.HashMap;
import java.util.Map;

public class TileMap {
    private static Map<Integer, TETile> map;

    public TileMap() {
        map = new HashMap<>();
        map.put(0, Tileset.NOTHING);
        map.put(1, Tileset.FLOOR);
        map.put(2, Tileset.WALL);
        map.put(3, Tileset.PLAYER);
    }

    public static TETile getByIndex(int i) {
        return map.get(i);
    }


}
