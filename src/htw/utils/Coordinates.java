package htw.utils;

/**
 * Represents coordinates with x and y values.
 */
public class Coordinates {
	private int x;
	private int y;

	/**
	 * Constructs a new Coordinates object with the given x and y values.
	 *
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Retrieves the x coordinate.
	 *
	 * @return The x coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate.
	 *
	 * @param x The new x coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retrieves the y coordinate.
	 *
	 * @return The y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate.
	 *
	 * @param y The new y coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Computes the hash code for this object.
	 *
	 * @return The hash code.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		return result;
	}

	/**
	 * Checks if this object is equal to another object.
	 *
	 * @param obj The object to compare.
	 * @return True if the objects are equal, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Coordinates other = (Coordinates) obj;

		return x == other.x && y == other.y;
	}

	/**
	 * Generates a string representation of this object.
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString() {
		return "(x: " + x + ", y: " + y + ")";
	}
}
