package leo.games.snake;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyPressedHandler implements EventHandler<KeyEvent> {

	private final KeyStrokeManager strokeManager;

	public KeyPressedHandler(final KeyStrokeManager strokeManager) {
		this.strokeManager = strokeManager;
	}

	@Override
	public void handle(KeyEvent event) {
		if (!strokeManager.isKeyPressed()) {
			final KeyCode code = event.getCode();
			switch (code) {
			case UP:
			case DOWN:
			case LEFT:
			case RIGHT:
				strokeManager.setKeyCode(code);
				break;
			default:
				break;
			}
		}
	}

}
