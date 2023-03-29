package it.unibo.AstroParty.ui.impl;

import java.util.List;

import it.unibo.AstroParty.core.api.View;
import it.unibo.AstroParty.core.impl.GameApp;
import it.unibo.AstroParty.graphics.impl.GameSceneImpl;
import it.unibo.AstroParty.input.api.InputControl;
import it.unibo.AstroParty.ui.api.Controller;
import it.unibo.AstroParty.ui.api.SceneFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;

public class SceneFactoryImpl implements SceneFactory {

    private final View view;

    /**
     * constructor
     * @param view of the app, used by all the scene controllers to make changes
     */
    public SceneFactoryImpl(View view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createMain() throws Exception {
        return loadFXML("layouts/MainPage.fxml", new MainPageController(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createTutorial() throws Exception {
        return loadFXML("layouts/Tutorial.fxml", new TutorialController(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createSettings() throws Exception {
        return loadFXML("layouts/Settings.fxml", new SettingsController(view));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createGame(InputControl inputControl) throws Exception {
    	return new GameSceneImpl(inputControl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createScoreboard(List<Integer> scores, int rounds) throws Exception {
        return loadFXML("layouts/Scoreboard.fxml", new ScoreboardController(view, scores, rounds));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Scene createOver() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOver'");
    }

    /**
     * loads a new {@link Scene} from a fxml file
     * @param path to the fxml file
     * @param controller of the scene
     */
    private Scene loadFXML(final String path, final Controller controller) throws Exception {
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(path));
        loader.setController(controller);
        Parent root = loader.load();
        double size = GameApp.WINDOW_SIZE/root.prefHeight(0);
        root.getTransforms().add(new Scale(size, size));
        return new Scene(root, GameApp.WINDOW_SIZE, GameApp.WINDOW_SIZE);
    }

}
