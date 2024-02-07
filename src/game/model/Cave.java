package game.model;

import java.util.ArrayList;

import game.model.entities.Entity;

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
