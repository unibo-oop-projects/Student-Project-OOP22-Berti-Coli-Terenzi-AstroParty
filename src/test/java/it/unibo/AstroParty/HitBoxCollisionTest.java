package it.unibo.AstroParty;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.HitBox;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;
import it.unibo.AstroParty.model.impl.RectangleHitBoxImpl;


public class HitBoxCollisionTest {

    private static final double SIDE = 2;   // the side size of the shapes used in the test
    private static final double HALF_SIDE = SIDE/2;     
    private static final double AXIS_DIST = 2*HALF_SIDE;
    private static final double CIRCLE_DIAGONAL_DIST = AXIS_DIST;
    private static final double CIRCLE_DIAGONAL_COMP = CIRCLE_DIAGONAL_DIST/Math.sqrt(2);
    private static final double RECT_DIAGONAL_DIST = (Math.sqrt(2) * HALF_SIDE) + HALF_SIDE;
    private static final double RECT_DIAGONAL_COMP = RECT_DIAGONAL_DIST/Math.sqrt(2);
    
    @Test
    void testCircleCollisionOnX() {
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(AXIS_DIST - 0.1, 0), HALF_SIDE)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(AXIS_DIST + 0.1, 0), HALF_SIDE)));
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(-(AXIS_DIST - 0.1), 0), HALF_SIDE)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(-(AXIS_DIST + 0.1), 0), HALF_SIDE)));
    }

    @Test
    void testCircleCollisionOnY() {;
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, AXIS_DIST - 0.1), HALF_SIDE)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, AXIS_DIST + 0.1), HALF_SIDE)));
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, -(AXIS_DIST - 0.1)), HALF_SIDE)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(0, -(AXIS_DIST + 0.1)), HALF_SIDE)));
    }

    @Test
    void testCircleCollisionOnDiagonal() {
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(CIRCLE_DIAGONAL_COMP, CIRCLE_DIAGONAL_COMP - 0.1), HALF_SIDE)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(-CIRCLE_DIAGONAL_COMP, CIRCLE_DIAGONAL_COMP + 0.1), HALF_SIDE)));
        assertTrue(this.testCircleCollision(new CircleHitBoxImpl(new Position(CIRCLE_DIAGONAL_COMP - 0.1, CIRCLE_DIAGONAL_COMP - 0.1), HALF_SIDE)));
        assertFalse(this.testCircleCollision(new CircleHitBoxImpl(new Position(CIRCLE_DIAGONAL_COMP - 0.1, -(CIRCLE_DIAGONAL_COMP + 0.1)), HALF_SIDE)));
    }

    @Test
    void testRectCollisionOnX() {
        assertTrue(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(AXIS_DIST - 0.1, 0), SIDE, SIDE)));
        assertFalse(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(AXIS_DIST + 0.1, 0), SIDE, SIDE)));
        assertTrue(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(-(AXIS_DIST - 0.1), 0), SIDE, SIDE)));
        assertFalse(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(-(AXIS_DIST + 0.1), 0), SIDE, SIDE)));
    }

    @Test
    void testRectCollisionOnY() {;
        assertTrue(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(0, AXIS_DIST - 0.1), SIDE, SIDE)));
        assertFalse(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(0, AXIS_DIST + 0.1), SIDE, SIDE)));
        assertTrue(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(0, -(AXIS_DIST - 0.1)), SIDE, SIDE)));
        assertFalse(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(0, -(AXIS_DIST + 0.1)), SIDE, SIDE)));
    }

    @Test
    void testRectCollisionOnDiagonal() {
        assertTrue(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(RECT_DIAGONAL_COMP, RECT_DIAGONAL_COMP - 0.1), SIDE, SIDE)));
        assertFalse(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(-(RECT_DIAGONAL_COMP + 0.1), RECT_DIAGONAL_COMP + 0.1), SIDE, SIDE)));
        assertTrue(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(RECT_DIAGONAL_COMP - 0.1, RECT_DIAGONAL_COMP - 0.1), SIDE, SIDE)));
        assertFalse(this.testCircleCollision(new RectangleHitBoxImpl(this.createRectPosition(RECT_DIAGONAL_COMP, -(RECT_DIAGONAL_COMP + 0.1)), SIDE, SIDE)));
    }

    // check for collisions with a circle in [0,0]
    private boolean testCircleCollision(HitBox hBox) {
        final CircleHitBox circle = new CircleHitBoxImpl(new Position(0, 0),HALF_SIDE);
        return hBox.checkCircleCollision(circle);
    }

    // create a ULCorner position (for the RectangleHitBox constructor) from a central position
    private Position createRectPosition(double x, double y) {
        return new Position(x - HALF_SIDE, y - HALF_SIDE);
    }
}