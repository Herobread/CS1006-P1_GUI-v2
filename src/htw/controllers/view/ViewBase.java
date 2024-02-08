package htw.controllers.view;

import htw.view.Renderer;

// template for views
public abstract class ViewBase {
	protected String name;
	protected Renderer renderer;

	public ViewBase(String name) {
		this.name = name;
	}

	public void init() {
		renderer = Renderer.getInstance();
		renderer.setDimensions(512, 512);
	}

	public void renderView() {
	};

	public void update() {
	};

	public void run() {
		System.out.println(name + " was not setup");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
}
