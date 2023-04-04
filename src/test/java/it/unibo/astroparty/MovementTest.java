package it.unibo.astroparty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.spaceship.api.Spaceship;
import it.unibo.astroparty.game.spaceship.impl.SpaceshipImpl;

/**
 * test for the movemnt of a spaceship.
 */
class MovementTest {
    private static final double START_ANGLE = 45;
    private static final Position STAR_POSITION = new Position(10, 10);
    private static final Direction START_DIRECTION = new Direction(1, 1);
    private static final double TIME = 10;
    private static final double SPEED = 1;
    private final Spaceship spaceship =  new SpaceshipImpl(STAR_POSITION, START_DIRECTION, START_ANGLE, null, 
                                        SPEED, 0, false, null, 0);
    /**
     * test the update when going straight.
     */
    @Test
    void testMovementForward() {
        this.spaceship.resetPosition();
        final Position expectedPosition = STAR_POSITION.move(START_DIRECTION.multiply(SPEED * TIME));
        this.spaceship.update(TIME); 
        assertEquals(expectedPosition, this.spaceship.getPosition());
    }

    /**
     * test update when turning.
     */
    @Test
    void testTurn() {
        this.spaceship.resetPosition();

        final double expectedAngle = (START_ANGLE + TIME * Spaceship.ROTATION_SPEED) % 360;
        final double dirX = Math.cos(Math.toRadians(expectedAngle));
        final double dirY = Math.sin(Math.toRadians(expectedAngle));
        final Direction expDirection =  new Direction(dirX, dirY);
        final Position expectedPosition = STAR_POSITION.move(expDirection.multiply(SPEED * TIME));

        this.spaceship.startTurn();
        this.spaceship.update(TIME);

        assertEquals(expectedAngle, this.spaceship.getAngle());
        assertEquals(expectedPosition, spaceship.getPosition());
    }
}
