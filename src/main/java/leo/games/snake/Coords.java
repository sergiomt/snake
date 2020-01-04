package leo.games.snake;

/**
 * Represents the position of a GridCell inside the Grid.
 * Used to store the location of the snake body, the walls and the apple.
 */
public class Coords {

	private int x;
	private int y;
	
	public Coords(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	public void setX(final int x) {
		this.x  = x;
	}

	public int getX() {
		return x;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void set(final Coords sourceCoords) {
		setX(sourceCoords.getX());
		setY(sourceCoords.getY());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coords)
			return getX()==((Coords) obj).getX() && getY()==((Coords) obj).getY();
		else
			return false;
	}

	/**
	 * 
	 * @param direction Direction where to move
	 * @param xBound int Maximum number of columns on the grid (GRID_COLUMNS)
	 * @param yBound int Maximum number of rows on the grid (GRID_ROWS)
	 */
	public void move(final Direction direction, final int xBound, final int yBound) {
		switch (direction) {
		case NORTH:
			y = y>0 ? y-1 : yBound-1;
			break;
		case SOUTH:
			y = y<yBound-1 ? y+1 : 0;
			break;
		case EAST:
			x = x<xBound-1 ? x+1 : 0;
			break;
		case WEST:
			x = x>0 ? x-1 : xBound-1;
			break;
		}
	}
}
