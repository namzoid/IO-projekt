package pl.put.poznan.sorting.rest.model;

public class SortingResponse<T> {

    private int code;
    private String msg;
    private T data;

    private SortingResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> SortingResponse<T> ok(T data) {
        return new SortingResponse<>(0, null, data);
    }

    public static <T> SortingResponse<T> error(int code, String msg) {
        return new SortingResponse<>(code, msg, null);
    }
}
