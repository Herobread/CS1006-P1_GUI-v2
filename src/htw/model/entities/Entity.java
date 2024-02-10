package htw.model.entities;

/**
 * Represents an entity in the game world.
 */
public class Entity {
	protected String name;
	protected String textureName;
	protected boolean isHazard;
	protected boolean isShootable;
	protected boolean removeOnShot;

	/**
	 * Constructs an entity with the specified name and texture name.
	 *
	 * @param name        The name of the entity.
	 * @param textureName The name of the texture representing the entity.
	 */
	public Entity(String name, String textureName) {
		this.name = name;
		this.textureName = textureName;
		this.isHazard = false;
	}

	/**
	 * Constructs an entity with the specified name, texture name, and hazard
	 * status.
	 *
	 * @param name        The name of the entity.
	 * @param textureName The name of the texture representing the entity.
	 * @param isHazard    Indicates whether the entity is a hazard.
	 */
	public Entity(String name, String textureName, boolean isHazard) {
		this.name = name;
		this.textureName = textureName;
		this.isHazard = isHazard;
	}

	/**
	 * Retrieves the name of the entity.
	 *
	 * @return The name of the entity.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the entity.
	 *
	 * @param name The new name of the entity.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Performs an interaction with the entity.
	 */
	public void interact() {
		System.out.println("Interacting with " + name);
	}

	/**
	 * Retrieves the name of the texture representing the entity.
	 *
	 * @return The name of the texture.
	 */
	public String getTextureName() {
		return textureName;
	}

	/**
	 * Sets the name of the texture representing the entity.
	 *
	 * @param textureName The new name of the texture.
	 */
	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

	/**
	 * Checks if the entity is a hazard.
	 *
	 * @return True if the entity is a hazard, false otherwise.
	 */
	public boolean isHazard() {
		return isHazard;
	}

	/**
	 * Sets the hazard status of the entity.
	 *
	 * @param isHazard True if the entity is a hazard, false otherwise.
	 */
	public void setHazard(boolean isHazard) {
		this.isHazard = isHazard;
	}

	/**
	 * Performs an action when the entity is shot.
	 */
	public void onShot() {
		System.out.println(name + " shot");
	}

	/**
	 * Checks if the entity is shootable.
	 *
	 * @return True if the entity is shootable, false otherwise.
	 */
	public boolean isShootable() {
		return isShootable;
	}

	/**
	 * Sets the shootable status of the entity.
	 *
	 * @param isShootable True if the entity is shootable, false otherwise.
	 */
	public void setShootable(boolean isShootable) {
		this.isShootable = isShootable;
	}

	/**
	 * Checks if the entity should be removed when shot.
	 *
	 * @return True if the entity should be removed when shot, false otherwise.
	 */
	public boolean isRemoveOnShot() {
		return removeOnShot;
	}

	/**
	 * Sets whether the entity should be removed when shot.
	 *
	 * @param removeOnShot True if the entity should be removed when shot, false
	 *                     otherwise.
	 */
	public void setRemoveOnShot(boolean removeOnShot) {
		this.removeOnShot = removeOnShot;
	}

	/**
	 * Returns a string representation of the entity.
	 *
	 * @return A string representing the entity.
	 */
	@Override
	public String toString() {
		return "Entity [name=" + name + ", textureName=" + textureName + ", isHazard=" + isHazard +
				", isShootable=" + isShootable + "]";
	}

	/**
	 * Computes the hash code for the entity.
	 *
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Checks if this entity is equal to another object.
	 *
	 * @param obj The object to compare.
	 * @return True if the objects are equal, false otherwise.
	 */
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
}
