package it.unibo.AstroParty;

import it.unibo.AstroParty.core.impl.GameApp;
import javafx.application.Application;

/**
 * Main class.
 */
public final class AstroParty {

    /**
     * It's an utility class, theoretically should not have a constructor.
     */
    private AstroParty() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Main methods that starts the game.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        Application.launch(GameApp.class, args);
    }
}
