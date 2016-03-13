/*
 * Created by Hu Ziming at 2016.3.7
 *
 * This file is used for percolation blocks.
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final boolean OPENED = true;
    private static final boolean CLOSED = false;

    private int size = 0;
    private boolean[] blockArray;
    private WeightedQuickUnionUF unionMatrix;

    private int topIndex = 0;
    private int bottomIndex;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException(
                "row count less or equals than 0"
            );
        }
        size = N;
        blockArray = new boolean[size * size + 1];
        unionMatrix = new WeightedQuickUnionUF(size * size + 2);
        bottomIndex = size * size + 1;
    }

    public void open(int i, int j) {
        if (isOpen(i, j))
            return;

        verifyIndex(i);
        verifyIndex(j);
        int array_idx = calcIndex(i, j);

        blockArray[array_idx] = OPENED;

        if (1 == i)
            unionMatrix.union(array_idx, topIndex);
        if (size == i)
            unionMatrix.union(array_idx, bottomIndex);

        if ((j > 1) && isOpen(i, j-1))
            unionMatrix.union(array_idx, calcIndex(i, j-1));
        if ((j < size) && isOpen(i, j+1))
            unionMatrix.union(array_idx, calcIndex(i, j+1));

        if ((i > 1) && isOpen(i-1, j))
            unionMatrix.union(array_idx, calcIndex(i-1, j));
        if ((i < size) && isOpen(i+1, j))
            unionMatrix.union(array_idx, calcIndex(i+1, j));
    }

    public boolean isOpen(int i, int j) {
        return blockArray[calcIndex(i, j)] == OPENED;
    }

    public boolean isFull(int i, int j) {
        return unionMatrix.connected(topIndex, calcIndex(i , j));
    }

    public boolean percolates() {
        return unionMatrix.connected(topIndex, bottomIndex);
    }

    private void verifyIndex(int i) {
        if ((i <= 0) || (i > size)) {
            throw new java.lang.IndexOutOfBoundsException(
                "index out of bounds"
            );
        }
    }

    private int calcIndex(int i, int j) {
        verifyIndex(i);
        verifyIndex(j);
        return (i-1) * size + j;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();

        Percolation foo = new Percolation(N);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            foo.open(i, j);
            StdOut.println(foo.isFull(i, j));
        }
    }
}

