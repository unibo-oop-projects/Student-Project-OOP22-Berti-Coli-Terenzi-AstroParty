package it.unibo.AstroParty.input.api;

/**
 *
 * an enum that rapresents the possible players in game, helpfull for connecting the right input.
 */
public enum GameId {
	Player1("Player1"),
	Player2("Player2"),
	Player3("Player3"),
	Player4("Player4");

	private final String stringRep;

	GameId(final String name) {
		this.stringRep = name;
	}

	public String toString() {
		return this.stringRep;
	}
}
