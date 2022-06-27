package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Map;

public class PercolationStats {
    int[] data;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        data = new int[T];
        for (int i = 0 ; i < T; i++) {
            data[i] = time(N, pf);
        }
    }   // perform T independent experiments on an N-by-N grid

    private int time(int N, PercolationFactory pf) {
        Percolation p = pf.make(N);
        int time = 0;
        while (!p.percolates()) {
            int i = StdRandom.uniform(N);
            int j = StdRandom.uniform(N);
            if (!p.isOpen(i, j)) {
                p.open(i, j);
                time ++;
            }
        }
        return time;
    }
    public double mean() {

        return StdStats.mean(data);
    }                                         // sample mean of percolation threshold
    public double stddev() {

        return StdStats.stddev(data);
    }                                        // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double t = Math.sqrt((double) data.length);

        return mean() - stddev() / t;
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double t = Math.sqrt((double) data.length);

        return mean() + stddev() / t;
    }                                // high endpoint of 95% confidence interval

}
