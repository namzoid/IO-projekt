package pl.put.poznan.sorting.rest.model.output;

/**
 * Represents REST API sorting output of strings.
 */
public class SortingOutputStrings extends SortingOutput {

    private String[] elements;

    public SortingOutputStrings(long time, String[] elements) {
        super(time);
        this.elements = elements;
    }

    /**
     * Return elements sorted.
     *
     * @return elements
     */
    public String[] getElements() {
        return elements;
    }
}
