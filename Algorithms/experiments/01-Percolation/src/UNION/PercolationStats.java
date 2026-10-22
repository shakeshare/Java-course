package UNION;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    public int N;
    public int T;
    public double[] Results;

    public PercolationStats(int N, int T) // perform T independent computational experiments on an N-by-N grid
    {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0");
        }
        this.N = N;
        this.T = T;
        Results = new double[T];
        for (int i = 0; i < T; i++) {
            Results[i] = MonteCarloSimulation();
        }
    }

    public double mean() // sample mean of percolation threshold
    {
            double sum = 0.0;
            for (int i = 0; i < T; i++) {
                sum += Results[i];
            }
            return sum / T;
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
            double mean = mean();
            double sum = 0.0;
            for (int i = 0; i < T; i++) {
                sum += (Results[i] - mean) * (Results[i] - mean);
            }
            return Math.sqrt(sum / (T - 1));
    }

    public double confidenceLo() // returns lower bound of the 95% confidence interval 
    {
        return mean() - (1.96 * stddev() / Math.sqrt(T));
    }

    public double confidenceHi() // returns upper bound of the 95% confidence interval 
    {
        return mean() + (1.96 * stddev() / Math.sqrt(T));
    }

public double MonteCarloSimulation() // perform the Monte Carlo simulation to estimate the percolation threshold
{
   Percolation percolation = new Percolation(N);
   int openSites = 0;
   while (!percolation.percolates()) {
         int i = StdRandom.uniform(1, N + 1);
         int j = StdRandom.uniform(1, N + 1);
         if (!percolation.isOpen(i, j)) {
              percolation.open(i, j);
              openSites++;
         }
    }
    return (double) openSites / (N * N);
}

public static void main(String[] args)      // test client, described below 
{
    int N=Integer.valueOf(args[0]);
    int T=Integer.valueOf(args[1]);

    long startTime = System.nanoTime();
    PercolationStats stats = new PercolationStats(N, T);
    long endTime = System.nanoTime();

    System.out.println("Mean: " + stats.mean());
    System.out.println("Standard Deviation: " + stats.stddev());
    System.out.println("Lower Bound: " + stats.confidenceLo());
    System.out.println("Upper Bound: " + stats.confidenceHi());
    System.out.println("Runtime: " + (endTime - startTime) / 1_000_000.0 + " ms");
}
}