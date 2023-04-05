package it.unibo.astroparty.game.powerUp.impl;

import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.powerUp.api.PowerUp;
import it.unibo.astroparty.game.powerUp.api.PowerUpFactory;

/**
 * 
 * a class that implements {@link PowerUpFactory}.
 */
public class PowerUpFactoryImpl implements PowerUpFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public PowerUp createPowerUp(final EntityType type, final Position pos) {
        PowerUp pUp = null;
        switch (type) {

                case SHIELD:
                    pUp = this.createShield(pos);
                    break;

                case IMMORTALITY:
                    pUp = this.createImmortality(pos);
                    break;

                case DOUBLESHOT:
                    pUp = this.createDoubleShot(pos);
                    break;

                case UPGRADEDSPEED:
                    pUp = this.createSpeed(pos);
                    break;

                default:
                    throw new UnsupportedOperationException();
        }
        return pUp;
    }

    /**
     * 
     * @param pos of the PowerUp.
     * @return a UPGRADEDSPEED powerUP.
     */
    private PowerUp createSpeed(final Position pos) {

        return new BasicPowerUp(pos, true,  EntityType.UPGRADEDSPEED) {

            private boolean inUse;
            private double useTime;

            @Override
            public void use() {
                this.inUse = true;
                super.getOwner().upgradeSpeed();
            }

            @Override
            public void update(final double time) {

                if (super.isPickedUp() && !this.inUse) {
                    this.use();
                }

                if (this.inUse) {
                    this.useTime += time;
                    if (this.useTime > PowerUp.DURATION) {
                        super.getOwner().normalSpeed();
                        super.getOwner().removePowerUp(this);
                    }
                }
            }
        };
    }

    /**
     * 
     * @param pos of the PowerUp.
     * @return a DOUBLESHOT powerUP.
     */
    private PowerUp createDoubleShot(final Position pos) {

        return new BasicPowerUp(pos, true,  EntityType.DOUBLESHOT) {

            @Override
            public void update(final double time) {
            }

            @Override
            public void use() {
                super.getOwner().removePowerUp(this);
            }
        };
    }

    /**
     * 
     * @param pos of the PowerUp.
     * @return a IMMORTALITY powerUP.
     */
    private PowerUp createImmortality(final Position pos) {

        return new BasicPowerUp(pos, true,  EntityType.IMMORTALITY) {

            private boolean inUse;
            private double useTime;

            @Override
            public void use() {
                this.inUse = true;
                super.getOwner().makeImmortal();
            }

            @Override
            public void update(final double time) {

                if (super.isPickedUp() && !this.inUse) {
                    this.use();
                }

                if (this.inUse) {
                    this.useTime += time;
                    if (this.useTime > PowerUp.DURATION) {
                        super.getOwner().makeMortal();
                        super.getOwner().removePowerUp(this);
                    }
                }
            }
        };
    }

    /**
     * 
     * @param pos of the PowerUp.
     * @return a SHIELD powerUP.
     */
    private PowerUp createShield(final Position pos) {

        return new BasicPowerUp(pos, false, EntityType.SHIELD) {

            @Override
            public void update(final double time) {
                if (super.isPickedUp()) {
                    this.use();
                }
            }

            @Override
            public void use() {
                super.getOwner().newShield();
                super.getOwner().removePowerUp(this);
            }
        };
    }
}
