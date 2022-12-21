package pl.put.poznan.sorting.rest.model;

/**
 * Represents common REST API (successful) response wrapper, which contains data
 * returned (depending on request) and status code with message (if error occurred).
 *
 * @param <T> response data type
 */
public class SortingResponse<T> {

    private int code;
    private String msg;
    private T data;

    private SortingResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * Return error code. If there are no errors, this should return 0.
     *
     * @return error code
     */
    public int getCode() {
        return code;
    }

    /**
     * Return error message. If there are no errors, this should return null.
     *
     * @return error message
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Return response data.
     *
     * @return response data
     */
    public T getData() {
        return data;
    }

    /**
     * Construct new OK sorting response
     *
     * @param data response data
     * @return sorting response
     * @param <T> response data type
     */
    public static <T> SortingResponse<T> ok(T data) {
        return new SortingResponse<>(0, null, data);
    }

    /**
     * Construct new FAILED sorting response
     *
     * @param code error code
     * @param msg error message
     * @return sorting response
     * @param <T> response data type
     */
    public static <T> SortingResponse<T> error(int code, String msg) {
        return new SortingResponse<>(code, msg, null);
    }
}
