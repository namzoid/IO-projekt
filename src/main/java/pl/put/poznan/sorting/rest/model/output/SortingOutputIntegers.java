package pl.put.poznan.sorting.rest.model.output;

public class SortingOutputIntegers extends SortingOutput {

    private int[] elements;

    public SortingOutputIntegers(long time, int[] elements) {
        super(time);
        this.elements = elements;
    }

    public int[] getElements() {
        return elements;
    }
}
