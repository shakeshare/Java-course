package UNION;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N; // grid size
    private boolean[][] grid; // grid to represent open/blocked sites
    private WeightedQuickUnionUF uf; // union-find data structure
    private int virtualtop;
    private int virtualbottom;

    public Percolation(int N) // create N-by-N grid, with all sites blocked
    {
        this.N = N;
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0");
        }
        grid = new boolean[N][N];
        int total= N * N;
        uf = new WeightedQuickUnionUF(total + 2);
        virtualtop = total;
        virtualbottom = total + 1;
        
    }

    public void open(int i, int j) // open site (row i, column j) if it is not already
    {
        check(i, j);
        if (!isOpen(i, j)) {
            grid[i-1][j-1] = true;
            int index = index(i, j);
            if (i == 1) {
                uf.union(index, virtualtop);
            }
            if (i == N) {
                uf.union(index, virtualbottom);
            }
            // Connect to adjacent open sites
            if (i > 1 && isOpen(i - 1, j)) {
                uf.union(index, index(i - 1, j));
            }
            if (i < N  && isOpen(i + 1, j)) {
                uf.union(index, index(i + 1, j));
            }
            if (j > 1 && isOpen(i, j - 1)) {
                uf.union(index, index(i, j - 1));
            }
            if (j < N && isOpen(i, j + 1)) {
                uf.union(index, index(i, j + 1));
            }   
        }
    }

    public boolean isOpen(int i, int j) // is site (row i, column j) open?
    {
        check(i, j);
        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) // is site (row i, column j) full?
    {
        check(i, j);
        return uf.connected(index(i, j), virtualtop);
    }

    public boolean percolates() // does the system percolate?
    {
        return uf.connected(virtualtop, virtualbottom);
    }
    
    public void check(int i,int j)
    {
         if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
       
    }

    private int index(int row, int col) {
    return (row - 1) * N + (col - 1);
}
    public static void main(String[] args) {
    } // test client, optional
}