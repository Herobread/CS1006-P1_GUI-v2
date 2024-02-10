package htw.model.caves;

import htw.utils.Coordinates;

/**
 * Represents a decoration object in a cave.
 */
public class Decoration {
	private int x;
	private int y;
	private int width = 48;
	private int height = 48;
	private String textureName;

	/**
	 * Constructs a decoration object with the specified coordinates and texture
	 * name.
	 *
	 * @param x           The x-coordinate of the decoration.
	 * @param y           The y-coordinate of the decoration.
	 * @param textureName The name of the texture representing the decoration.
	 */
	public Decoration(int x, int y, String textureName) {
		this.x = x;
		this.y = y;
		this.textureName = textureName;
	}

	/**
	 * Constructs a decoration object with the specified coordinates and texture
	 * name.
	 *
	 * @param coordinates The coordinates of the decoration.
	 * @param textureName The name of the texture representing the decoration.
	 */
	public Decoration(Coordinates coordinates, String textureName) {
		this(coordinates.getX(), coordinates.getY(), textureName);
	}

	/**
	 * Retrieves the x-coordinate of the decoration.
	 *
	 * @return The x-coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x-coordinate of the decoration.
	 *
	 * @param x The new x-coordinate.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retrieves the y-coordinate of the decoration.
	 *
	 * @return The y-coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y-coordinate of the decoration.
	 *
	 * @param y The new y-coordinate.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Retrieves the name of the texture representing the decoration.
	 *
	 * @return The texture name.
	 */
	public String getTextureName() {
		return textureName;
	}

	/**
	 * Sets the name of the texture representing the decoration.
	 *
	 * @param textureName The new texture name.
	 */
	public void setTextureName(String textureName) {
		this.textureName = textureName;
	}

	/**
	 * Retrieves the width of the decoration.
	 *
	 * @return The width of the decoration.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the decoration.
	 *
	 * @param width The new width of the decoration.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Retrieves the height of the decoration.
	 *
	 * @return The height of the decoration.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the decoration.
	 *
	 * @param height The new height of the decoration.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
}
