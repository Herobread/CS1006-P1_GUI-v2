package game.controllers.game.logic.movement;

import game.utils.Coordinates;
import game.utils.Direction;

public class CoordinateCalculator {
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
