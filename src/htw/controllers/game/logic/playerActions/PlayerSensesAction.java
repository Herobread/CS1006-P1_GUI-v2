package htw.controllers.game.logic.playerActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import htw.model.caves.CaveSystem;
import htw.model.entities.Entity;
import htw.utils.Coordinates;

/**
 * Provides methods for sensing hazards in neighboring caves.
 */
public class PlayerSensesAction {

	/**
	 * Checks neighboring caves for hazards and returns a string representing the
	 * hazards found.
	 *
	 * @param caves       The cave system.
	 * @param coordinates The coordinates of the cave to check neighbors for
	 *                    hazards.
	 * @return A string representing the hazards found in neighboring caves.
	 */
	public static String checkNeighbours(CaveSystem caves, Coordinates coordinates) {
		// Get neighboring caves
		HashSet<Coordinates> neighbours = caves.getCaveConnections(coordinates);
		Set<Character> hazards = new HashSet<>();

		// Check entities in neighboring caves for hazards
		for (Coordinates neighbourCoordinates : neighbours) {
			ArrayList<Entity> entities = caves.getCaveEntities(neighbourCoordinates);

			for (Entity entity : entities) {
				if (entity.isHazard()) {
					char entityIdentifier = entity.getName().charAt(0);
					hazards.add(entityIdentifier);
				}
			}
		}

		// Sort hazards alphabetically
		List<Character> sortedHazards = new ArrayList<>(hazards);
		Collections.sort(sortedHazards);

		// Construct result string
		String result = "";
		for (char hazard : sortedHazards) {
			result += hazard;
		}

		return result;
	}
}
