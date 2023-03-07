package it.unibo.AstroParty.core.api;

public class PlayerId {
	String name;
	GameId id;
	
	public PlayerId (String name, GameId gid) {
		this.name = name;
		this.id = gid;
	}
	
	public PlayerId ( GameId gid) {
		this(null,gid);
	}
	
	public String getPalyerName() {
		return name == null ? id.toString() : name;
	}
	
	public GameId getGameId() {
		return this.id;
	}
}
