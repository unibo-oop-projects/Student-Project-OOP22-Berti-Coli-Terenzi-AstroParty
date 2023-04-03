package it.unibo.AstroParty.model.PowerUp.impl;

import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.model.PowerUp.api.PowerUpFactory;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.PowerUpSpawner;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;

/**
 * concrete implementation of {@link PowerUpSpawner}.
 */
public class PowerUpSpawnerImpl implements PowerUpSpawner {

	private final Collection<EntityType> possiblePowerUpTypes;
	private final long spawnDelay;
	private GameState world;
	private PowerUpFactory pUPfactory= new PowerUpFactoryImpl();

	private Random random = new Random();

	private Timer timer = new Timer();
	/**
	 * 
	 * @param possiblePowerUpTypes a collection of the possible types of PowerUPs.
	 * @param spawnDelay the delay between spawns.
	 */
	public PowerUpSpawnerImpl(final Collection<EntityType> possiblePowerUpTypes, final long spawnDelay){
		this.possiblePowerUpTypes = possiblePowerUpTypes;
		this.spawnDelay = spawnDelay;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(final GameState world) {
		this.world=world;
		this.timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				generate();
			}
		}, 2 * spawnDelay, spawnDelay);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
		timer.cancel();
	}

	/**
	 * creates and adds a new powerUp to the world
	 */
	private void generate() {
		//System.out.println("spawn");
		this.world.addPowerUp(this.pUPfactory.createPowerUp(this.generateType() , this.generatePos()));
	}

	/**
	 * generate the type of the new Power Up between the active ones in the world
	 * @return the {@link PowerUpTypes}
	 */
	private EntityType generateType() {

		int rand = random.nextInt(this.possiblePowerUpTypes.size());
		var it = this.possiblePowerUpTypes.iterator();
		
		for (int i = 0 ; i < rand ; i++) {
			it.next();							//non controllo perche' ho preso un num minore di size quindi deve esserci qualcosa;
		}

		return it.next();
	}

	/**
	 * generates a possible {@link Position} for the new PowerUp
	 * @return the position
	 */
	private Position generatePos() {

		Position pos;

		do {

			pos = new Position(random.nextDouble(GameState.WIDTH) , random.nextDouble(GameState.HEIGHT));

		} while (canExist(pos));

		return pos;
	}

	private boolean canExist(Position position) {
		CircleHitBox hbox = new CircleHitBoxImpl(position, PowerUp.RELATIVE_SIZE);
		return  this.world.getEntities().stream()
				.map(entity -> entity.getHitBox())
				.anyMatch(e -> e.checkCircleCollision(hbox));
	}
}
