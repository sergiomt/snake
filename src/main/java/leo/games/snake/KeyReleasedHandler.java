package leo.games.snake;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyReleasedHandler implements EventHandler<KeyEvent> {

	private final KeyStrokeManager strokeManager;

	public KeyReleasedHandler(final KeyStrokeManager strokeManager) {
		this.strokeManager = strokeManager;
	}

	@Override
	public void handle(KeyEvent event) {
		if (strokeManager.isKeyPressed()) {
			strokeManager.clearKeyCode();
		}		
	}

}
