package pl.put.poznan.sorting.logic.algorithms;

import pl.put.poznan.sorting.logic.SortingAlgorithm;

public class BubbleSortingAlgorithm implements SortingAlgorithm {

    @Override
    public int[] sort(int[] elements) {
        int length = elements.length;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < length - 1; i++) {
                if (elements[i] > elements[i + 1]) {
                    int temp = elements[i];
                    elements[i] = elements[i + 1];
                    elements[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return elements;
    }

    @Override
    public String[] sort(String[] elements) {
        int length = elements.length;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < length - 1; i++) {
                if (elements[i].compareTo(elements[i + 1]) > 0) {
                    var temp = elements[i];
                    elements[i] = elements[i + 1];
                    elements[i + 1] = temp;
                    sorted = false;
                }
            }
        }
        return elements;
    }
}
