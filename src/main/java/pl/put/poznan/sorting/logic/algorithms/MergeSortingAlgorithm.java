package pl.put.poznan.sorting.logic.algorithms;

import java.util.Arrays;

import pl.put.poznan.sorting.logic.SortingAlgorithm;

public class MergeSortingAlgorithm implements SortingAlgorithm {

    @Override
    public void sort(int[] elements) {
        int n = elements.length;
        if (n < 2)
            return;

        int mid = n / 2;
        var left = Arrays.copyOfRange(elements, 0, mid);
        var right = Arrays.copyOfRange(elements, mid, elements.length);

        sort(left);
        sort(right);

        merge(elements, left, right);
    }

    private void merge(int[] elements, int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;

        int i = 0, j = 0, k = 0;
        while (i < leftLength && j < rightLength) {
            if (left[i] <= right[j]) {
                elements[k++] = left[i++];
            } else {
                elements[k++] = right[j++];
            }
        }

        while (i < leftLength)
            elements[k++] = left[i++];
        while (j < rightLength)
            elements[k++] = right[j++];
    }

    @Override
    public void sort(String[] elements) {
        int length = elements.length;
        if (length < 2)
            return;

        int mid = length / 2;
        var left = Arrays.copyOfRange(elements, 0, mid);
        var right = Arrays.copyOfRange(elements, mid, elements.length);

        sort(left);
        sort(right);

        merge(elements, left, right);
    }

    private void merge(String[] elements, String[] left, String[] right) {
        int leftLength = left.length;
        int rightLength = right.length;

        int i = 0, j = 0, k = 0;
        while (i < leftLength && j < rightLength) {
            if (left[i].compareTo(right[j]) <= 0) {
                elements[k++] = left[i++];
            } else {
                elements[k++] = right[j++];
            }
        }

        while (i < leftLength)
            elements[k++] = left[i++];
        while (j < rightLength)
            elements[k++] = right[j++];
    }
}
