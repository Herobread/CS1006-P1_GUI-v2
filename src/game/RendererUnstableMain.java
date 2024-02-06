// testing the Renderer unstable

package game;

import java.awt.Color;
import java.util.Random;

import game.view.Renderer;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);
		for (int x = 0; x < renderer.getFrameWidth();x+=64){
			for (int y = 0; y < renderer.getFrameHeight(); y+=64){
				Random rand = new Random();
				renderer.drawTexture("cave-u", x, y, rand.nextFloat(.3f,.7f));
			}
		}
		renderer.drawText("Swing can do one", 50, 50, 30, Color.BLUE);
		renderer.draw();
	}
}