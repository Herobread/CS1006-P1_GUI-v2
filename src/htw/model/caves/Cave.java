package htw.model.caves;

import java.util.ArrayList;

import htw.model.entities.Entity;

public class Cave {
	private ArrayList<Entity> entities;
	private ArrayList<Decoration> decorations;

	public Cave() {
		this.entities = new ArrayList<>();
		this.decorations = new ArrayList<>();
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void addDecoration(Decoration decoration) {
		decorations.add(decoration);
	}

	public ArrayList<Decoration> getDecorations() {
		return decorations;
	}

	@Override
	public String toString() {
		return "Cave [entities=" + entities + "]";
	}
}
