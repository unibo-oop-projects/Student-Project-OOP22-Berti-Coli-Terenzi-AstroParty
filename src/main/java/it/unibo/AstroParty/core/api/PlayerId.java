package it.unibo.AstroParty.core.api;

public class PlayerId {
	String name;
	GameId id;
	
	public String getPalyerName() {
		return name == null ? id.toString() : name;
	}
	
	public GameId getGameId() {
		return this.id;
	}
}
