package stateandbehavior;

public class StopWatch {
    private int ticks;
    private int time;
    private boolean started = false;
    private boolean stopped = false;
    private int lastLap;
    private int currentLap;


    public int getTicks() {
        return ticks;
    }

    public int getTime() {
        if (!started) {
            return -1;
        }
        return time;
    }

    public boolean isStarted() {
        return started;
    }


    public boolean isStopped() {
        return stopped;
    }


    public int getLastLapTime() {
        if (lastLap == 0) {
            return -1;
        }
        return lastLap;
    }


    public int getLapTime() {
        if (!started) {
            return -1;
        }
        return currentLap;
    }

    public void tick(int ticks) {
        this.ticks += ticks;
        if (started && !stopped) {
            time += ticks;
            currentLap += ticks;
        }
    }

    public void start() {
        started = true;
    }

    public void stop() {
        lastLap = currentLap;
        currentLap = 0;
        stopped = true;
    }

    public void lap() {
        lastLap = currentLap;
        currentLap = 0;
    }
}
