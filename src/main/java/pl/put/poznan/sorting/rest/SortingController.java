package pl.put.poznan.sorting.rest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.sorting.logic.SortingAlgorithmProvider;
import pl.put.poznan.sorting.logic.SortingHandler;
import pl.put.poznan.sorting.logic.exceptions.InvalidElementsException;
import pl.put.poznan.sorting.logic.exceptions.UnknownAlgorithmException;
import pl.put.poznan.sorting.rest.model.SortingResponse;
import pl.put.poznan.sorting.rest.model.input.SortingInputIntegers;
import pl.put.poznan.sorting.rest.model.input.SortingInputStrings;
import pl.put.poznan.sorting.rest.model.output.SortingOutputIntegers;
import pl.put.poznan.sorting.rest.model.output.SortingOutputStrings;

/**
 * Represents REST API controller.<br>
 * The server runs on local port 8080 by default.
 */
@RestController
public class SortingController {

    /**
     * Sort integers.<br>
     *
     * @param input sort integers input
     * @return output
     */
    @GetMapping(value = "/integers", produces = "application/json", consumes = "application/json")
    public SortingResponse<SortingOutputIntegers> sortIntegers(@RequestBody SortingInputIntegers input) {
        try {
            var algorithm = SortingAlgorithmProvider.getAlgorithm(input.getAlgorithm());
            var elements = input.getElements();

            LOGGER.debug("Sorting integers, algorithm={}, elements={}",
                    algorithm.getClass().getSimpleName(), Arrays.toString(elements));

            var handler = new SortingHandler();
            handler.setAlgorithm(algorithm);
            long time = handler.sort(elements);

            var output = new SortingOutputIntegers(time, elements);
            return SortingResponse.ok(output);
        } catch (UnknownAlgorithmException ex) {
            return SortingResponse.error(CODE_UNKNOWN_ALGORITHM, "");
        } catch (InvalidElementsException ex) {
            return SortingResponse.error(CODE_INVALID_ELEMENTS, ex.getMessage());
        }
    }

    /**
     * Sort strings.<br>
     *
     * @param input sort strings input
     * @return output
     */
    @GetMapping(value = "/strings", produces = "application/json", consumes = "application/json")
    public SortingResponse<SortingOutputStrings> sortStrings(@RequestBody SortingInputStrings input) {
        try {
            var algorithm = SortingAlgorithmProvider.getAlgorithm(input.getAlgorithm());
            var elements = input.getElements();

            LOGGER.debug("Sorting strings, algorithm={}, elements={}",
                    algorithm.getClass().getSimpleName(), Arrays.toString(elements));

            var handler = new SortingHandler();
            handler.setAlgorithm(algorithm);
            long time = handler.sort(elements);

            var output = new SortingOutputStrings(time, elements);
            return SortingResponse.ok(output);
        } catch (UnknownAlgorithmException ex) {
            return SortingResponse.error(CODE_UNKNOWN_ALGORITHM, "");
        } catch (InvalidElementsException ex) {
            return SortingResponse.error(CODE_INVALID_ELEMENTS, ex.getMessage());
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SortingController.class);

    private static final int CODE_UNKNOWN_ALGORITHM = 1001;
    private static final int CODE_INVALID_ELEMENTS = 1002;
}
