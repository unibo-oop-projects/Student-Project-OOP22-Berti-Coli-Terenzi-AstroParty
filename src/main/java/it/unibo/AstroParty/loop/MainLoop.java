package it.unibo.AstroParty.loop;

import it.unibo.AstroParty.core.impl.GameEngine;

public class MainLoop {

	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		
		engine.init();
		engine.mainLoop();
	}

}



