package it.unibo.astroparty;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.astroparty.common.Direction;
import it.unibo.astroparty.common.Position;
import it.unibo.astroparty.game.EntityType;
import it.unibo.astroparty.game.projectile.impl.ProjectileImpl;

public class ProjectileMovementTest{
	private static final Position STARTING_POSITION = new Position(0, 0);
	private static final Direction DIR = new Direction(1,1);
	private static final double SPEED = 1;
	private static final double TIME_INTERVAL = 5;
	private static final ProjectileImpl projectile = new ProjectileImpl(STARTING_POSITION, DIR, EntityType.PROJECTILE, SPEED);

	@Test
	void checkMovement() {
		projectile.update(TIME_INTERVAL);
		final Position expectedPosition = STARTING_POSITION.move(DIR.multiply( SPEED * TIME_INTERVAL ));
		assertEquals(projectile.getPosition(), expectedPosition);
	}
}