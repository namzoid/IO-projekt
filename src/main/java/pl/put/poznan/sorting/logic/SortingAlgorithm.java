package pl.put.poznan.sorting.logic;

/**
 * Represents sorting algorithm
 */
public interface SortingAlgorithm {

    /**
     * Return algorithm ID
     *
     * @return algorithm id
     */
    public String getId();

    /**
     * Return algorithm display name
     *
     * @return algorithm display name
     */
    public String getDisplayName();

    /**
     * Sort integer elements
     *
     * @param elements elements to sort
     */
    public void sort(int[] elements);

    /**
     * Sort string elements
     *
     * @param elements elements to sort
     */
    public void sort(String[] elements);
}
