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
        sortRange(values, 0, values.length - 1);
    }

    private void sortRange(int[] values, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIndex = low + random.nextInt(high - low + 1);
        swap(values, low, pivotIndex);
        int pivot = values[low];

        int less = low;
        int current = low + 1;
        int greater = high;

        while (current <= greater) {
            if (values[current] < pivot) {
                swap(values, less, current);
                less++;
                current++;
            } else if (values[current] > pivot) {
                swap(values, current, greater);
                greater--;
            } else {
                current++;
            }
        }

        sortRange(values, low, less - 1);
        sortRange(values, greater + 1, high);
    }

    private void swap(int[] values, int first, int second) {
        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }
}