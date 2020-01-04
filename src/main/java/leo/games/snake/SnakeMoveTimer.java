package leo.games.snake;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import static javafx.scene.input.KeyCode.*;
import static leo.games.snake.Direction.*;

import static leo.games.snake.Constants.ANIMATION_DELAY_MILLIS;

import java.util.Map;
import java.util.Optional;

public class SnakeMoveTimer extends AnimationTimer {

	private long lastUpdate;
	private final long MILLISECONDS = 1000000l;
	private final long delay;
	private final GameController game;
	private final KeyStrokeManager manager;
	private final Map<KeyCode, Direction> keyToDirection;

	public SnakeMoveTimer(final GameController theGame, final KeyStrokeManager strokeManager) {
		delay = ANIMATION_DELAY_MILLIS * MILLISECONDS;
		lastUpdate = 0l;
		game = theGame;
		manager = strokeManager;
		keyToDirection = Map.of(UP, NORTH, DOWN, SOUTH, LEFT, WEST, RIGHT, EAST);
	}

	@Override
	public void handle(long nowNanos) {
		if (nowNanos - lastUpdate >= delay) {
			lastUpdate = nowNanos;
			final Optional<KeyCode> code = manager.getKeyCode();
			final Direction direction = code.isPresent() ? keyToDirection.get(code.get()) : null;
			game.moveSnake(direction);
		}
	}

}
