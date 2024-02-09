package htw.controllers.game.logic.movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import htw.model.caves.CaveSystem;
import htw.utils.Coordinates;

public class RandomPlace {
	public static Coordinates find(CaveSystem caves) {
		int width = caves.getWidth();
		int height = caves.getHeight();
		List<Coordinates> availablePlaces = new ArrayList<>();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Coordinates currentCaveCoordinates = new Coordinates(x, y);

				if (caves.isSolid(currentCaveCoordinates)) {
					continue;
				}

				availablePlaces.add(currentCaveCoordinates);
			}
		}

		if (availablePlaces.size() == 0) {
			return null;
		}

		Collections.shuffle(availablePlaces);

		return availablePlaces.get(0);
	}
}
