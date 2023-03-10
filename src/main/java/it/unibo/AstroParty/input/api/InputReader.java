package it.unibo.AstroParty.input.api;

/**
 * @author Alessandro Coli
 * an interface for possible reader of input from different sources
 */
public interface InputReader {
	
	/**
	 * stop propagating input signals
	 */
	public void stop();
	
	/**
	 * start propagating input signals
	 */
	public void start();
}
