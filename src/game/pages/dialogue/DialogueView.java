package game.pages.dialogue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.controllers.state.GameStateManager;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.view.Renderer;

public class DialogueView extends ViewBase {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public DialogueView() {
		super("Dialogue");
	}

	// button action listeneres
	private ActionListener nextButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToGameplay();
			// viewManager.displayWindow();
		}
	};

	// rendering
	@Override
	public void renderView() {
		renderer.clear();

		System.out.println("[Dialogue] Rendering");

		renderer.drawTexture("bat", 192, 175, 128, 128);
		renderer.drawTexture("next", 432, 432, 72, 72);
		renderer.drawText("Bat bat bat bat bat bat bat", 113, 317, 12, Color.WHITE);

		renderer.drawButtonUnstable("next", 100, 450, nextButtonActionListener);

		renderer.draw();
	}

	// logic
	@Override
	public void update() {
		System.out.println("[dialogue] Updating model in dialogue");

		// String action = gameStateManager.getInputAction();

		// if (action.equals("something")) {
		// System.out.println("something clicked");
		// ViewManager.getInstance().switchToGameplay();
		// }

		// ViewManager manager = ViewManager.getInstance();
		// manager.switchToGameplay();
	}

	@Override
	public void run() {
		update();
		renderView();
	}

}
