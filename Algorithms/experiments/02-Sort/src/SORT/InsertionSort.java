package SORT;

public class InsertionSort implements SortAlgorithm {
    @Override
    public String name() {
        return "IS";
    }

    @Override
    public void sort(int[] values) {
        int n = values.length;
        for (int i = 1; i < n; i++) {
            int key = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > key) {
                values[j + 1] = values[j];
                j--;
            }
            values[j + 1] = key;
        }
    }
}