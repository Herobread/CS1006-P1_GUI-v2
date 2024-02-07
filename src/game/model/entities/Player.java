package game.model.entities;

import game.utils.Coordinates;

public class Player extends Entity {
	Coordinates coordinates;

	public Player(int x, int y) {
		super("Player");

		coordinates.setX(x);
		coordinates.setY(y);
	}

	public int getX() {
		return coordinates.getX();
	}

	public void setX(int x) {
		coordinates.setX(x);
	}

	public int getY() {
		return coordinates.getY();
	}

	public void setY(int y) {
		coordinates.setY(y);
	}
}
