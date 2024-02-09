package htw.controllers.game.logic.playerActions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import htw.model.caves.CaveSystem;
import htw.model.entities.Entity;
import htw.utils.Coordinates;

public class Senses {
	public static String checkNeighbours(CaveSystem caves, Coordinates coordinates) {
		String result = "";

		HashSet<Coordinates> neighbours = caves.getCaveConnections(coordinates);
		Set<Character> hazards = new HashSet<>();

		for (Coordinates neighbourCoordinates : neighbours) {
			ArrayList<Entity> entities = caves.getCaveEntities(neighbourCoordinates);

			for (Entity entity : entities) {
				if (entity.isHazard()) {
					char entityIdentifier = entity.getName().charAt(0);
					hazards.add(entityIdentifier);
				}
			}
		}

		List<Character> sortedHazards = new ArrayList<>(hazards);
		Collections.sort(sortedHazards);

		StringBuilder sb = new StringBuilder();
		for (char hazard : sortedHazards) {
			sb.append(hazard);
		}

		result = sb.toString();

		return result;
	}
}
