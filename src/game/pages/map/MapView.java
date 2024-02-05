package game.pages.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.controllers.state.GameStateManager;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.view.Renderer;

public class MapView extends ViewBase {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public MapView() {
		super("Map");
	}

	// button action listeneres
	private ActionListener closeButtonActionListener = new ActionListener() {
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

		System.out.println("[Map] Rendering");

		renderer.drawTexture("cross", 432, 10, 72, 72);

		renderer.drawText("map", 100, 100, 20);

		renderer.drawButtonUnstable("close", 100, 450, closeButtonActionListener);

		renderer.draw();
	}

	// logic
	@Override
	public void update() {
		System.out.println("[Main menu] Updating model in Main menu");

		String action = gameStateManager.getInputAction();

		if (action.equals("something")) {
			System.out.println("something clicked");
			// ViewManager.getInstance().switchToGameplay();
		}

		// ViewManager manager = ViewManager.getInstance();
		// manager.switchToGameplay();
	}

	@Override
	public void run() {
		update();
		renderView();
	}

}
