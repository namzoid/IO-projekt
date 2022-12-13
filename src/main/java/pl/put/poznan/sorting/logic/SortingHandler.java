package pl.put.poznan.sorting.logic;

public class SortingHandler {

    private SortingAlgorithm algorithm;

    public SortingHandler() {}

    public void setAlgorithm(SortingAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int[] sort(int[] elements) {
        return algorithm.sort(elements);
    }

    public String[] sort(String[] elements) {
        return algorithm.sort(elements);
    }
}
