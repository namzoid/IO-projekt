package pl.put.poznan.sorting.rest.model.output;

/**
 * Represents REST API sorting output of integers.
 */
public class SortingOutputIntegers extends SortingOutput {

    private int[] elements;

    public SortingOutputIntegers(long time, int[] elements) {
        super(time);
        this.elements = elements;
    }

    /**
     * Return elements sorted.
     *
     * @return elements
     */
    public int[] getElements() {
        return elements;
    }
}
