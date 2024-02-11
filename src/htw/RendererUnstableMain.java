// testing the Renderer unstable

package htw;

import htw.view.Renderer;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);
		renderer.drawButton("cave-u",100,100, 100, 100,null);
		renderer.draw();
	}
}