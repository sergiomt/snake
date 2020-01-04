package leo.games.snake;

import javafx.scene.paint.Color;

public class GridCell {

	private Color backGroundColor;

	public GridCell(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public void setColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public Color getColor() {
		return backGroundColor;
	}

	public static final GridCell EMPTY = new GridCell(Color.DARKSLATEBLUE);
	public static final GridCell SNAKE = new GridCell(Color.LIMEGREEN);
	public static final GridCell APPLE = new GridCell(Color.FIREBRICK);
	public static final GridCell WALL = new GridCell(Color.CHOCOLATE);

}
