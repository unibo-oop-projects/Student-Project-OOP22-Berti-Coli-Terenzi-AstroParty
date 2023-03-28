package it.unibo.AstroParty.loop;

import it.unibo.AstroParty.core.api.GameEngine;
import it.unibo.AstroParty.core.impl.GameEngineImpl;

/**
 * main class for the main with the following method
 * 
 * @author dario
 *
 */
public class MainLoop {

	/**
	 * main method that starts everything
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		GameEngine engine = new GameEngineImpl();
		
		engine.init();
		engine.mainLoop();
	}

}



