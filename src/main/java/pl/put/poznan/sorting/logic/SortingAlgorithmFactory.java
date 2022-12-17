package pl.put.poznan.sorting.logic;

import java.util.HashMap;
import java.util.Map;

import pl.put.poznan.sorting.logic.algorithms.*;
import pl.put.poznan.sorting.logic.exceptions.UnknownAlgorithmException;

public class SortingAlgorithmFactory {

    private static final Map<String, SortingAlgorithm> ALGORITHMS = new HashMap<>();

    static {
        ALGORITHMS.put("bubble_sort", new BubbleSortingAlgorithm());
        ALGORITHMS.put("merge_sort", new MergeSortingAlgorithm());
        ALGORITHMS.put("quick_sort", new QuickSortingAlgorithm());
        ALGORITHMS.put("insertion_sort", new InsertionSortingAlgorithm());
        ALGORITHMS.put("selection_sort", new SelectionSortingAlgorithm());
        // TODO Implement "Radix Sort"
    }

    public static SortingAlgorithm getAlgorithm(String name) {
        var algorithm = ALGORITHMS.get(name);
        if (algorithm == null)
            throw new UnknownAlgorithmException();
        return algorithm;
    }
}
