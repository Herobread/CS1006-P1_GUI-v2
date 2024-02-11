package htw.model.entities;

import htw.utils.Coordinates;

public class Player extends Entity {
	private Coordinates coordinates;
	private int arrows = 5;
	private int gold = 0;
	private int batsShot = 0;
	private int wumpusesShot = 0;

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

	public int getArrows() {
		return arrows;
	}

	public void setArrows(int arrows) {
		this.arrows = arrows;
	}

	public boolean useArrow() {
		if (arrows > 0) {
			arrows -= 1;
			return true;
		}

		return false;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void addGold(int amount) {
		gold += amount;
	}

	public int getBatsShot() {
		return batsShot;
	}

	public void setBatsShot(int batsShot) {
		this.batsShot = batsShot;
	}

	public void addBatsShot(int amount) {
		batsShot += amount;
	}

	public int getWumpusesShot() {
		return wumpusesShot;
	}

	public void setWumpusesShot(int wumpusesShot) {
		this.wumpusesShot = wumpusesShot;
	}

	public void addWumpusShot(int amount) {
		wumpusesShot += amount;
	}
}
