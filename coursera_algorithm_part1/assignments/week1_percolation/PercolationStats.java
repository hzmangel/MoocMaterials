import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private int testCount = 0;
    private int matrixSize = 0;
    private int totalBlocks = 0;
    private Percolation pr;
    private double[] results;

    public PercolationStats(int N, int T) {     // perform T independent experiments on an N-by-N grid
        if ((N <= 0) || (T <= 0)) {
            throw new java.lang.IllegalArgumentException("Given params are less than or equal 0");
        }

        testCount = T;
        matrixSize = N;
        totalBlocks = N * N;
        results = new double[testCount];
        doTest(testCount);
    }

    public double mean() {                      // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() {                    // sample standard deviation of percolation threshold
        return StdStats.stddev(results);
    }

    public double confidenceLo() {              // low  endpoint of 95% confidence interval
        return mean() - ((1.96 * stddev()) / Math.sqrt(testCount));
    }

    public double confidenceHi() {              // high endpoint of 95% confidence interval
        return mean() + ((1.96 * stddev()) / Math.sqrt(testCount));
    }

    public static void main(String[] args) {    // test client (described below)
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats foo = new PercolationStats(N, T);
        String confidence = foo.confidenceLo() + ", " + foo.confidenceHi();
        StdOut.println("mean                    = " + foo.mean());
        StdOut.println("stddev                  = " + foo.stddev());
        StdOut.println("95% confidence interval = " + confidence);
    }

    private void doTest(int testTimes) {
        int opened_blocks = 0;
        int i = 0;
        int j = 0;

        for (int cnt = 0; cnt < testTimes; cnt++) {
            pr = new Percolation(matrixSize);
            opened_blocks = 0;
            while (!pr.percolates()) {
                i = StdRandom.uniform(1, matrixSize+1);
                j = StdRandom.uniform(1, matrixSize+1);
                if (pr.isOpen(i, j))
                    continue;

                pr.open(i, j);
                opened_blocks++;
            }

            results[cnt] = (double) opened_blocks / totalBlocks;
        }
    }
}

