package leo.games.snake;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Observable score
 *
 */
public class Score {

	public static final String POINTS = "points";
	public static final String GAME_OVER = "gameover";

	private int points;

	private PropertyChangeSupport change;

	public Score() {
		points = 0;
		change = new PropertyChangeSupport(this);
	}

	/**
	 * Increase score by the given number of points
	 * and fire event to notify the change.
	 * @param p int Points
	 */
	public void increase(final int p) {
		change.firePropertyChange(POINTS, points, p);
		points += p;
	}

	/**
	 * Fire event for Game Over
	 */
	public void gameOver() {
		change.firePropertyChange(GAME_OVER, false, true);
	}

	public int getPoints() {
		return points;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        change.addPropertyChangeListener(pcl);
    }
 
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
    	change.removePropertyChangeListener(pcl);
    }

}
