package it.unibo.AstroParty.input.api;

/**
 *
 * an enum that rapresents the possible players in game, helpfull for connecting the right input.
 */
public enum GameId {
	PLAYER1("Player1"),
	PLAYER2("Player2"),
	PLAYER3("Player3"),
	PLAYER4("Player4");

	private final String stringRep;

	GameId(final String name) {
		this.stringRep = name;
	}

	@Override
	public String toString() {
		return this.stringRep;
	}
}
