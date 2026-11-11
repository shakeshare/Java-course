package SORT;

import java.util.Random;

public class InputDataFactory {
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

        switch (order) {
            case INCREASING:
                return createIncreasing(size);
            case MOSTLY_SORTED:
                return createMostlySorted(size, random);
            case RANDOM:
                return createRandom(size, random);
            default:
                throw new IllegalArgumentException("Unknown order");
        }
    }

    private static int[] createIncreasing(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = i;
        }
        return values;
    }

    private static int[] createMostlySorted(int size, Random random) {
        int[] values = createIncreasing(size);
        for (int i = 0; i < size / 10; i++) {
            int j = random.nextInt(size);
            int k = random.nextInt(size);
            int temp = values[j];
            values[j] = values[k];
            values[k] = temp;
        }
        return values;
    }

    private static int[] createRandom(int size, Random random) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt();
        }
        return values;
    }
}