package pl.put.poznan.sorting.logic;

public interface SortingAlgorithm {

    public String getId();

    public String getDisplayName();

    public void sort(int[] elements);

    public void sort(String[] elements);
}
