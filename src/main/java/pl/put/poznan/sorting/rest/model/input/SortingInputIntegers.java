package pl.put.poznan.sorting.rest.model.input;

/**
 * Represents REST API input to sort integers.
 */
public class SortingInputIntegers extends SortingInput {

    private int[] elements;

    private SortingInputIntegers() {}

    /**
     * Return elements to sort.
     *
     * @return elements
     */
    public int[] getElements() {
        return elements;
    }
}
