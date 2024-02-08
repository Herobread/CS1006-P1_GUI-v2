package htw.model.map;

import htw.utils.Coordinates;

public class ExploredMap {
	public enum TileState {
		WALL,
		CAVE,
		HAZARD
	}

	private int width;
	private int height;

	private TileState[][] exploredMap;

	public ExploredMap(int width, int height) {
		this.width = width;
		this.height = height;

		exploredMap = new TileState[height][width];
	}

	public void markTile(TileState mark, Coordinates coordinates) {
		markTile(mark, coordinates.getX(), coordinates.getY());
	}

	public void markTile(TileState mark, int x, int y) {
		exploredMap[y][x] = mark;
	}

	public TileState getTile(int x, int y) {
		return exploredMap[y][x];
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TileState[][] getExploredMap() {
		return exploredMap;
	}

	public void setExploredMap(TileState[][] exploredMap) {
		this.exploredMap = exploredMap;
	}
}
