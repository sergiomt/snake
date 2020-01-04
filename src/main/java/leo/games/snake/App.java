package leo.games.snake;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static leo.games.snake.Constants.*;

/**
 * Snake Game Application
 *
 */
public class App extends Application implements PropertyChangeListener {

	private SnakeMoveTimer timer;
	private StackPane pane;		
	private Score scoring;

	/**
	 * Application entry point, everything starts here
	 * @param args String[] Command line arguments (not used)
	 */
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {

		// Class shared between the Scene and the AnimationTimer for handling key strokes
		final KeyStrokeManager strokeManager = new KeyStrokeManager();

		// Set up the window where the game will take place
		final Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		
		pane = new StackPane();
		pane.getChildren().add(canvas);

		final Scene scene = new Scene(pane, SCENE_WIDTH, SCENE_HEIGHT, Color.DARKSLATEBLUE);		
		scene.setOnKeyPressed(new KeyPressedHandler(strokeManager));
		scene.setOnKeyReleased(new KeyReleasedHandler(strokeManager));

		stage.setTitle("Snake");
		stage.setScene(scene);

		try (InputStream snakeIcon = getClass().getResourceAsStream("Cute-Green-Snake-64x64.png")) {
			stage.getIcons().add(new Image(snakeIcon));
		} catch (IOException | NullPointerException e) {
			System.err.println("Could not load icon");
		}

		stage.show();

		// Create the game

		// Use an observer pattern for the score
		scoring = new Score();
		scoring.addPropertyChangeListener(this);
		
		final Grid board = new Grid(canvas.getGraphicsContext2D());
		
		final GameController game = new GameController(board, scoring);

		// Start the game
		timer = new SnakeMoveTimer(game, strokeManager);
		timer.start();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch(evt.getPropertyName()) {
		case Score.GAME_OVER:
			timer.stop();
			final Label gameOver = new Label("GAME OVER! SCORE: " + scoring.getPoints());
			gameOver.setTextFill(Color.FLORALWHITE);
			pane.getChildren().add(gameOver);			
			break;
		case Score.POINTS:
			break;
		}
	}

}