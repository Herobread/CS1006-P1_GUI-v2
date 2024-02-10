package htw.controllers.game.logic.playerActions.interact;

import java.util.ArrayList;

import htw.model.caves.CaveSystem;
import htw.model.entities.Entity;
import htw.utils.Coordinates;

/**
 * Provides methods for interacting with entities at specific coordinates within
 * the cave system.
 */
public class InteractWithEntity {

	/**
	 * Interacts with entities located at the specified coordinates in the cave
	 * system.
	 *
	 * @param caves       The cave system containing the entities.
	 * @param coordinates The coordinates of the cave to interact with entities.
	 */
	public static void interact(CaveSystem caves, Coordinates coordinates) {
		ArrayList<Entity> entities = caves.getCaveEntities(coordinates);

		if (entities == null) {
			return;
		}

		// for i prevents the java.util.ConcurrentModificationException
		// that for each causes
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			entity.interact();
		}
	}
}
