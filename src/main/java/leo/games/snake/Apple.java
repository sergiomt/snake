package leo.games.snake;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;

public class Apple extends Coords {

	public Apple(final int x, final int y) {
		super(x, y);
	}

	public Image getImage() {
		Image img = null;
		try (InputStream snakeIcon = getClass().getResourceAsStream("Apple.png")) {
			img = new Image(snakeIcon);
		} catch (IOException | NullPointerException e) {
			System.err.println("Could not load image");
		}
		return img;
	}

}
