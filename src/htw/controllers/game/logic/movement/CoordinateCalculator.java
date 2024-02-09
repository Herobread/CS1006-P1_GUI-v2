package htw.controllers.game.logic.movement;

import htw.utils.Coordinates;
import htw.utils.Direction;

public class CoordinateCalculator {
	public static int calculateDistance(Coordinates c1, Coordinates c2) {
		int xDif = c1.getX() - c2.getX();
		int yDif = c1.getY() - c2.getY();

		int distance = (int) Math.sqrt(xDif * xDif + yDif * yDif);

		return distance;
	}

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
