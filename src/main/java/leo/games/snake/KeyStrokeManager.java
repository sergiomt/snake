package leo.games.snake;

import java.util.Optional;

import javafx.scene.input.KeyCode;

public class KeyStrokeManager {

	private Optional<KeyCode> keyCode;

	public KeyStrokeManager() {
		keyCode = Optional.empty();
	}

	public boolean isKeyPressed() {
		return keyCode.isPresent();
	}

	public void setKeyCode(KeyCode code) {
		assert(keyCode.isEmpty());
		keyCode = Optional.of(code);
	}

	public Optional<KeyCode> getKeyCode() {
		return keyCode;
	}

	public void clearKeyCode() {
		assert(keyCode.isPresent());
		keyCode = Optional.empty();
	}

}
