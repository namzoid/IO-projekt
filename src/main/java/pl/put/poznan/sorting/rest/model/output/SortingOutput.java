package pl.put.poznan.sorting.rest.model.output;

public abstract class SortingOutput {

    private long time;

    public SortingOutput(long time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }
}
