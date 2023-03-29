package it.unibo.AstroParty;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.HitBox;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;


public class HitBoxCollisionTest {

    private static final double CIRCLE_RADIUS = 2;
    private static final double AXIS_DISTANCE = 2 * CIRCLE_RADIUS;
    
    @Test
    void testCircleCollisionOnX() {
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(AXIS_DISTANCE - 0.1, 0), CIRCLE_RADIUS)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(AXIS_DISTANCE + 0.1, 0), CIRCLE_RADIUS)));
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(- AXIS_DISTANCE + 0.1, 0), CIRCLE_RADIUS)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(- AXIS_DISTANCE - 0.1, 0), CIRCLE_RADIUS)));
    }

    @Test
    void testCircleCollisionOnY() {;
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, AXIS_DISTANCE - 0.1), CIRCLE_RADIUS)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, AXIS_DISTANCE + 0.1), CIRCLE_RADIUS)));
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, - AXIS_DISTANCE + 0.1), CIRCLE_RADIUS)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, - AXIS_DISTANCE - 0.1), CIRCLE_RADIUS)));
    }

    //TODO rectangle on axis e circle/rectangle diagonal

    private boolean testCircleCollision(HitBox hBox) {
        final CircleHitBox circle = new CircleHitBoxImpl(new Position(0, 0),CIRCLE_RADIUS);
        return hBox.checkCircleCollision(circle);
    }
}