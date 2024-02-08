package game.view.pages.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import game.controllers.state.GameStateManager;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.view.Renderer;

public class MainMenuView extends ViewBase {
	// private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public MainMenuView() {
		super("Main menu");
	}

	// button action listeneres
	private ActionListener playButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			viewManager.switchToGameplay();
		}
	};

	// rendering
	@Override
	public void renderView() {
		renderer.clear();

		renderer.drawTexture("logo", 64, 116, 384, 160);
		renderer.drawTexture("play", 172, 324, 168, 72);
		renderer.drawClickAreaUnstable(172, 324, 168, 72, playButtonActionListener);

		renderer.draw();
	}

	// logic
	@Override
	public void update() {
	}

	@Override
	public void run() {
		update();
		renderView();
	}
}
