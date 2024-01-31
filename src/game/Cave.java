package game;

import java.util.ArrayList;

public class Cave {
	private ArrayList<Entity> entities;

	public Cave() {
		this.entities = new ArrayList<>();
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}
}
