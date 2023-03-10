package it.unibo.AstroParty.model.Obstacle.impl;

import java.util.function.BiFunction;

public class ActiveSetter {

    private double totalTimePassed;
    private BiFunction<Double,Double,Boolean> biFun;
    
    //TODO: javadoc
    public ActiveSetter(BiFunction<Double,Double,Boolean> biFun) {
        this.biFun = biFun;
    }

    public boolean apply(double time) {
        return biFun.apply(totalTimePassed, time);
    }


}
