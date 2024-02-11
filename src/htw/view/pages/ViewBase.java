package htw.view.pages;

import htw.view.renderer.Renderer;

/**
 * A template for views in the application.
 */
public abstract class ViewBase {
	protected String name;
	protected Renderer renderer;

	/**
	 * Constructs a new view with the specified name.
	 *
	 * @param name The name of the view.
	 */
	public ViewBase(String name) {
		this.name = name;
	}

	/**
	 * Initializes the view.
	 */
	public void init() {
		renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);
	}

	/**
	 * Renders the view.
	 */
	public void renderView() {
	}

	/**
	 * Updates the view.
	 */
	public void update() {
	}

	/**
	 * Runs the view.
	 */
	public void run() {
		System.out.println(name + " was not set up");
	}

	/**
	 * Retrieves the name of the view.
	 *
	 * @return The name of the view.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the view.
	 *
	 * @param name The new name of the view.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
