package game.controllers.view;

// template for views
public abstract class ViewBase {
	protected String name;

	public ViewBase(String name) {
		this.name = name;
	}

	public void handleInput() {
	};

	public void renderView() {
	};

	public void update() {
	};

	public void run() {
		handleInput();
		renderView();
		update();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
}
