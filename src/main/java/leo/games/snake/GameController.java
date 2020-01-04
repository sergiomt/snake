package leo.games.snake;

import static leo.games.snake.Constants.GRID_COLUMNS;
import static leo.games.snake.Constants.GRID_ROWS;
import static leo.games.snake.Constants.POINTS_EARNED_PER_APPLE;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {

	private final Grid board;	// The game board
	private final Score scoring;// The current score
	private final Snake snake;	// The snake inside the Grid
	private final Random rnd;	// Random number generator for placing the apple
	private Apple apple;		// Position of the apple inside the grid
	private List<Wall> walls;

	public GameController(final Grid board, final Score scoring) {

		this.board = board;
		this.scoring = scoring;

		this.rnd = new Random();

		this.walls = new ArrayList<>();
		addWalls();

		// Create a short snake and place it in the middle of the grid
		this.snake = new Snake();

		paintWalls();

		placeSnake();
		paintSnake();

		placeApple();
		paintApple();

	}

	/**
	 * Move the head of the snake in the given direction
	 * If direction is null then the snake will keep moving in the same direction as it was
	 * @param direction Direction
	 */
	public void moveSnake(final Direction direction) {
		unpaintTail();
		snake.turn(direction);
		placeSnake();
		paintSnake();
		if (snake.getHead().equals(apple)) {
			scoring.increase(POINTS_EARNED_PER_APPLE);
			snake.extend();
			placeApple();
			paintApple();
		}
		final boolean snakedCrossedWithItself = snake.selfCross();
		final boolean snakedHitWall = walls.stream().anyMatch(wall -> wall.getSegments().contains(snake.getHead()));
		if (snakedCrossedWithItself || snakedHitWall) {
			scoring.gameOver();
		}
	}

	/**
	 * Delete the tail of the snake for the board
	 */
	private void unpaintTail() {
		board.paintPath(Arrays.asList(snake.getTail()), GridCell.EMPTY.getColor());
	}

	/**
	 * Paint the whole snake on the board
	 */
	private void paintSnake() {
		board.paintPath(snake.getParts(), GridCell.SNAKE.getColor());
	}

	/**
	 * Paint the apple on the board
	 */
	private void paintApple() {
		board.paintImage(apple.getImage(), apple);
	}

	/**
	 * Paint the walls on the board
	 */
	private void paintWalls() {
		for (Wall wall : walls) {
			board.paintPath(wall.getSegments(), GridCell.WALL.getColor());			
		}
	}

	/**
	 * Get a random X coordinate within the grid boundaries
	 * @return int
	 */
	private int getRandomPositionX() {
		return rnd.nextInt(GRID_COLUMNS);
	}

	/**
	 * Get a random Y coordinate within the grid boundaries
	 * @return int
	 */
	private int getRandomPositionY() {
		return rnd.nextInt(GRID_ROWS);
	}

	/**
	 * Put the snake body parts on the board
	 */
	private void placeSnake() {
		for (Coords part : snake.getParts()) {
			board.setCell(GridCell.SNAKE, part);
		}
	}

	/**
	 * Put the apple on the board.
	 * Avoid the apple being placed inside the snake body or inside a wall.
	 */
	private void placeApple() {
		do {
			apple = new Apple(getRandomPositionX(), getRandomPositionY());
		} while (snake.getParts().contains(apple) ||
				walls.stream().anyMatch(wall -> wall.getSegments().contains(apple)));
		board.setCell(GridCell.APPLE, apple);
	}

	/**
	 * Read walls from a comma separated file.
	 * The file is read line by line.
	 * Then each line is split using commas as delimiter
	 */
	private void addWalls() {
		try (InputStream csv = getClass().getResourceAsStream("Walls.csv")) {
			final Scanner scanner = new Scanner(csv, "UTF-8");
			scanner.useDelimiter("\n");
			var lineNumber = 1;
			while (scanner.hasNext()) {
				final String line = scanner.next().strip();
				if (!line.isEmpty()) {
					final String[] data = line.split(",");
					if (data.length==4) {
						try {
							walls.add(new Wall().addSegment(data));
						} catch (NumberFormatException nfe) {
							System.err.println("Unparseable number at Walls.csv line " + lineNumber + " " + line);						
						} catch (IllegalArgumentException iae) {
							System.err.println(iae.getMessage() + " at Walls.csv line " + lineNumber + " " + line);						
						}
					} else {
						System.err.println("Error parsing walls line " + lineNumber + " wrong number of columns");					
					}		    		
				}
				lineNumber++;
			}
			scanner.close();
		} catch (IOException | NullPointerException e) {
			System.err.println("Could not load Walls.csv file");
		}		
	}

}