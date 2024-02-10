package htw.model.entities;

public class Entity {
	protected String name;
	protected String textureName;
	protected boolean isHazard;
	protected boolean isShootable;
	protected boolean removeOnShot;

	public Entity(String name, String textureName) {
		this.name = name;
		this.textureName = textureName;
		this.isHazard = false;
	}

	public Entity(String name, String textureName, boolean isHazard) {
		this.name = name;
		this.textureName = textureName;
		this.isHazard = isHazard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void interact() {
		System.out.println("Interacting with " + name);
	}

	@Override
	public String toString() {
		return "Entity [name=" + name + ", textureName=" + textureName + ", isHazard=" + isHazard + ", isShootable="
				+ isShootable + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

	public boolean isHazard() {
		return isHazard;
	}

	public void setHazard(boolean isHazard) {
		this.isHazard = isHazard;
	}

	public void onShot() {
		System.out.println(name + " shot");
	}

	public boolean isShootable() {
		return isShootable;
	}

	public void setShootable(boolean isShootable) {
		this.isShootable = isShootable;
	}

	public boolean isRemoveOnShot() {
		return removeOnShot;
	}

	public void setRemoveOnShot(boolean removeOnShot) {
		this.removeOnShot = removeOnShot;
	}

}
