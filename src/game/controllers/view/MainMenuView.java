package game.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.controllers.state.GameStateManager;
import game.view.Renderer;

public class MainMenuView extends GameView {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	// private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();

	public MainMenuView() {
		super("Main menu");
	}

	public void init() {
		System.out.println("Initializing " + name + " view");
	}

	// button action listeneres
	private ActionListener playButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("Button clicked!");
			gameStateManager.setInputAction("play");
			// viewManager.switchToGameplay();
			// viewManager.displayWindow();
		}
	};
	private ActionListener somethingButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			System.out.println("something Button clicked!");
			gameStateManager.setInputAction("something");
			gameStateManager.setSomeNumber(gameStateManager.getSomeNumber() + 1);
			run();
		}
	};

	// rendering
	@Override
	public void renderView() {
		renderer.clear();

		System.out.println("[Main menu] Rendering Main menu");

		renderer.drawButtonUnstable("Play", 50, 50, playButtonActionListener);
		renderer.drawButtonUnstable("Something else", 50, 150, somethingButtonActionListener);

		renderer.drawText(gameStateManager.getSomeNumber() + " <- number", 50, 200, 12);

		renderer.drawTexture("logo", 10, 10);

		renderer.draw();
	}

	// logic
	@Override
	public void update() {
		// Provide the implementation for updating the game model in the view
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
