package pl.put.poznan.sorting.logic;

public class SortingHandler {

    private SortingAlgorithm algorithm;

    public SortingHandler() {}

    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public long sort(int[] elements) {
        long startTime = System.nanoTime();
        algorithm.sort(elements);
        long elapsedNanos = startTime - System.nanoTime();
        return elapsedNanos / 1_000_000;
    }

    public long sort(String[] elements) {
        long startTime = System.nanoTime();
        algorithm.sort(elements);
        long elapsedNanos = startTime - System.nanoTime();
        return elapsedNanos / 1_000_000;
    }
}
