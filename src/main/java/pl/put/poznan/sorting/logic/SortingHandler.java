package pl.put.poznan.sorting.logic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.put.poznan.sorting.logic.exceptions.InvalidElementsException;

/**
 * Used to sort elements using specified algorithm.
 */
public class SortingHandler {

    private SortingAlgorithm algorithm;

    public SortingHandler() {}

    /**
     * Set sorting algorithm.
     *
     * @param algorithm sorting algorithm
     */
    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Sort integer elements (ascending order).
     *
     * @param elements elements to sort
     * @return millis elapsed to sort elements
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
        return elapsedNanos / 1_000_000; // Convert nanos to millis
    }

    /**
     * Sort string elements (ascending order).
     *
     * @param elements elements to sort
     * @return millis elapsed to sort elements
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
        return elapsedNanos / 1_000_000; // Convert nanos to millis
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SortingHandler.class);
}
