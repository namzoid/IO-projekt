package pl.put.poznan.sorting.logic.algorithms;

import pl.put.poznan.sorting.logic.SortingAlgorithm;

public class QuickSortingAlgorithm implements SortingAlgorithm {

    @Override
    public void sort(int[] elements) {
        sort0(elements, 0, elements.length - 1);
    }

    private void sort0(int[] elements, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(elements, begin, end);
            sort0(elements, begin, partitionIndex - 1);
            sort0(elements, partitionIndex + 1, end);
        }
    }

    private int partition(int[] elements, int begin, int end) {
        int pivot = elements[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (elements[j] <= pivot) {
                i++;

                int temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }

        int temp = elements[i + 1];
        elements[i + 1] = elements[end];
        elements[end] = temp;

        return i + 1;
    }

    @Override
    public void sort(String[] elements) {
        sort0(elements, 0, elements.length - 1);
    }

    private void sort0(String[] elements, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(elements, begin, end);
            sort0(elements, begin, partitionIndex - 1);
            sort0(elements, partitionIndex + 1, end);
        }
    }

    private int partition(String[] elements, int begin, int end) {
        var pivot = elements[end];
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (elements[j].compareTo(pivot) <= 0) {
                i++;

                var temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
            }
        }

        var temp = elements[i + 1];
        elements[i + 1] = elements[end];
        elements[end] = temp;

        return i + 1;
    }
}
