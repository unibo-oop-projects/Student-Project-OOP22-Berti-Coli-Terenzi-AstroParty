package it.unibo.AstroParty.input.impl ;

import it.unibo.AstroParty.input.api.GameId;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KeyboardInput extends Application{
	
	private final InputCommandFactory factory ;

	public KeyboardInput(InputCommandFactory factory) {
		this.factory = factory;		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		VBox root = new VBox();
		Scene scene = new Scene( root );
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			// suppressed because it happens to press other keys but nothing has to happen
			@SuppressWarnings("incomplete-switch") 
			@Override
			public void handle(KeyEvent key) {
				
				switch( key.getCode() ) {
				
					case Q:
						factory.startTurn( GameId.Player1 );
						break;
						
					case A:
						factory.shoot( GameId.Player1 );
						break;
					
					case X:
						factory.startTurn( GameId.Player2 );
						break;
						
					case C:
						factory.shoot( GameId.Player2 );
						break;
					
					case P:
						factory.startTurn( GameId.Player3 );
						break;
						
					case L:
						factory.shoot( GameId.Player3 );
						break;
					
					case N:
						factory.startTurn( GameId.Player4 );
						break;
						
					case M:
						factory.shoot( GameId.Player4 );
						break;
				}
			}
			
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			// suppressed because it happens to press other keys but nothing has to happen
			@SuppressWarnings("incomplete-switch") 
			@Override
			public void handle(KeyEvent key) {
				
				switch( key.getCode() ) {
				
				case Q:
					factory.stopTurn( GameId.Player1 );
					break;
					
				case X:
					factory.stopTurn( GameId.Player2 );
					break;
				
				case P:
					factory.stopTurn( GameId.Player3 );
					break;
				
				case N:
					factory.stopTurn( GameId.Player4 );
					break;
				}
			}
		});
	}
	
}