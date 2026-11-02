package SORT;

import java.util.Random;

public class SortExperiment {
    private static final int[] INPUT_SIZES = {1_000, 100_000};
    private static final int RUNS = 10;

    private static final SortAlgorithm[] ALGORITHMS = {
            new InsertionSort(),
            new TopDownMergeSort(),
            new BottomUpMergeSort(),
            new RandomQuickSort(),
            new DijkstraThreeWayQuickSort()
    };

    public static void main(String[] args) {
        SortExperiment experiment = new SortExperiment();
        experiment.run();
    }

    public void run() {
        for (int inputSize : INPUT_SIZES) {
            for (InputDataFactory.Order order : InputDataFactory.Order.values()) {
                runCase(inputSize, order);
            }
        }
    }

    private void runCase(int inputSize, InputDataFactory.Order order) {
        System.out.printf("%nInput size: %d, order: %s%n", inputSize, order);
        for (SortAlgorithm algorithm : ALGORITHMS) {
            long totalNanos = 0L;
            for (int run = 0; run < RUNS; run++) {
                int[] values = InputDataFactory.create(inputSize, order, new Random(run));
                long start = System.nanoTime();
                algorithm.sort(values);
                totalNanos += System.nanoTime() - start;
            }

            // TODO: Record memory usage and write the ten runs plus average to a report.
            double averageMicros = totalNanos / (RUNS * 1_000.0);
            System.out.printf("%-5s average: %.3f microseconds%n", algorithm.name(), averageMicros);
        }
    }
}