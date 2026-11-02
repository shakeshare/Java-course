package SORT;

/** Common contract for the sorting algorithms in this experiment. */
public interface SortAlgorithm {
    String name();

    void sort(int[] values);
}