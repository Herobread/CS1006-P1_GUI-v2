package game.controllers.view;

public class MainMenuView extends ViewBase {
	public MainMenuView(String name) {
		super(name);
	}

	@Override
	public void handleInput() {
		// Provide the implementation for handling input in the game view
		System.out.println("Handling input in Main menu:");
		System.out.println("assuming user pressed play");
	}

	@Override
	public void renderView() {
		// Provide the implementation for rendering the game view
		System.out.println("Rendering Main menu");
	}

	@Override
	public void update() {
		// Provide the implementation for updating the game model in the view
		System.out.println("Updating model in Main menu");

		ViewManager manager = ViewManager.getInstance();
		manager.switchToGameplay();
	}

	@Override
	public void run() {
		handleInput();
		renderView();
		update();
	}

}
