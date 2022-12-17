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
        int[] input = new int[] {3, 2, 1, -7, 4, 4, 2};
        int[] expected = new int[] {-7, 1, 2, 2, 3, 4, 4};
        int[] output = algorithm.sort(input);
        Assertions.assertArrayEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_intsSingleItem_correctOutput(SortingAlgorithm algorithm) {
        int[] input = new int[] {0};
        int[] expected = new int[] {0};
        int[] output = algorithm.sort(input);
        Assertions.assertArrayEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_intsEmptyInput_correctOutput(SortingAlgorithm algorithm) {
        int[] input = new int[] {};
        int[] expected = new int[] {};
        int[] output = algorithm.sort(input);
        Assertions.assertArrayEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_stringsSimpleInput_correctOutput(SortingAlgorithm algorithm) {
        String[] input = new String[] {"abc", "aaa", "a", "abb", "baa"};
        String[] expected = new String[] {"a", "aaa", "abb", "abc", "baa"};
        String[] output = algorithm.sort(input);
        Assertions.assertArrayEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_stringsSingleInput_correctOutput(SortingAlgorithm algorithm) {
        String[] input = new String[] {"a"};
        String[] expected = new String[] {"a"};
        String[] output = algorithm.sort(input);
        Assertions.assertArrayEquals(expected, output);
    }

    @ParameterizedTest
    @MethodSource("algorithms")
    public void test_stringsEmptyInput_correctOutput(SortingAlgorithm algorithm) {
        String[] input = new String[] {};
        String[] expected = new String[] {};
        String[] output = algorithm.sort(input);
        Assertions.assertArrayEquals(expected, output);
    }

    public static Stream<SortingAlgorithm> algorithms() {
        var algorithms = new LinkedList<SortingAlgorithm>();
        algorithms.add(new BubbleSortingAlgorithm());
        algorithms.add(new MergeSortingAlgorithm());
        algorithms.add(new QuickSortingAlgorithm());
        algorithms.add(new SelectionSortingAlgorithm());
        algorithms.add(new InsertionSortingAlgorithm());
        return algorithms.stream();
    }
}
