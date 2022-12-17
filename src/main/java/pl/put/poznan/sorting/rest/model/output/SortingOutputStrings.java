package pl.put.poznan.sorting.rest.model.output;

public class SortingOutputStrings extends SortingOutput {

    private String[] elements;

    public SortingOutputStrings(long time, String[] elements) {
        super(time);
        this.elements = elements;
    }

    public String[] getElements() {
        return elements;
    }
}
