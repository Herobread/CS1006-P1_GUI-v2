// testing the Renderer unstable

package game;

public class RendererUnstableMain {
	public static void main(String[] args) {
		RendererUnstable renderer = new RendererUnstable();

		renderer.drawTexture("cave-dlru", 100, 100);

		renderer.draw();
	}
}
