package game.pages.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import game.controllers.state.GameStateManager;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.view.Renderer;

public class GameView extends ViewBase {
	// private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public GameView() {
		super("Gameplay");
	}

	private ActionListener dialogueButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToDialogue();
		}
	};

	private ActionListener mapButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToMap();
		}
	};

	@Override
	public void renderView() {
		System.out.println("[Game view] Rendering GameView");

		renderer.clear();

		// temp
		renderer.drawButtonUnstable("Dialogue test", 100, 450, dialogueButtonActionListener);
		renderer.drawButtonUnstable("open map", 100, 350, mapButtonActionListener);

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
