package SORT;

public class TopDownMergeSort implements SortAlgorithm {
    @Override
    public String name() {
        return "TDM";
    }

    @Override
    public void sort(int[] values) {
        // TODO: Implement top-down merge sort.
    }

    private void merge(int[] values, int[] auxiliary, int low, int middle, int high) {
        // TODO: Merge the two sorted ranges [low, middle] and [middle + 1, high].
    }
}