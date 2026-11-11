package SORT;



public class BottomUpMergeSort implements SortAlgorithm {
    @Override
    public String name() {
        return "BUM";
    }

    @Override
    public void sort(int[] values) {
        int n = values.length;
        int[] auxiliary = new int[n];
        for (int size = 1; size < n; size = size + size) {
            for (int low = 0; low < n - size; low = size + size + low) {
                merge(values, auxiliary, low, low + size - 1, Math.min(low + size + size - 1, n - 1));
            }
        }

    }

    private void merge(int[] values, int[] auxiliary, int low, int mid, int high) {
        for (int k = low; k <= high; k++)
            auxiliary[k] = values[k];
        int left = low;
        int right = mid + 1;
        for (int k = low; k <= high; k++) {

            if (left > mid) {
                values[k] = auxiliary[right];
                right++;
            } else if (right > high) {
                values[k] = auxiliary[left];
                left++;
            } else if (auxiliary[left] <= auxiliary[right]) {
                values[k] = auxiliary[left];
                left++;
            } else if (auxiliary[left] >= auxiliary[right]) {
                values[k] = auxiliary[right];
                right++;
            }
        }
    }
}
