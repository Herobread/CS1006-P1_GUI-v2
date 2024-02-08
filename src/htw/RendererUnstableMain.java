// testing the Renderer unstable

package htw;

import java.awt.Color;
import java.util.Random;

import htw.view.Renderer;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);
		String textureName = "";
		for (int x = 0; x < renderer.getFrameWidth(); x += 64) {
			for (int y = 0; y < renderer.getFrameHeight(); y += 64) {
				Random rand = new Random();
				switch (rand.nextInt(1, 15)) {
					case 1:
						textureName = "cave-u";
						break;
					case 2:
						textureName = "cave-l";
						break;
					case 3:
						textureName = "cave-r";
						break;
					case 4:
						textureName = "cave-d";
						break;
					case 5:
						textureName = "cave-ur";
						break;
					case 6:
						textureName = "cave-ud";
						break;
					case 7:
						textureName = "cave-ul";
						break;
					case 8:
						textureName = "cave-rd";
						break;
					case 9:
						textureName = "cave-rl";
						break;
					case 10:
						textureName = "cave-dl";
						break;
					case 11:
						textureName = "cave-urd";
						break;
					case 12:
						textureName = "cave-url";
						break;
					case 13:
						textureName = "cave-rdl";
					case 14:
						textureName = "cave-udl";
						break;
					case 15:
						textureName = "cave-urdl";
						break;
					default:
						textureName = "cave-urdl";
						break;
				}
				renderer.drawTexture(textureName, x, y, rand.nextFloat(.3f, .7f));
			}
		}
		renderer.drawText("Swing can do one", 20, 0, 60, Color.BLUE);
		renderer.draw();
	}
}