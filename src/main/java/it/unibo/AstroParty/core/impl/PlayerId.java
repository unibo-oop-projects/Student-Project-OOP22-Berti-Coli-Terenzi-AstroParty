package it.unibo.AstroParty.core.impl;

import it.unibo.AstroParty.input.impl.GameId;

/**
 * 
 * @author Alessandro Coli
 * 
 * a class useful to recognise player in game and between different games of the same match or for a future leaderboard implementation
 */
public class PlayerId {
	String name;
	GameId id;

	/**
	 * @param name : the scoreboard name
	 * @param gid : the id inside the game for input
	 */
	public PlayerId (String name, GameId gid) {
		this.name = name;
		this.id = gid;
	}
	
	/**
	 * {@link GameId#toString()} will be used for the scoreboard name
	 * 
	 * @param gid:  the id inside the game for input
	 */
	public PlayerId ( GameId gid) {
		this(null,gid);
	}
	
	/**
	 * @return the string that represents the player in the view and in the game history and for the scoreboard
	 */
	public String getPalyerName() {
		return name == null ? id.toString() : name;
	}
	
	/**
	 * @return a {@link GameId} that represents which player is inside the game, to be recognized by the input controller
	 */
	public GameId getGameId() {
		return this.id;
	}
	
	@Override
	public boolean equals( Object obj) {
		
		if( obj.getClass().isInstance( this.getClass())) {
			PlayerId ob = (PlayerId) obj;
			if( ob.getGameId().equals( this.getGameId() ) || ob.getPalyerName().equals( this.getPalyerName() )) {
				return true;
			}
		}
		
		return false;
	}
}
