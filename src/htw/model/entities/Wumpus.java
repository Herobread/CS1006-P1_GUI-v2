package htw.model.entities;

public class Wumpus extends Entity {
	public Wumpus() {
		super("wumpus", "wumpus-teeth");
		setHazard(true);
		setShootable(true);

	}
}
