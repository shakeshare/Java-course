package SORT;

public class TopDownMergeSort implements SortAlgorithm {
    @Override
    public String name() {
        return "TDM";
    }

    @Override
    public void sort(int[] value) {
        int[] auxiliary = new int[value.length];
        truesort(value, auxiliary, 0, value.length - 1);
    }

    public void truesort(int[] value, int[] auxiliary, int low, int high) {

        if (high <= low) {
            return;
        }
        int mid = (low + high) / 2;
        truesort(value, auxiliary, low, mid);
        truesort(value, auxiliary, mid + 1, high);
        merge(value, auxiliary, low, mid, high);

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