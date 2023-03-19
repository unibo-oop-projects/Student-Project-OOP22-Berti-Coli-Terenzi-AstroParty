package it.unibo.AstroParty.model.Obstacle.impl;

/**
 * a simple class that tells everytime an {@code interval} of time passes
 */
public class Timer {

    private double totElapsed;
    private double interval;

    /**
     * 
     * @param interval in seconds
     */
    public Timer(int interval) {
        this.interval = interval;
    }

    /**
     * 
     * @param elapse the time that has passed
     * @return true if the interval has passed, false otherwise
     */
    public boolean check(double elapse) {
        totElapsed += elapse;
        if (totElapsed > interval) {
            totElapsed -= interval;
            return true;
        }
        return false;
    }

}
