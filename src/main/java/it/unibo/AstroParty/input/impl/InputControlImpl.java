package it.unibo.AstroParty.input.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import it.unibo.AstroParty.common.Pair;
import it.unibo.AstroParty.core.api.GameId;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.input.api.InputReader;
import it.unibo.AstroParty.model.api.Spaceship;

public class InputControlImpl implements InputControl {
	
	private final Collection<Spaceship> spaceships;
	private final InputReader reader = new KeyboardInput(this);
	private final List<Pair<GameId, SpaceshipAction>> actions = new LinkedList<>();
	
	public InputControlImpl(Collection<Spaceship> spaceships){
		this.spaceships = spaceships;
	}
	@Override
	public void stop() {
		reader.stop();
	}
	
	public void start() {
		reader.start();
	}

	@Override
	public void compute() {
		
		for( var event : actions) {
			switch( event.getY() ) {
			case Shoot:
				this.shoot( this.spaceships.stream()
						.filter( s -> s.getId().getGameId().equals(event.getX()))
						.findAny());
				break;
			case StartTurn:
				this.startTurn(this.spaceships.stream()
						.filter( s -> s.getId().getGameId().equals(event.getX()))
						.findAny());
				break;
			case StopTurn:
				this.stopTurn(this.spaceships.stream()
						.filter( s -> s.getId().getGameId().equals(event.getX()))
						.findAny());
			break;
			}
		}
		this.actions.clear();
	}

	@Override
	public void addEvent(Pair<GameId, SpaceshipAction> action) {
		actions.add(action);
	}
	
	private void shoot(Optional<Spaceship> ship) {
		ship.ifPresent( s -> s.shoot());;
	}
	
	private void startTurn(Optional<Spaceship> ship) {
		ship.ifPresent( s -> s.startTurn());
	}

	private void stopTurn(Optional<Spaceship> ship) {
		ship.ifPresent(s -> s.startTurn());
	}

}
