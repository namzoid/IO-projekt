package pl.put.poznan.sorting.logic.algorithms;

import java.util.Collections;
import java.util.PriorityQueue;

import pl.put.poznan.sorting.logic.SortingAlgorithm;

public class HeapSortingAlgorithm implements SortingAlgorithm {

    @Override
    public void sort(int[] elements) {
        var queue = new PriorityQueue<Integer>();
        for (int element : elements)
            queue.add(element);

        int i = 0;
        while (!queue.isEmpty())
            elements[i++] = queue.poll();
    }

    @Override
    public void sort(String[] elements) {
        var queue = new PriorityQueue<String>();
        Collections.addAll(queue, elements);

        int i = 0;
        while (!queue.isEmpty())
            elements[i++] = queue.poll();
    }
}
