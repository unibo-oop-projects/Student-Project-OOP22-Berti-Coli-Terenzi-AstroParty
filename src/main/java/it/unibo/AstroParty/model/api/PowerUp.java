package it.unibo.AstroParty.model.api;

public interface PowerUp extends Entity {
	
    public void use();
    public boolean pickUp( Spaceship owner );
}
