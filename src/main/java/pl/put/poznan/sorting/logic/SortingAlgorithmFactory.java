package pl.put.poznan.sorting.logic;

import java.util.HashMap;
import java.util.Map;

import pl.put.poznan.sorting.logic.algorithms.BubbleSortingAlgorithm;

public class SortingAlgorithmFactory {

    private static final Map<String, SortingAlgorithm> ALGORITHMS = new HashMap<>();

    static {
        ALGORITHMS.put("bubble_sort", new BubbleSortingAlgorithm());
        // TODO Implement "Merge Sort"
        // TODO Implement "Quick Sort"
        // TODO Implement "Insertion Sort"
        // TODO Implement "Selection Sort"
        // TODO Implement "Radix Sort"
    }

    public static SortingAlgorithm getAlgorithm(String name) {
        var algorithm = ALGORITHMS.get(name);
        if (algorithm == null)
            throw new IllegalArgumentException("Unknown sorting algorithm: " + name);
        return algorithm;
    }
}
