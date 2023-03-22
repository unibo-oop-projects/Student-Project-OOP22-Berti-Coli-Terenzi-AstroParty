package it.unibo.AstroParty.core.impl;

import java.io.IOException;

import it.unibo.AstroParty.core.api.GameApplication;
import it.unibo.AstroParty.graphics.api.Controller;
import it.unibo.AstroParty.graphics.impl.MainPageController;
import it.unibo.AstroParty.graphics.impl.ScoreboardController;
import it.unibo.AstroParty.graphics.impl.SettingsController;
import it.unibo.AstroParty.graphics.impl.TutorialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameApp extends Application implements GameApplication{

    private static final int WINDOW_SIZE;
    private static final double WINDOW_PERC = 0.75;
    private static final String ICON_PATH = "sprites/icon.png";
    private static final String MAINPAGE_PATH = "layouts/MainPage.fxml";
    private static final String TUTORIAL_PATH = "layouts/Tutorial.fxml";
    private static final String SETTINGS_PATH = "layouts/Settings.fxml";
    private static final String SCOREBOARD_PATH = "layouts/Scoreboard.fxml";

    private Stage primaryStage;

    // gets the sizes of the current screen and takes the shorter as window size
    static {
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        WINDOW_SIZE = (int) (Math.min(bounds.getHeight(), bounds.getWidth()) * WINDOW_PERC);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AstroParty");
        this.primaryStage.getIcons().add(new Image(ClassLoader.getSystemResource(ICON_PATH).toString()));
        
        this.primaryStage.setWidth(WINDOW_SIZE);
        this.primaryStage.setHeight(WINDOW_SIZE);
        this.primaryStage.setResizable(false);
        this.primaryStage.setOnCloseRequest(s -> System.exit(0));

        this.mainPage();
    }

    private void switchScene(final Scene scene) {
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }

    /**
     * loads a new {@link Scene} from a fxml file
     * @param path to the fxml file
     * @param controller of the scene
     */
    private void loadFXML(final String path, final Controller controller) {
        
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(path));
        loader.setController(controller);

        try {
        Parent root = loader.load();
        double size = WINDOW_SIZE/root.prefHeight(0);
        root.getTransforms().add(new Scale(size, size));
        switchScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mainPage() {
        this.loadFXML(MAINPAGE_PATH, new MainPageController(this));  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tutorial() {
        this.loadFXML(TUTORIAL_PATH, new TutorialController(this));  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void settings() {
        this.loadFXML(SETTINGS_PATH, new SettingsController(this));  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void game() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'game'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scoreboard() {
        this.loadFXML(SCOREBOARD_PATH, new ScoreboardController(this));  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameOver'");
    }
    
}
