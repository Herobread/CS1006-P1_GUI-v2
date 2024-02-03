package game.controllers.view;

import game.view.Renderer;

public class GameView extends ViewBase {
	public GameView(String name) {
		super(name);
	}

	@Override
	public void handleInput() {
		// Provide the implementation for handling input in the game view
		System.out.println("[Game view] Handling input in GameView");
	}

	@Override
	public void renderView() {
		// Provide the implementation for rendering the game view
		Renderer.getInstance().clear();
		Renderer.getInstance().drawTexture("bat", 10, 10, 80, 80);
		Renderer.getInstance().draw();
		System.out.println("[Game view] Rendering GameView");
	}

	@Override
	public void update() {
		// Provide the implementation for updating the game model in the view
		System.out.println("[Game view] Updating model in GameView");
	}

	@Override
	public void run() {
		handleInput();
		renderView();
		update();
	}
}
