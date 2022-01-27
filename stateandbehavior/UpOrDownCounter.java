package stateandbehavior;

public class UpOrDownCounter {

    private int counter;
    private int end;
    private int step;
    private boolean running = true;

    public UpOrDownCounter(int start, int end) {
        this.end = end;
        this.counter = start;

        if (start < end) {
            this.step = 1;
        } else if (start > end) {
            this.step = -1;
        } else {
            throw new IllegalArgumentException("Start and end values must be different");
        }
    }

    public int getCounter() {
        return counter;
    }

    public boolean count() {
        if (running) {
            counter += step;
            if (counter == end) {
                running = false;
            }
        }
        return running;
    }

}
