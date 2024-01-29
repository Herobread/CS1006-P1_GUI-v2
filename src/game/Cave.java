package game;

import java.util.ArrayList;

public class Cave {
	private boolean isTunnel;
	private ArrayList<Entity> entities;
	// is left
	// right

	public Cave(boolean isTunnel) {
		this.entities = new ArrayList<>();
		this.isTunnel = isTunnel;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public boolean isTunnel() {
		return isTunnel;
	}

	public void setTunnel(boolean isTunnel) {
		this.isTunnel = isTunnel;
	}
}
