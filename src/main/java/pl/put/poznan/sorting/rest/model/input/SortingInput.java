package pl.put.poznan.sorting.rest.model.input;

/**
 * Represents REST API input to sort elements.
 */
public abstract class SortingInput {

    private String algorithm;

    protected SortingInput() {}

    /**
     * Return sorting algorithm ID.
     *
     * @return algorithm id
     */
    public String getAlgorithm() {
        return algorithm;
    }
}
