package it.unibo.AstroParty.loop;

import it.unibo.AstroParty.core.api.GameEngine;
import it.unibo.AstroParty.core.impl.GameEngineImpl;

public class MainLoop {

	public static void main(String[] args) {
		GameEngine engine = new GameEngineImpl();
		
		engine.init();
		engine.mainLoop();
	}

}



