package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int size;
    private WeightedQuickUnionUF uf;
    private final static  int blocked = 0;
    private final  static int open = 1;
    private int count;


    public Percolation(int N) {
        count = 0;
        size = N;
        if (N <= 0) {
            throw new IllegalArgumentException("N must larger than 0!");
        }
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = blocked;
            }
        }
    }                // create N-by-N grid, with all sites initially blocked
    public void open(int row, int col)  {
        if (isValid(row, col)) {
            if (!isOpen(row, col)) {
                count++;
                grid[row][col] = open;
                if (row == 0) {
                    uf.union(0, ufIndex(row, col));
                }
                if (row == size - 1) {
                    uf.union(1, ufIndex(row, col));
                }

                if (isValid(row - 1, col) && isOpen(row - 1, col)) {
                    uf.union(ufIndex(row, col), ufIndex(row - 1, col));
                }

                if (isValid(row + 1, col)  && isOpen(row + 1, col)) {
                    uf.union(ufIndex(row, col), ufIndex(row + 1, col));
                }
                if (isValid(row, col - 1) && isOpen(row, col - 1)) {
                    uf.union(ufIndex(row, col), ufIndex(row, col - 1));
                }

                if (isValid(row, col + 1) && isOpen(row, col + 1)) {
                    uf.union(ufIndex(row, col), ufIndex(row, col + 1));
                }

            }
        } else {
            throw new IndexOutOfBoundsException(row + " "+ col +" " +"out of bound +" + size );
        }

    }   // open the site (row, col) if it is not open already
    public boolean isOpen(int row, int col) {
        if (isValid(row, col)) {
            return grid[row][col] == open;
        } else {
            throw new IndexOutOfBoundsException(row + " "+ col +" " +"out of bound +" + size );
        }

    } // is the site (row, col) open?
    public boolean isFull(int row, int col) {
        if(isValid(row, col)) {
            return uf.connected(0, ufIndex(row, col));
        } else {
            throw new IndexOutOfBoundsException(row + " "+ col +" " +"out of bound +" + size );
        }

    } // is the site (row, col) full?
    public int numberOfOpenSites() {

        return count;
    }          // number of open sites
    public boolean percolates() {

        return uf.connected(0, 1);
    }// does the system percolate?

    private int ufIndex(int i, int j) {
        if (isValid(i, j)) {
            return  i * size + j + 2;
        }
        throw new IndexOutOfBoundsException(i + " "+ j +" " +"out of bound +" + size );
    }

    private boolean isValid(int i, int j) {
        if (i < 0 || i >= size) {
            return false;
        }
        if (j < 0 || j >= size) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }  // use for unit testing (not required)

}
