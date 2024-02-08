package htw.model;

import java.util.ArrayList;

import htw.model.entities.Entity;

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

	@Override
	public String toString() {
		return "Cave [entities=" + entities + "]";
	}
}
