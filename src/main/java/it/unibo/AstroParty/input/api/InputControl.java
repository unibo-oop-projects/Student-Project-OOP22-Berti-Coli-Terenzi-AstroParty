package it.unibo.AstroParty.input.api;

import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.input.impl.GameId;
import it.unibo.AstroParty.input.impl.SpaceshipAction;

/** 
 * @author Alessandro Coli
 *	
 *	a controller used to signal to the model tha input signals
 */
public interface InputControl {
	
	/**
	 * stop propagating input signals
	 */
	public void stop();
	
	/**
	 * start propagating input signals
	 */
	public void start();
	
	/**
	 * compute all the input events in que
	 */
    public void compute();
    
    /**
     * add an input event to the que
     * @param a {@link Pair} of the {@link SpaceshipAction} to be performed and the {@link GameId} of the spaceship
     */
    public void addEvent(Pair<GameId,SpaceshipAction> action);
}
