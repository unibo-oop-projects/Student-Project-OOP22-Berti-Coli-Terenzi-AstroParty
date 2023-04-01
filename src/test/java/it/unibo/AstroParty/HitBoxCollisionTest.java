package it.unibo.AstroParty;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.HitBox;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;
import it.unibo.AstroParty.model.impl.RectangleHitBoxImpl;

/**
 * a test for the collisions detection.
 */
class HitBoxCollisionTest {

    private static final double MAIN_COORD = 0;
    private static final double SIDE = 2;   // the side size of the shapes used in the test
    private static final double HALF_SIDE = SIDE / 2;
    private static final double AXIS_DIST = 2 * HALF_SIDE;
    private static final double CIRCLE_DIAGONAL_DIST = AXIS_DIST;
    private static final double CIRCLE_DIAGONAL_COMP = CIRCLE_DIAGONAL_DIST / Math.sqrt(2);
    private static final double RECT_DIAGONAL_DIST = Math.sqrt(2) * HALF_SIDE + HALF_SIDE;
    private static final double RECT_DIAGONAL_COMP = RECT_DIAGONAL_DIST / Math.sqrt(2);
    private static final double DELTA = 0.1;

    @Test
    void testCircleCollisionOnX() {
        assertTrue(this.testCircleCollision(this.createCircle(AXIS_DIST - DELTA, MAIN_COORD)));
        assertFalse(this.testCircleCollision(this.createCircle(AXIS_DIST + DELTA, MAIN_COORD)));
        assertTrue(this.testCircleCollision(this.createCircle(-(AXIS_DIST - DELTA), MAIN_COORD)));
        assertFalse(this.testCircleCollision(this.createCircle(-(AXIS_DIST + DELTA), MAIN_COORD)));
    }

    @Test
    void testCircleCollisionOnY() {
        assertTrue(this.testCircleCollision(this.createCircle(MAIN_COORD, AXIS_DIST - DELTA)));
        assertFalse(this.testCircleCollision(this.createCircle(MAIN_COORD, AXIS_DIST + DELTA)));
        assertTrue(this.testCircleCollision(this.createCircle(MAIN_COORD, -(AXIS_DIST - DELTA))));
        assertFalse(this.testCircleCollision(this.createCircle(MAIN_COORD, -(AXIS_DIST + DELTA))));
    }

    @Test
    void testCircleCollisionOnDiagonal() {
        assertTrue(this.testCircleCollision(this.createCircle(CIRCLE_DIAGONAL_COMP, CIRCLE_DIAGONAL_COMP - DELTA)));
        assertFalse(this.testCircleCollision(this.createCircle(-CIRCLE_DIAGONAL_COMP, CIRCLE_DIAGONAL_COMP + DELTA)));
        assertTrue(this.testCircleCollision(this.createCircle(CIRCLE_DIAGONAL_COMP - DELTA, CIRCLE_DIAGONAL_COMP - DELTA)));
        assertFalse(this.testCircleCollision(this.createCircle(CIRCLE_DIAGONAL_COMP - DELTA, -(CIRCLE_DIAGONAL_COMP + DELTA))));
    }

    @Test
    void testRectCollisionOnX() {
        assertTrue(this.testCircleCollision(this.createRectangle(AXIS_DIST - DELTA, MAIN_COORD)));
        assertFalse(this.testCircleCollision(this.createRectangle(AXIS_DIST + DELTA, MAIN_COORD)));
        assertTrue(this.testCircleCollision(this.createRectangle(-(AXIS_DIST - DELTA), MAIN_COORD)));
        assertFalse(this.testCircleCollision(this.createRectangle(-(AXIS_DIST + DELTA), MAIN_COORD)));
    }

    @Test
    void testRectCollisionOnY() {
        assertTrue(this.testCircleCollision(this.createRectangle(MAIN_COORD, AXIS_DIST - DELTA)));
        assertFalse(this.testCircleCollision(this.createRectangle(MAIN_COORD, AXIS_DIST + DELTA)));
        assertTrue(this.testCircleCollision(this.createRectangle(MAIN_COORD, -(AXIS_DIST - DELTA))));
        assertFalse(this.testCircleCollision(this.createRectangle(MAIN_COORD, -(AXIS_DIST + DELTA))));
    }

    @Test
    void testRectCollisionOnDiagonal() {
        assertTrue(this.testCircleCollision(this.createRectangle(RECT_DIAGONAL_COMP, RECT_DIAGONAL_COMP - DELTA)));
        assertFalse(this.testCircleCollision(this.createRectangle(-(RECT_DIAGONAL_COMP + DELTA), RECT_DIAGONAL_COMP + DELTA)));
        assertTrue(this.testCircleCollision(this.createRectangle(RECT_DIAGONAL_COMP - DELTA, RECT_DIAGONAL_COMP - DELTA)));
        assertFalse(this.testCircleCollision(this.createRectangle(RECT_DIAGONAL_COMP, -(RECT_DIAGONAL_COMP + DELTA))));
    }

    // check for collisions with a circle in [0,0]
    private boolean testCircleCollision(final HitBox hBox) {
        final CircleHitBox circle = new CircleHitBoxImpl(new Position(MAIN_COORD, MAIN_COORD), HALF_SIDE);
        return hBox.checkCircleCollision(circle);
    }

    private HitBox createCircle(final double x, final double y) {
        return new CircleHitBoxImpl(new Position(x, y), HALF_SIDE);
    }

    private HitBox createRectangle(final double x, final double y) {
        return new RectangleHitBoxImpl(this.getRectPosition(x, y), SIDE, SIDE);
    }

    // get the ULCorner position (for the RectangleHitBox constructor) from a central position
    private Position getRectPosition(final double x, final double y) {
        return new Position(x - HALF_SIDE, y - HALF_SIDE);
    }
}
