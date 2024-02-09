package htw.model.caves;

import htw.utils.Coordinates;

public class Decoration {
	private int x;
	private int y;
	private int width = 48;
	private int height = 48;
	private String textureName;

	public Decoration(int x, int y, String textureName) {
		this.x = x;
		this.y = y;
		this.textureName = textureName;
	}

	public Decoration(Coordinates coordinates, String textureName) {
		this(coordinates.getX(), coordinates.getY(), textureName);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getTextureName() {
		return textureName;
	}

	public void setTextureName(String textureName) {
		this.textureName = textureName;
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

}
