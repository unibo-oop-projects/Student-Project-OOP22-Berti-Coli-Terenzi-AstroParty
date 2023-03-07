package it.unibo.AstroParty.core.api;

public enum GameId {
	Player1("Player1"),
	Player2("Player2"),
	Player3("Player3"),
	Player4("Player4");
	
	private String stringRep;
	GameId(String name){
		this.stringRep = name;
	}
	
	public String toString() {
		return this.stringRep;
	}

}
