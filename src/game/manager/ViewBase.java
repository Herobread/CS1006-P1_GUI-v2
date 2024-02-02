package game.manager;

// template for views
public interface ViewBase {
	public void handleInput();

	public void renderView();

	public void updateModel();

	public void run();
}
