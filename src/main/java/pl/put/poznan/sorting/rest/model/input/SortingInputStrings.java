package pl.put.poznan.sorting.rest.model.input;

/**
 * Represents REST API input to sort strings.
 */
public class SortingInputStrings extends SortingInput {

    private String[] elements;

    private SortingInputStrings() {}

    /**
     * Return elements to sort.
     *
     * @return elements
     */
    public String[] getElements() {
        return elements;
    }
}
