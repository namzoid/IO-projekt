package pl.put.poznan.sorting.logic;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import pl.put.poznan.sorting.logic.algorithms.*;
import pl.put.poznan.sorting.logic.exceptions.UnknownAlgorithmException;

/**
 * Used to store sorting algorithm implementations.
 */
public class SortingAlgorithmProvider {

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

    /**
     * Return sorting algorithm by its id.
     *
     * @param id algorithm id
     * @return sorting algorithm
     */
    public static SortingAlgorithm getAlgorithm(String id) {
        var algorithm = ALGORITHMS.get(id);
        if (algorithm == null)
            throw new UnknownAlgorithmException();
        return algorithm;
    }

    /**
     * Return all sorting algorithms registered.
     *
     * @return collection of sorting algorithms
     */
    public static Collection<SortingAlgorithm> getRegisteredAlgorithms() {
        return Collections.unmodifiableCollection(ALGORITHMS.values());
    }
}
