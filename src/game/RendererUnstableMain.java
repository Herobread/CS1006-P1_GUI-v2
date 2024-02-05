// testing the Renderer unstable

package game;

import game.view.Renderer;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);
		renderer.drawText("Hello World", 0, 0, 30);
		renderer.drawTexture("cave-u", 0, 0);
		renderer.drawTexture("cave-ur", 50, 50,.5f);
		renderer.drawTexture("cave-urd", 100, 100,100,100);
		renderer.drawTexture("cave-urdl", 200, 200, 300, 300, .1f);
	}
}