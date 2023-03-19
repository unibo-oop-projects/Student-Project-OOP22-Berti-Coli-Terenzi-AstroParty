package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.PowerUpTypes;
import it.unibo.AstroParty.model.Spaceship.api.SimpleSpaceship;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Spaceship;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;

/** 
 * 
 * @author Alessandro Coli
 * an abstract class implementing methonds in common between {@link PowerUp} in AstroParty
 */
public abstract class BasicPowerUp implements PowerUp {

	protected SimpleSpaceship owner;
	protected final Position position;
	protected boolean pickedUp;
	private final boolean offensive;
	private final PowerUpTypes type;
	
	public BasicPowerUp(Position position, boolean offensive, PowerUpTypes type) {
		this.position = position;
		this.offensive = offensive;
		this.type = type;
	}
	
	@Override
	public CircleHitBox getHitBox() {

		return new CircleHitBoxImpl( this.position, PowerUp.relativaSize);
	}

	@Override
	public Position getPosition() {

		return this.position;
	}

	@Override
	public boolean hit() {
		return true;
	}

	@Override
	public abstract void update(double time);

	@Override
	public abstract void use();

	@Override
	public boolean pickUp(Spaceship owner) {
		
		if(this.pickedUp) {
			return false;
		}
		
		this.pickedUp=true;
		
		this.owner = (SimpleSpaceship) owner;			//cast forzato in quanto tutte le spaceShip sono simpleSpaceship, ma per le classi oltre ai power up non serve saperlo
		return true;
	}
	@Override
	public boolean isOffensive() {
		return this.offensive;
	}
	@Override
	public PowerUpTypes getType() {
		return this.type;
	}

}
