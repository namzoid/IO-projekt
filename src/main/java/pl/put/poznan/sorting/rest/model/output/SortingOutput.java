package pl.put.poznan.sorting.rest.model.output;

/**
 * Represents REST API sorting output.
 */
public abstract class SortingOutput {

    private long time;

    public SortingOutput(long time) {
        this.time = time;
    }

    /**
     * Return time elapsed (millis) to sort elements.
     *
     * @return time elapsed
     */
    public long getTime() {
        return time;
    }
}
