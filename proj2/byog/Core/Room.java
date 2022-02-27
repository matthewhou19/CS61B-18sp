package byog.Core;

import byog.TileEngine.TETile;
import java.util.Random;

public class Room implements container{
    private Position p;
    private int[][] mirror;
    private Random r;
    int width;
    int height;
    // create a random size room in the specific position
    public void specificCreate(Position l, Random random) {
        p = l;
        randomCreate(random);
    }

    //create a random size room in the random position
    public void randomPositionCreate(int xScale, int yScale, Random random){
        randomCreate(random);
        p = Position.randomPosition(xScale - width, yScale - height, random);
    }




    // create a room with random size at the Position l
    private void randomCreate(Random random){
        r = random;
        width = RandomUtils.uniform(random, 3, 8);
        height = RandomUtils.uniform(random, 3, 5);
        mirror = new int[width][height];
        for (int i = 0; i < mirror.length; i++) {
            for (int j = 0; j < mirror[0].length; j++) {
                mirror[i][j] = 1;
            }
        }

        DrawLine.horizonLine(mirror, 0, 2);
        DrawLine.horizonLine(mirror, height - 1, 2);
        DrawLine.verticalLine(mirror, 0, 2);
        DrawLine.verticalLine(mirror,width - 1, 2);
    }






    @Override
    public void draw(TETile[][] world) {
        int originalX = p.getX();
        int originalY = p.getY();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (originalY > world[0].length - 1) break;
                world[originalX][originalY] = TileMap.getByIndex(mirror[i][j]);
                originalY++;
            }
            originalX++;
            if (originalX > world.length - 1) {
                break;}
            originalY = p.getY();
        }
    }

    @Override
    public boolean isOverlap(container c) {
        return false;
    }

    @Override
    public boolean separate(container c) {
        return false;
    }
}
