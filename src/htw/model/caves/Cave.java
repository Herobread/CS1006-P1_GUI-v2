package htw.model.caves;

import java.util.ArrayList;

import htw.model.entities.Entity;

/**
 * Represents a cave in the CaveSystem.
 */
public class Cave {
	private ArrayList<Entity> entities;
	private ArrayList<Decoration> decorations;

	/**
	 * Constructs a new cave with empty lists of entities and decorations.
	 */
	public Cave() {
		this.entities = new ArrayList<>();
		this.decorations = new ArrayList<>();
	}

	/**
	 * Adds an entity to the cave.
	 *
	 * @param entity The entity to add.
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	/**
	 * Removes an entity from the cave.
	 *
	 * @param entity The entity to remove.
	 */
	public void removeEntity(Entity entity) {
		if (entity == null) {
			return;
		}

		entities.remove(entity);
	}

	/**
	 * Retrieves the list of entities in the cave.
	 *
	 * @return The list of entities.
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * Adds a decoration to the cave.
	 *
	 * @param decoration The decoration to add.
	 */
	public void addDecoration(Decoration decoration) {
		decorations.add(decoration);
	}

	/**
	 * Retrieves the list of decorations in the cave.
	 *
	 * @return The list of decorations.
	 */
	public ArrayList<Decoration> getDecorations() {
		return decorations;
	}

	/**
	 * Returns a string representation of the cave, including its entities and
	 * decorations.
	 *
	 * @return A string representing the cave.
	 */
	@Override
	public String toString() {
		String result = "Cave:\nEntities:\n";

		for (Entity entity : entities) {
			result += entity.toString() + "\n";
		}

		result += "Decorations:\n";
		for (Decoration decoration : decorations) {
			result += decoration.toString() + "\n";
		}

		return result;
	}

}
