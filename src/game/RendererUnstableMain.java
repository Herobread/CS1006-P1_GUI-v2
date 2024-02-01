// testing the Renderer unstable

package game;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = new Renderer(500, 500);
		renderer.drawText("Hello World", 0, 0, 30);
		renderer.drawTexture("cave-urdjl", 0, 0, renderer.getFrameWidth(), renderer.getFrameHeight());
		renderer.draw();

	}
}