package pl.put.poznan.sorting.logic;

import java.util.LinkedHashMap;
import java.util.Map;

import pl.put.poznan.sorting.logic.algorithms.*;
import pl.put.poznan.sorting.logic.exceptions.UnknownAlgorithmException;

public class SortingAlgorithmFactory {

    private static final Map<String, SortingAlgorithm> ALGORITHMS = new LinkedHashMap<>();

    private static void registerAlgorithm(SortingAlgorithm algorithm) {
        ALGORITHMS.put(algorithm.getId(), algorithm);
    }

    static {
        registerAlgorithm(new BubbleSortingAlgorithm());
        registerAlgorithm(new MergeSortingAlgorithm());
        registerAlgorithm(new QuickSortingAlgorithm());
        registerAlgorithm(new InsertionSortingAlgorithm());
        registerAlgorithm(new SelectionSortingAlgorithm());
        registerAlgorithm(new HeapSortingAlgorithm());
    }

    public static SortingAlgorithm getAlgorithm(String id) {
        var algorithm = ALGORITHMS.get(id);
        if (algorithm == null)
            throw new UnknownAlgorithmException();
        return algorithm;
    }
}
