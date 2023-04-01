package it.unibo.AstroParty.model.PowerUp.impl;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.model.Spaceship.api.SimpleSpaceship;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;
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
	private final EntityType type;
	
	/**
	 * takes the basic parameters of the PowerUP
	 * @param position
	 * @param offensive 
	 * @param type
	 */
	public BasicPowerUp(Position position, boolean offensive, EntityType type) {
		this.position = position;
		this.offensive = offensive;
		this.type = type;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CircleHitBox getHitBox() {

		return new CircleHitBoxImpl( this.position, PowerUp.relativeSize);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Position getPosition() {

		return this.position;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hit() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void update(double time);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void use();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean pickUp(Spaceship owner) {
		
		if(this.pickedUp) {
			return false;
		}
		
		this.pickedUp=true;
		
		this.owner = (SimpleSpaceship) owner;			//cast forzato in quanto tutte le spaceShip sono simpleSpaceship, ma per le classi oltre ai power up non serve saperlo
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isOffensive() {
		return this.offensive;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityType getType() {
		return this.type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GraphicEntity getGraphicComponent(){
		return this.getHitBox().getGraphicComponent( this.type );
	}
	
}
