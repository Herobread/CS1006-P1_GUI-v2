package htw.model.map;

import htw.utils.Coordinates;

/**
 * Represents a map that tracks the exploration state of its tiles.
 */
public class ExploredMap {
	/**
	 * Represents the possible states of a tile on the explored map.
	 */
	public enum TileState {
		WALL,
		CAVE,
		HAZARD
	}

	private int width;
	private int height;
	private TileState[][] exploredMap;

	/**
	 * Constructs a new ExploredMap with the specified width and height.
	 *
	 * @param width  The width of the map.
	 * @param height The height of the map.
	 */
	public ExploredMap(int width, int height) {
		this.width = width;
		this.height = height;
		exploredMap = new TileState[height][width];
	}

	/**
	 * Marks the tile at the specified coordinates with the given state.
	 *
	 * @param mark        The state to mark the tile with.
	 * @param coordinates The coordinates of the tile to mark.
	 */
	public void markTile(TileState mark, Coordinates coordinates) {
		markTile(mark, coordinates.getX(), coordinates.getY());
	}

	/**
	 * Marks the tile at the specified coordinates with the given state.
	 *
	 * @param mark The state to mark the tile with.
	 * @param x    The x-coordinate of the tile.
	 * @param y    The y-coordinate of the tile.
	 */
	public void markTile(TileState mark, int x, int y) {
		exploredMap[y][x] = mark;
	}

	/**
	 * Retrieves the state of the tile at the specified coordinates.
	 *
	 * @param x The x-coordinate of the tile.
	 * @param y The y-coordinate of the tile.
	 * @return The state of the tile.
	 */
	public TileState getTile(int x, int y) {
		return exploredMap[y][x];
	}

	/**
	 * Retrieves the width of the explored map.
	 *
	 * @return The width of the map.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the explored map.
	 *
	 * @param width The new width of the map.
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Retrieves the height of the explored map.
	 *
	 * @return The height of the map.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the explored map.
	 *
	 * @param height The new height of the map.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Retrieves the 2D array representing the explored map.
	 *
	 * @return The explored map represented as a 2D array of TileStates.
	 */
	public TileState[][] getExploredMap() {
		return exploredMap;
	}

	/**
	 * Sets the explored map to the specified 2D array of TileStates.
	 *
	 * @param exploredMap The new explored map.
	 */
	public void setExploredMap(TileState[][] exploredMap) {
		this.exploredMap = exploredMap;
	}
}
