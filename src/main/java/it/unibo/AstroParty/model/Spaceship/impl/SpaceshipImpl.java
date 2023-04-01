package it.unibo.AstroParty.model.Spaceship.impl;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.AstroParty.common.Direction;
import it.unibo.AstroParty.common.Position;
import it.unibo.AstroParty.core.impl.PlayerId;
import it.unibo.AstroParty.graphics.api.GraphicEntity;
import it.unibo.AstroParty.model.Projectile.impl.ProjectileFactoryImpl;
import it.unibo.AstroParty.model.Spaceship.api.SimpleSpaceship;
import it.unibo.AstroParty.model.api.CircleHitBox;
import it.unibo.AstroParty.model.api.EntityType;
import it.unibo.AstroParty.model.api.GameState;
import it.unibo.AstroParty.model.api.PowerUp;
import it.unibo.AstroParty.model.api.Projectile;
import it.unibo.AstroParty.model.api.Spaceship;
import it.unibo.AstroParty.model.impl.CircleHitBoxImpl;

/**
 * 
 * @author Alessandro Coli
 * a {@link Spaceship} inside an AstroParty game
 */
public class SpaceshipImpl implements SimpleSpaceship {

	private double speed;								//impostazioni prese dal builder
	private final PlayerId playerId;
	private Position position;							// gestione movimento
	private Position lastPos;
	private Direction direction;
	private double angle ;
	boolean turning;
	
	private Optional<PowerUp> powerUp = Optional.empty();
	
	private int bullets;								//proiettili
	private final int maxBullets;
	private final long bulletRegenTime;
	private Timer timer = new Timer();
	private final GameState world;
	
	private boolean shield;								// defensive;
	private boolean immortal;
	private boolean recharging;
	private boolean dead;
	
	/**
	 * takes all the parameters needed for the game
	 * @param startPosition
	 * @param startDirection
	 * @param angle
	 * @param world
	 * @param speed
	 * @param maxBullets
	 * @param startingShield
	 * @param id
	 * @param bulletRegenTime
	 */
	public SpaceshipImpl(Position startPosition, Direction startDirection, double angle, GameState world, double speed, int maxBullets, boolean startingShield, PlayerId id, long bulletRegenTime){
		
		this.world = world;
		this.shield = startingShield;
		this.maxBullets = maxBullets;
		this.playerId = id;
		this.speed = speed;
		this.bulletRegenTime = bulletRegenTime;
		this.bullets = maxBullets;
		this.position = startPosition;
		this.angle = angle;
		this.direction = startDirection;
		this.lastPos = startPosition;
		
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void resetPosition() {
		this.position = this.lastPos ;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Position getPosition() {
		return this.position.copy();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equipPowerUp(PowerUp pUp) {
		
		if ( powerUp.isEmpty()) {
			powerUp = Optional.ofNullable(pUp);
			pUp.pickUp(this);
			return true;
		}
		
		return false;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public CircleHitBox getHitBox() {
		
		return new CircleHitBoxImpl(this.position , Spaceship.relativeSize);
	}
	
	public PlayerId getId() {
		return this.playerId;
		
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public GraphicEntity getGraphicComponent() {
		GraphicEntity view = this.getHitBox().getGraphicComponent( EntityType.SPACESHIP );
		view.setAngle(angle);
		view.setId( this.playerId.getGameId() );
		return view;
	}
	

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public double getAngle() {
		
		return this.angle;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public EntityType getType() {
		
		return EntityType.SPACESHIP;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void update(double time) {
		
		if(this.turning ) {
			this.updateDirection(time);
		}
		this.powerUp.ifPresent( p -> p.update(time) );
		this.move(time);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void shoot() {
		
		if(  this.dead || this.bullets <= 0) {
			return;
		}
		
		if ( this.powerUp.isPresent() && this.powerUp.get().isOffensive() ) {
			
			switch ( this.powerUp.get().getType() ) {
				
				case DOUBLESHOT:
					this.createProjectile();
					this.powerUp.get().use();

					timer.schedule(new TimerTask() {

						@Override
						public void run() {
							createProjectile();
						}
						
					}, 55);

					break;
					
				default :
					this.createProjectile();
			}
			
		}else {
			this.createProjectile();
		}
		this.bullets -- ;
		this.startTimer(); 					// non e' contenuto in createProjectile in quanto in caso di DuobleShot deve toglierne uno solo al counter
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void removePowerUp(PowerUp pUp) {
		if( this.powerUp.isPresent() &&  this.powerUp.get().equals(pUp)) {
			this.powerUp = Optional.empty();
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void makeImmortal() {

		this.immortal = true;
	}
	

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void makeMortal() {
		
		this.immortal = false;
	}
	

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void newShield() {

		this.shield = true;
	}
	

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void upgradeSpeed() {
		
		this.speed = this.speed * PowerUp.speedModifier;
	}
	

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void normalSpeed() {

		this.speed = this.speed / PowerUp.speedModifier;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean hit() {			// basta dire che soso stato ucciso a gamestate, che lascia il mio riferimento e avvisa InputControl 

		if( this.immortal ) {
			this.dead =  false;
		}
		
		if( this.shield ) {
			this.shield = false;
			this.dead =  false;
		}
		this.dead =  true;

		return this.dead;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void startTurn() {

		this.turning = true;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void stopTurn() {

		this.turning = false;
	}

	/** 
	 * updtaes the direction of the spaceship based on how much time it has been turning
	 * @param turnTime in milliseconds
	 */
	private void updateDirection(double turnTime) {
		
		// uso le formule per trovare le coordinate di un punto dala la distanza dall'origine del piano e l'angolo rispetto all'asse x a velocita 1x
		
		this.angle = ( this.angle + turnTime * Spaceship.rotationSpeed ) % 360;

		double dirX = Math.cos( Math.toRadians(this.angle) ) ;
		double dirY = Math.sin( Math.toRadians(this.angle) ) ;
		
		this.direction = new Direction( dirX , dirY) ;		
	}

	/**
	 * updates the position based on the current direction and time between updates
	 * @param timeDiff in milliseconds
	 */
	private void move(double timeDiff) {
		
		this.lastPos = this.position;
		this.position = this.position.move( this.direction.multiply( this.speed * timeDiff ) );
		//System.out.println(this.lastPos + " -> " + this.position);
	}

	/**
	 * creates and adds to the world a new {@link Projectile}
	 */
	private void createProjectile() {

		this.world.addProjectile(new ProjectileFactoryImpl()
				.createProjectile(position.move(direction.multiply(Projectile.radius)), direction));
	}
	
	/**
	 * start the timer to recharge a bullet
	 */
	private void startTimer() {
		
		if( !this.recharging ) {
			this.recharging = true;
			timer.schedule( new TimerTask() {

				@Override
				public void run() {
					addBullet();
				}
				
			}, this.bulletRegenTime);
		}
	}
	
	/**
	 * adds a new bullet to the spaceship
	 */
	private void addBullet() {
		
		this.recharging = false;
		
		this.bullets ++;
		
		if(this.bullets < this.maxBullets) {
			this.startTimer();
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals( Object o){

		if( o instanceof Spaceship ){

			if( ( (Spaceship)o ).getId() == this.getId() ){

				return true;

			}
		}
		return false;
	}
}