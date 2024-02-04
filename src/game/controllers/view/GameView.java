package game.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.controllers.state.GameStateManager;
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
		// Provide the implementation for rendering the game view
		Renderer.getInstance().clear();
		Renderer.getInstance().drawTexture("bat", 10, 10, 80, 80);
		renderer.drawButtonUnstable("Main menu", 50, 150, mainMenuButtonActionListener);

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
