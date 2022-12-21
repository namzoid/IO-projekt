package pl.put.poznan.sorting.logic.exceptions;

/**
 * Thrown to indicate that elements are invalid, for example contain null elements.
 */
public class InvalidElementsException extends RuntimeException {

    /**
     * Construct a new exception with specified message.
     *
     * @param message message
     */
    public InvalidElementsException(String message) {
        super(message);
    }
}
