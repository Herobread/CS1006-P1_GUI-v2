package htw.model.entities;

import htw.utils.Coordinates;

public class Player extends Entity {
	private Coordinates coordinates;

	public Player(int x, int y) {
		super("Player", "player");

		coordinates = new Coordinates(x, y);
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public Coordinates getCoordinates() {
		return coordinates;
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
