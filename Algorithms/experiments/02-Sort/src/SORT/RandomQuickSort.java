package SORT;

import java.util.Random;

public class RandomQuickSort implements SortAlgorithm {
    private final Random random;

    public RandomQuickSort() {
        this(new Random());
    }

    public RandomQuickSort(Random random) {
        this.random = random;
    }

    @Override
    public String name() {
        return "RQ";
    }

    @Override
    public void sort(int[] values) {
        // TODO: Shuffle or choose a random pivot, then implement quicksort.
    }

    private int partition(int[] values, int low, int high) {
        // TODO: Partition values around a randomly selected pivot.
        return low;
    }
}