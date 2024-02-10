package htw.controllers.game.logic.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import htw.model.caves.CaveSystem;
import htw.utils.Coordinates;

/**
 * Provides methods for finding random available places in a cave system.
 */
public class RandomPlace {

	/**
	 * Finds a random available place in the cave system.
	 *
	 * @param caves The cave system.
	 * @return The coordinates of the random available place, or null if no
	 *         available places are found.
	 */
	public static Coordinates find(CaveSystem caves) {
		int width = caves.getWidth();
		int height = caves.getHeight();
		List<Coordinates> availablePlaces = new ArrayList<>();

		// Iterate through all caves to find available places
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Coordinates currentCaveCoordinates = new Coordinates(x, y);

				if (caves.isSolid(currentCaveCoordinates)) {
					continue;
				}

				availablePlaces.add(currentCaveCoordinates);
			}
		}

		// If no available places are found, return null
		if (availablePlaces.size() == 0) {
			return null;
		}

		// Shuffle the list of available places and return the first one
		Collections.shuffle(availablePlaces);
		return availablePlaces.get(0);
	}
}
