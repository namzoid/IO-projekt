package pl.put.poznan.sorting.logic;

import java.util.LinkedList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pl.put.poznan.sorting.logic.algorithms.*;

public class SortingAlgorithmTest {

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_intsSimpleInput_correctOutput(SortingAlgorithm algorithm) {
        int[] array = new int[] {3, 2, 1, -7, 4, 4, 2};
        int[] expected = new int[] {-7, 1, 2, 2, 3, 4, 4};
        algorithm.sort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_intsSingleItem_correctOutput(SortingAlgorithm algorithm) {
        int[] array = new int[] {0};
        int[] expected = new int[] {0};
        algorithm.sort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_intsEmptyInput_correctOutput(SortingAlgorithm algorithm) {
        int[] array = new int[] {};
        int[] expected = new int[] {};
        algorithm.sort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_stringsSimpleInput_correctOutput(SortingAlgorithm algorithm) {
        String[] array = new String[] {"abc", "aaa", "a", "abb", "baa"};
        String[] expected = new String[] {"a", "aaa", "abb", "abc", "baa"};
        algorithm.sort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_stringsSingleInput_correctOutput(SortingAlgorithm algorithm) {
        String[] array = new String[] {"a"};
        String[] expected = new String[] {"a"};
        algorithm.sort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_stringsEmptyInput_correctOutput(SortingAlgorithm algorithm) {
        String[] array = new String[] {};
        String[] expected = new String[] {};
        algorithm.sort(array);
        Assertions.assertArrayEquals(expected, array);
    }

    public static Stream<SortingAlgorithm> algorithms() {
        var algorithms = new LinkedList<SortingAlgorithm>();
        algorithms.add(new BubbleSortingAlgorithm());
        algorithms.add(new MergeSortingAlgorithm());
        algorithms.add(new QuickSortingAlgorithm());
        algorithms.add(new SelectionSortingAlgorithm());
        algorithms.add(new InsertionSortingAlgorithm());
        algorithms.add(new HeapSortingAlgorithm());
        return algorithms.stream();
    }
}
