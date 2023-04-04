package it.unibo.astroparty.game.obstacle.impl;

/**
 * a simple class that tells everytime an {@code interval} of time passes.
 */
public class Timer {

    private final double interval;
    private double totElapsed;

    /**
     * 
     * @param interval in seconds
     */
    public Timer(final int interval) {
        this.interval = interval;
    }

    /**
     * 
     * @param elapse the time that has passed
     * @return true if the interval has passed, false otherwise
     */
    public boolean check(final double elapse) {
        totElapsed += elapse;
        if (totElapsed > interval) {
            totElapsed -= interval;
            return true;
        }
        return false;
    }

}
