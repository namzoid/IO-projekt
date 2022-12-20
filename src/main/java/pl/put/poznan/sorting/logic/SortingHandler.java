package pl.put.poznan.sorting.logic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.logic.exceptions.InvalidElementsException;

/**
 * Represents sorting handler to sort elements using specified algorithm
 */
public class SortingHandler {

    private SortingAlgorithm algorithm;

    /**
     * Create empty instance
     */
    public SortingHandler() {}

    /**
     * Set sorting algorithm
     *
     * @param algorithm sorting algorithm
     */
    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Sort integer elements in ascending order
     *
     * @param elements elements to sort
     * @return millis took to sort elements
     */
    public long sort(int[] elements) {
        LOGGER.debug("Sorting elements=" + Arrays.toString(elements));

        if (elements == null) {
            throw new InvalidElementsException("Array is null");
        }

        if (elements.length == 0) {
            throw new InvalidElementsException("Array is empty");
        }

        long startTime = System.nanoTime();
        algorithm.sort(elements);
        long elapsedNanos = System.nanoTime() - startTime;
        return elapsedNanos / 1_000_000;
    }

    /**
     * Sort string elements in ascending order
     *
     * @param elements elements to sort
     * @return millis took to sort elements
     */
    public long sort(String[] elements) {
        LOGGER.debug("Sorting elements=" + Arrays.toString(elements));

        if (elements == null) {
            throw new InvalidElementsException("Array is null");
        }

        if (elements.length == 0) {
            throw new InvalidElementsException("Array is empty");
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SortingHandler.class);
}
