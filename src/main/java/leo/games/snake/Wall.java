package leo.games.snake;

import java.util.ArrayList;
import java.util.List;

public class Wall {

	private final ArrayList<Coords> segments;

	public Wall() {
		segments = new ArrayList<>();
	}

	public List<Coords> getSegments() {
		return segments;
	}

	public void addSingleSegment(final int x, final int y) {
		segments.add(new Coords(x,y));
	}

	public void addHorizontalSegment(final int x, final int y, final int length) {
		for (int s=0; s<length; s++)
			segments.add(new Coords(x+s, y));
	}

	public void addVerticalSegment(final int x, final int y, final int length) {
		for (int s=0; s<length; s++)
			segments.add(new Coords(x, y+s));
	}

	public Wall addSegment(final String[] data)
		throws IllegalArgumentException, NumberFormatException {
		if ("H".equalsIgnoreCase(data[0]))
			addHorizontalSegment(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		else if ("V".equalsIgnoreCase(data[0]))
			addVerticalSegment(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		else
			throw new IllegalArgumentException("Unrecognized orientation " + data[0]);
		return this;
	}
}
