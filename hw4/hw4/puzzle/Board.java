package hw4.puzzle;

public class Board implements WorldState{

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    private int size;
    private int[][] tiles;

    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = tiles;
    }
    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i > size - 1 || j > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size() {
        return size;
    }
    public Iterable<WorldState> neighbors() {
        return null;
    }
    private int oughToBe(int i, int j) {
        if (i == size - 1 && j == size - 1) return 0;
        return i * size + j + 1;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != oughToBe(i, j)) {
                    count++;
                }
            }
        }
        if (count != 0) return  count - 1;
        return 0;
    }
    public int manhattan() {
        
        return 0;
    }
    public int estimatedDistanceToGoal() {
        return 0;
    }
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y.getClass() != this.getClass()) return false;
        if ((((Board) y).size() != size())) return false;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <size; j++) {
                if (((Board) y).tileAt(i, j) != this.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }






    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
