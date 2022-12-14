package pl.put.poznan.sorting.logic.algorithms;

import pl.put.poznan.sorting.logic.SortingAlgorithm;

public class InsertionSortingAlgorithm implements SortingAlgorithm {

    @Override
    public String getId() {
        return "insertion";
    }

    @Override
    public String getDisplayName() {
        return "Insertion Sort";
    }

    public void sort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int key = elements[i];
            int j = i - 1;
            while (j >= 0 && elements[j] > key) {
                elements[j + 1] = elements[j];
                j = j - 1;
            }
            elements[j + 1] = key;
        }
    }

    public void sort(String[] elements) {
        for (int i = 1; i < elements.length; i++) {
            var key = elements[i];
            int j = i - 1;
            while (j >= 0 && elements[j].compareTo(key) > 0) {
                elements[j + 1] = elements[j];
                j = j - 1;
            }
            elements[j + 1] = key;
        }
    }
}
