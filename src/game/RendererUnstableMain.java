// testing the Renderer unstable

package game;

public class RendererUnstableMain {
	public static void main(String[] args) {
		Renderer renderer = new Renderer();		
		while(true){
			renderer.drawText("Hello World", 0, 0, 30);
			renderer.drawTexture("cave-urdl", 0, 0,renderer.getFrameWidth(),renderer.getFrameHeight());		
			renderer.draw();
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
			renderer.clear();
			renderer.draw();
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){}
		}
		
	}
}