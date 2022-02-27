package byog.Core;

public class DrawLine {

    // draw a horizon line by using the TETile index t, from the left to right, at the y vertical index
    public static void horizonLine(int[][] mirror, int y, int t) {
        for (int i = 0; i < mirror.length; i++) {
            for (int j = 0;j < mirror[0].length; j++) {
                if (j == y) {
                    mirror[i][j] = t;
                }
            }
        }
    }

    public static void horizonLine(int[][] mirror, int x, int y, int l, int t) {

    }

    //draw a vertical line by using the TETile index t, from the top to bottom, at the y vertical index
    public static void verticalLine(int[][] mirror, int x, int t) {
        for (int i = 0;i < mirror[0].length; i++) {
            mirror[x][i] = t;
        }
    }

}
