package leo.games.snake;

import java.util.ArrayList;
import java.util.List;

import static leo.games.snake.Direction.*;

import static leo.games.snake.Constants.GRID_COLUMNS;
import static leo.games.snake.Constants.GRID_ROWS;
import static leo.games.snake.Constants.INITIAL_SNAKE_LENGTH;

public class Snake {

	/**
	 * The snake is defined as a list of Coords that will be later plotted on the Grid
	 */
	private final ArrayList<Coords> bodyParts;
	
	/**
	 * The snake never stops, it's always moving in some direction
	 */
	private Direction moving;

	public Snake() {
		bodyParts = new ArrayList<>(500);
		int sx = GRID_COLUMNS / 2;
		int sy = GRID_ROWS / 2;
		for (int l=0; l<INITIAL_SNAKE_LENGTH && l<GRID_COLUMNS-1; l++)
			add(++sx % GRID_COLUMNS, sy);
		moving = WEST;
	}

	public void add(final int x, final int y) {
		bodyParts.add(new Coords(x,y));
	}

	public List<Coords> getParts() {
		return bodyParts;
	}

	public List<Coords> getBody() {
		return bodyParts.subList(1, bodyParts.size());
	}

	public Coords getHead() {
		return bodyParts.get(0);
	}

	public Coords getTail() {
		return bodyParts.get(bodyParts.size()-1);
	}

	public boolean selfCross() {
		for (Coords part : getBody())
			if (getHead().equals(part))
				return true;
		return false;
	}

	public void extend() {
		final Coords tail = bodyParts.get(bodyParts.size()-1);
		final Coords part = bodyParts.get(bodyParts.size()-2);
		Coords preferedExtend;
		if (tail.getX()>part.getX())
			preferedExtend = new Coords((tail.getX()+1) % GRID_COLUMNS, tail.getY());
		else if (tail.getX()<part.getX())
			preferedExtend = new Coords(tail.getX()>0 ? tail.getX()-1 : GRID_COLUMNS-1, tail.getY());
		else if (tail.getY()>part.getY())
			preferedExtend = new Coords(tail.getX(), (tail.getY()+1) % GRID_ROWS);
		else
			preferedExtend = new Coords(tail.getX(), tail.getY()>0 ? tail.getY()-1 : GRID_ROWS-1);
		bodyParts.add(preferedExtend);
	}

	public void turn(final Direction direction) {

		// Shift tail
		for (int c=bodyParts.size()-1; c>0; c--)
			bodyParts.get(c).set(bodyParts.get(c-1));
		
		// Turn head
		if (direction!=null) {
			switch(direction) {
			case NORTH:
				if (!SOUTH.equals(moving))
					moving = direction;
				break;
			case SOUTH:
				if (!NORTH.equals(moving))
					moving = direction;
				break;
			case EAST:
				if (!WEST.equals(moving))
					moving = direction;
				break;
			case WEST:
				if (!EAST.equals(moving))
					moving = direction;
				break;
			}
		}
		getHead().move(moving, GRID_COLUMNS, GRID_ROWS);
	}

}
