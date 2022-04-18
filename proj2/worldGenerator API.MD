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
* `public void addElements(Element e); `  add an element to the world

#### Others

* `public TETile[][] outputWorld();` return the world as a TETILE[][]
* `public static void main(String[] args);` for test

### class Position

#### private variable

```
private final int x;
private final int y;
```

#### Constructor

* `public Position (int x, int y);`

#### Others

* `public int getX()`
* `public int getY()`
* `public boolean equals(Object x)`
* `public int hashCode()`
* `public position randomPosition(Random random, int w, int y);`
* `public position randmomPosition(Random random, int x1, int x2, int y1, int y2)`;
* `public position randomPosition(int w, int y);`
* `public position randmomPosition(int x1, int x2, int y1, int y2)`;

### class Room

#### private variable

```java
private final Position p;
private final int width;
private final int height;
private final TETile t;
private final static int off = 2;
```

#### Constructor

* `public Room(Position p, int width, int height);`
* `public Room(Position p, int width, int height, TETile T);`

#### Others

* `public int getHeight();`
* `public int getWidth();`
* `public Position getPosition();`
* `public int getArea();`
* `public Map<Position, TETile> output();`
* `public static Room randomRoom(Random random, Position sp, int w, int h);`
* `public static Room randomRoom(Position sp, int w, int h);`
