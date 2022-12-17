package pl.put.poznan.sorting.rest.model.input;

public abstract class SortingInput {

    private String algorithm;

    protected SortingInput() {}

    public String getAlgorithm() {
        return algorithm;
    }
}
