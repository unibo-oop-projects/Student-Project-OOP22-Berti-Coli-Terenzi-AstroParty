package it.unibo.AstroParty.core.impl;

import it.unibo.AstroParty.input.api.GameId;

/**
 * 
 * a class used to recognise player in game and between different 
 * games of the same match or for a future leaderboard implementation.
 */
public class PlayerId {
	private String name;
	private GameId id;

	/**
	 * @param name : the scoreboard name.
	 * @param gid : the id inside the game for input.
	 */
	public PlayerId(final String name, final GameId gid) {
		this.name = name;
		this.id = gid;
	}
	
	/**
	 * {@link GameId#toString()} will be used for the scoreboard name.
	 * 
	 * @param gid  the id inside the game for input.
	 */
	public PlayerId(final GameId gid) {
		this(null, gid);
	}
	
	/**
	 * @return the string that represents the player in the view and in the game history and for the scoreboard.
	 */
	public String getPlayerName() {
		return name == null ? id.toString() : name;
	}
	
	/**
	 * @return a {@link GameId} that represents which player is inside the game, to be recognized by the input controller.
	 */
	public GameId getGameId() {
		return this.id;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof PlayerId) {
            final PlayerId other = (PlayerId)obj;
            return id.equals(other.id) || getPlayerName().equals(other.getPlayerName());
        }
        return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode(){
		final int prime = 13;
		int result = 1;
		result = prime * result + name.hashCode();
		return result;
	}
}
