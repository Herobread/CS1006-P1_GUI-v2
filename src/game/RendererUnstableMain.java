// testing the Renderer unstable

package game;

import game.view.Renderer;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(500, 500);
		renderer.drawText("Hello World", 0, 0, 30);
		renderer.drawTexture("cave-urdl", 0, 0,400,400,.5f);
		renderer.drawTexture("cave-udl", 200, 200,400,400,.5f);
	}
}