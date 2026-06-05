package io.github.highyul.ticklesstimer.timer;


public class RealTimer {

    private long startTime;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }


    public boolean isRunning() {
        return this.startTime != 0;
    }

    public long elapsed() {
        if (!isRunning()) {
            return 0;
        }

        return System.currentTimeMillis() - this.startTime;
    }


    public void reset() {
        this.startTime = 0;

    }
}
