package htw.controllers.game.logic.playerActions.interact;

import java.util.ArrayList;

import htw.model.caves.CaveSystem;
import htw.model.entities.Entity;
import htw.utils.Coordinates;

public class InteractWithEntity {
	public static void interact(CaveSystem caves, Coordinates coordinates) {
		ArrayList<Entity> entities = caves.getCaveEntities(coordinates);

		if (entities == null) {
			return;
		}

		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			System.out.println(entity);
			entity.interact();
		}
	}
}
