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
        shuffle(values);
        sortRange(values, 0, values.length - 1);
    }

    private int partition(int[] values, int low, int high) {
        int pivot = values[low];
        int left = low - 1;
        int right = high + 1;

        while (true) {
            do {
                left++;
            } while (values[left] < pivot);

            do {
                right--;
            } while (values[right] > pivot);

            if (left >= right) {
                return right;
            }

            swap(values, left, right);
        }
    }

    private void sortRange(int[] values, int low, int high) {
        if (low >= high) {
            return;
        }

        int split = partition(values, low, high);
        sortRange(values, low, split);
        sortRange(values, split + 1, high);
    }

    private void shuffle(int[] values) {
        for (int current = values.length - 1; current > 0; current--) {
            int randomIndex = random.nextInt(current + 1);
            swap(values, current, randomIndex);
        }
    }

    private void swap(int[] values, int first, int second) {
        int temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }
}