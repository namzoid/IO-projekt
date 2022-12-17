package pl.put.poznan.sorting.logic;

import pl.put.poznan.sorting.logic.exceptions.InvalidElementsException;

public class SortingHandler {

    private SortingAlgorithm algorithm;

    public SortingHandler() {}

    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public long sort(int[] elements) {
        if (elements == null) {
            throw new InvalidElementsException("Array is null");
        }

        long startTime = System.nanoTime();
        algorithm.sort(elements);
        long elapsedNanos = System.nanoTime() - startTime;
        return elapsedNanos / 1_000_000;
    }

    public long sort(String[] elements) {
        if (elements == null) {
            throw new InvalidElementsException("Array is null");
        }

        for (var element : elements) {
            if (element == null)
                throw new InvalidElementsException("Array contains null element");
        }

        long startTime = System.nanoTime();
        algorithm.sort(elements);
        long elapsedNanos = System.nanoTime() - startTime;
        return elapsedNanos / 1_000_000;
    }
}
