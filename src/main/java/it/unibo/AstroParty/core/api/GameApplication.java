package it.unibo.AstroParty.core.api;

public interface GameApplication {

    /**
     * loads and set the main page
     */
    public void mainPage();

    /**
     * loads and set the tutorial page
     */
    public void tutorial();

    /**
     * loads and set the settings page
     */
    public void settings();

    // TODO 
    public void game();

    /**
     * loads and set the scoreboard page
     */
    public void scoreboard();

    /**
     * loads and set the game-over page
     */
    public void gameOver();
}
