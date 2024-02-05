package game.pages.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.controllers.state.GameStateManager;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.view.Renderer;

public class GameView extends ViewBase {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public GameView() {
		super("Gameplay");
	}

	private ActionListener mainMenuButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Button clicked!");
			gameStateManager.setInputAction("some action");
			viewManager.switchToMainMenu();
			viewManager.displayWindow();
		}
	};

	@Override
	public void renderView() {
		System.out.println("[Game view] Rendering GameView");

		renderer.clear();

		// buttons up:
		renderer.drawTexture("shoot", 176, 8, 72, 72);
		renderer.drawTexture("walk-up", 256, 8, 72, 72);

		// buttons down:
		renderer.drawTexture("shoot", 176, 432, 72, 72);
		renderer.drawTexture("walk-down", 256, 432, 72, 72);

		// buttons left:
		renderer.drawTexture("shoot", 8, 176, 72, 72);
		renderer.drawTexture("walk-left", 8, 256, 72, 72);

		// buttons right:
		renderer.drawTexture("shoot", 432, 176, 72, 72);
		renderer.drawTexture("walk-right", 432, 256, 72, 72);

		// info:
		renderer.drawTexture("dialogue-bs", 208, 144, 144, 64);

		// main
		renderer.drawTexture("map", 8, 8, 72, 72);
		renderer.drawTexture("player", 192, 192, 128, 128);
		renderer.drawTexture("shadow", 176, 273, 128, 64);
		renderer.drawTexture("cave-urdl", 0, 0, 512, 512);

		// renderer.drawButtonUnstable("Play", 100, 450, playButtonActionListener);

		renderer.draw();
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
