import java.util.ArrayList;
import java.util.List;

public class Cave {
	private boolean isTunnel;
	private List<Entity> entities;
	// is left
	// right

	public Cave(boolean isTunnel) {
		this.entities = new ArrayList<>();
		this.isTunnel = isTunnel;
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public boolean isTunnel() {
		return isTunnel;
	}

	public void setTunnel(boolean isTunnel) {
		this.isTunnel = isTunnel;
	}
}
