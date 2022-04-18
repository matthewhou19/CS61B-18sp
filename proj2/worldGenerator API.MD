## Structure

### General

* class WorldGenerator   //  generating a world, which can be use by different ALGO
* BinarySpacePartition  // as a tool to use BSP ALGO to generate a world

### Foundation

* class Position
* interface Element
* class Room
* class Path

## API

### class WorldGenerator

#### private variable

```java
private Random random;
private int width;
private int height;
private TETile[][] world;
```

#### Constructor

* `public WorldGenerator(long seed, int width, int height, TETile t)` generate a world with TETile  t.
* `public WorldGenerator(long seed, int width, int height)` generate a world with TETile  t.

#### Generating ALGO

* `public void randomBSPWorld()`  use BSP ALGO to generator a random world

#### Others

* `public TETile[][] outputWorld();` return the world as a TETILE[][]
* `public static void main(String[] args);` for test
