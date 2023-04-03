package it.unibo.AstroParty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.Spaceship.impl.SpaceshipImpl;
import it.unibo.AstroParty.model.api.Spaceship;

/**
 * test for the movemnt of a spaceship
 */
public class MovementTest {
    private static final double startAngle = 45;
    private static final Position startPos = new Position(10, 10);
    private static final Direction startDir = new Direction(1, 1);
    private static final double time = 10;
    private static final double speed = 1;
    private final Spaceship spaceship =  new SpaceshipImpl(startPos, startDir, startAngle, null, speed, 0, false, null, 0);

    /**
     * test the update when going straight
     */
    @Test
    void testMovementForward() {      
        this.spaceship.resetPosition();
        Position expectedPosition = (startPos.move( startDir.multiply( speed * time )));
        this.spaceship.update( time ); 
        assertEquals( expectedPosition , this.spaceship.getPosition() );
    }

    /**
     * test update when turning
     */
    @Test
    void testTurn() {      
        this.spaceship.resetPosition();

        double expectedAngle =( startAngle + time * Spaceship.rotationSpeed ) % 360;
        double dirX = Math.cos( Math.toRadians( expectedAngle ) ) ;
		double dirY = Math.sin( Math.toRadians( expectedAngle ) ) ;
        Direction expDirection =  new Direction(dirX, dirY);
        Position expectedPosition = (startPos.move( expDirection.multiply( speed * time )));

        this.spaceship.startTurn();
        this.spaceship.update(time);
        
        assertEquals( expectedAngle, this.spaceship.getAngle() );
        assertEquals(expectedPosition , spaceship.getPosition() );
    }
}