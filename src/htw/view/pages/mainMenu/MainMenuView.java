package htw.view.pages.mainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import htw.controllers.view.ViewManager;
import htw.view.pages.ViewBase;
import htw.view.renderer.Renderer;

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

		// background image
		renderer.drawTexture("cave-", 0, 0, 512, 512);

		renderer.drawTexture("logo", 64, 116, 384, 160);
		renderer.drawButton("play", 172, 324, 168, 72, playButtonActionListener);

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
