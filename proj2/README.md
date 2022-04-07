# CS61BYOG ANALYST

## Starter files

### Package TileEngine

#### TERenderer.java

```java
private static final int TILE_SIZE = 16;
private int width;
private int height;
private int xOffset;
private int yOffset;
```

* `public void initialize(int w, int h, int xOff, int yOff);`the xOff and yOff parameters will change where the renderFrame method starts drawing.    **For example,** if you select w = 60, h = 30, xOff = 3, yOff = 4 and then call renderFrame with a TETile[50][25] array, the renderer will leave 3 tiles blank on the left, 7 tiles blank on the right, 4 tiles blank on the bottom, and 1 tile blank on the top.
* `public void initialize(int w, int h);`  Initializes StdDraw parameters and launches the StdDraw window. w and h are thewidth and height of the world in number of tiles. If the TETile[][] array that you pass to renderFrame is smaller than this, then extra blank space will be left on the right and top edges of the frame. For example, if you select w = 60 and h = 30, this method will create a 60 tile wide by 30 tile tall window. If you then subsequently call renderFrame with a TETile[50][25] array, it will leave 10 tiles blank on the right side and 5 tiles blank on the top side.
* `public void renderFrame(TETile[][] world);`Takes in a 2d array of TETile objects and renders the 2d array to the screen, starting from xOffset and yOffset.If the array is an NxM array, then the element displayed at positions would be as follows given in units of tiles.

  positions                               xOffset |xOffset+1|xOffset+2| .... |xOffset+world.length

  startY+world[0].length         [0][M-1] | [1][M-1] | [2][M-1] | .... | [N-1][M-1]

  ...                                              ......  |  ......        |  ......         | .... | ......

  startY+2                                    [0][2]  |    [1][2]  |  [2][2]  | .... | [N-1][2]

  startY+1                                       [0][1]  |  [1][1]  |  [2][1]  | .... | [N-1][1]

  startY                                          [0][0]  |  [1][0]  |  [2][0]  | .... | [N-1][0]

#### TETile.java

##### Constructor

```java
private final char character; // Do not rename character or the autograder will break.
private final Color textColor;
private final Color backgroundColor;
private final String description;
private final String filepath;
```

* `public TETile(char character, Color textColor, Color backgroundColor, String description, String filepath)`
* `public TETile(char character, Color textColor, Color backgroundColor, String description)`
* `public TETile(TETile t, Color textColor)`

##### TETile[][]

* `public static TETile[][] copyOf(TETile[][] tiles)`
* `public static String toString(TETile[][] world)`

##### Others

* `public void draw(double x, double y)`
  draw the tetile on the stdraw window. The windon won't be displayed untill the `StdDraw.show()` is excuted.
* `public char character()`
* `public String description()`
* `public static TETile colorVariant(TETile t, int dr, int dg, int db, Random r)`
