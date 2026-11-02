package SORT;

import java.util.Random;

public class DijkstraThreeWayQuickSort implements SortAlgorithm {
    private final Random random;

    public DijkstraThreeWayQuickSort() {
        this(new Random());
    }

    public DijkstraThreeWayQuickSort(Random random) {
        this.random = random;
    }

    @Override
    public String name() {
        return "QD3P";
    }

    @Override
    public void sort(int[] values) {
        // TODO: Implement quicksort with Dijkstra's three-way partitioning.
    }

    private void sortRange(int[] values, int low, int high) {
        // TODO: Partition into less-than, equal-to, and greater-than ranges.
    }
}