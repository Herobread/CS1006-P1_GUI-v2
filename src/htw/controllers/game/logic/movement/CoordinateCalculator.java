package htw.controllers.game.logic.movement;

import htw.utils.Coordinates;
import htw.utils.Direction;

/**
 * Provides methods for calculating distances and target coordinates based on
 * directions.
 */
public class CoordinateCalculator {

	/**
	 * Calculates the Euclidean distance between two coordinates.
	 *
	 * @param c1 The first coordinate.
	 * @param c2 The second coordinate.
	 * @return The distance between the two coordinates.
	 */
	public static int calculateDistance(Coordinates c1, Coordinates c2) {
		int xDif = c1.getX() - c2.getX();
		int yDif = c1.getY() - c2.getY();

		return (int) Math.sqrt(xDif * xDif + yDif * yDif);
	}

	/**
	 * Calculates a descriptive string for the distance between two coordinates.
	 *
	 * @param c1 The first coordinate.
	 * @param c2 The second coordinate.
	 * @return A descriptive string indicating the distance between the two
	 *         coordinates.
	 */
	public static String calculateDistanceString(Coordinates c1, Coordinates c2) {
		int distance = calculateDistance(c1, c2);

		if (distance < 3) {
			return "to a nearby spot";
		}

		if (distance < 6) {
			return "on a brief journey";
		}

		if (distance < 10) {
			return "through a scenic route";
		}

		return "across distant lands";
	}

	/**
	 * Calculates the target coordinates based on the current coordinates and
	 * direction.
	 *
	 * @param currentCoordinates The current coordinates.
	 * @param direction          The direction to move.
	 * @return The target coordinates after moving in the specified direction.
	 */
	public static Coordinates calculateTargetCoordinates(Coordinates currentCoordinates, Direction direction) {
		int x = currentCoordinates.getX();
		int y = currentCoordinates.getY();

		switch (direction) {
			case DOWN:
				y += 1;
				break;
			case UP:
				y -= 1;
				break;
			case LEFT:
				x -= 1;
				break;
			case RIGHT:
				x += 1;
				break;
			default:
				break;
		}

		return new Coordinates(x, y);
	}
}
