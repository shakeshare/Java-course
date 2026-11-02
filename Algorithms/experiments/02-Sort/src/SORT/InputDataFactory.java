package SORT;

import java.util.Random;

public final class InputDataFactory {
    public enum Order {
        INCREASING,
        MOSTLY_SORTED,
        RANDOM
    }

    private InputDataFactory() {
    }

    public static int[] create(int size, Order order, Random random) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative");
        }

        // TODO: Generate the input distributions required by the experiment.
        return new int[size];
    }
}