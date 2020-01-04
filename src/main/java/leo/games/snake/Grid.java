package leo.games.snake;

import java.util.Arrays;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static leo.games.snake.Constants.*;
import static leo.games.snake.GridCell.EMPTY;

public class Grid {

	private final GraphicsContext gc;
	private final GridCell cells[][];

	public Grid(GraphicsContext graphicsContext) {

		// Fill the grid with empty cells
		this.cells = new GridCell[GRID_COLUMNS][GRID_ROWS];
		for (int x=0; x<GRID_COLUMNS; x++) {
			Arrays.fill(cells[x], EMPTY);
		}

		// Paint an empty background
		this.gc = graphicsContext;
    	gc.setFill(EMPTY.getColor());
    	gc.fillRect(0, 0, getWidth(), getHeight());
		
	}

	public int getWidth() {
		return CELL_SIZE * GRID_COLUMNS;
	}

	public int getHeight() {
		return CELL_SIZE * GRID_ROWS;
	}

	private void setCell(final GridCell c, final int x, final int y) throws ArrayIndexOutOfBoundsException {
		cells[x][y] = c;
	}

	public void setCell(final GridCell cell, final Coords xy) throws ArrayIndexOutOfBoundsException {
		setCell(cell, xy.getX(), xy.getY());
	}

	public void paintImage(Image img, Coords position) {
		gc.drawImage(img, position.getX() * CELL_SIZE, position.getY() * CELL_SIZE);
	}

	public void paintPath(final List<Coords> path, final Color colour) {
    	gc.setFill(colour);
		for (Coords part : path) {
        	gc.fillRect(part.getX() * CELL_SIZE, part.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
	}

}
