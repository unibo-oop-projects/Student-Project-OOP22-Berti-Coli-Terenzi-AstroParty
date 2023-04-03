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
 * an abstract class implementing methonds in common between {@link PowerUp} in AstroParty.
 */
public abstract class BasicPowerUp implements PowerUp {

    private SimpleSpaceship owner;
    private final Position position;
    private boolean pickedUp;
    private final boolean offensive;
    private final EntityType type;

    /**
     * takes the basic parameters of the PowerUP.
     * @param position of the powerUP
     * @param offensive true if the powerUp is offensive
     * @param type switch
     */
    public BasicPowerUp(final Position position, final boolean offensive, final EntityType type) {
        this.position = position;
        this.offensive = offensive;
        this.type = type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CircleHitBox getHitBox() {

        return new CircleHitBoxImpl(this.position, PowerUp.RELATIVE_SIZE);
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
    public boolean pickUp(final Spaceship owner) {

        if (this.pickedUp) {
            return false;
        }

        this.pickedUp = true;

        //cast forzato in quanto tutte le spaceShip sono simpleSpaceship, ma alle altre classi non serve saperlo
        this.owner = (SimpleSpaceship) owner;
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
    public GraphicEntity getGraphicComponent() {
        return this.getHitBox().getGraphicComponent(this.type);
    }

    /**
     * 
     * @return true if the powerUp is been pickedUp.
     */
    protected boolean isPickedUp() {
        return this.pickedUp;
    }

    /**
     * @return the spaceship that picked it up.
     */
    protected SimpleSpaceship getOwner() {
        return this.owner;
    }
}
