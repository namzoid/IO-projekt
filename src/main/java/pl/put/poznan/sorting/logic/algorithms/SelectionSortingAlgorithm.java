package pl.put.poznan.sorting.logic.algorithms;

import pl.put.poznan.sorting.logic.SortingAlgorithm;

public class SelectionSortingAlgorithm implements SortingAlgorithm {

    @Override
    public String getId() {
        return "selection";
    }

    @Override
    public String getDisplayName() {
        return "Selection Sort";
    }

    public void sort(int[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (elements[minElementIndex] > elements[j]) {
                    minElementIndex = j;
                }
            }

            if (minElementIndex != i) {
                int temp = elements[i];
                elements[i] = elements[minElementIndex];
                elements[minElementIndex] = temp;
            }
        }
    }

    public void sort(String[] elements) {
        for (int i = 0; i < elements.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < elements.length; j++) {
                if (elements[minElementIndex].compareTo(elements[j]) > 0) {
                    minElementIndex = j;
                }
            }

            if (minElementIndex != i) {
                var temp = elements[i];
                elements[i] = elements[minElementIndex];
                elements[minElementIndex] = temp;
            }
        }
    }
}
