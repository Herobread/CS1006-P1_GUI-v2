package game.pages.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import game.controllers.state.GameStateManager;
import game.controllers.view.ViewBase;
import game.controllers.view.ViewManager;
import game.model.CaveSystem;
import game.model.WorldGenerator;
import game.model.entities.Entity;
import game.model.entities.Player;
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

	private ActionListener generateButtonActionListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			WorldGenerator generator = new WorldGenerator(20, 20);
			CaveSystem caves = new CaveSystem(20, 20);

			generator.generateCellularAutomataCaves(caves);

			gameStateManager.setCaves(caves);

			renderView();
		}
	};

	// rendering
	@Override
	public void renderView() {
		renderer.clear();

		System.out.println("[Map] Rendering");

		CaveSystem caves = gameStateManager.getCaves();
		Player player = gameStateManager.getPlayer();

		int width = caves.getWidth();
		int height = caves.getHeight();

		final int START_POS_X = 82;
		final int START_POS_Y = 82;
		final int MAP_SIZE_PX = 348;
		final int TILE_SIZE = MAP_SIZE_PX / width;

		renderer.drawTexture("map-frame", 70, 70, 372, 372);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				renderer.drawTexture("map-" + caves.getConnectionsString(x, y), x * TILE_SIZE + START_POS_X,
						y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);

				if (player.getX() == x && player.getY() == y) {
					renderer.drawTexture(player.getTextureName(), x * TILE_SIZE + START_POS_X,
							y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);
				}

				List<Entity> entities = caves.getCaveEntities(x, y);

				if (entities == null) {
					continue;
				}

				if (entities.size() == 0) {
					continue;
				}

				for (Entity entity : entities) {
					renderer.drawTexture(entity.getName(), x * TILE_SIZE + START_POS_X,
							y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);
				}
			}
		}

		renderer.drawTexture("cross", 432, 10, 72, 72);
		renderer.drawClickAreaUnstable(432, 10, 72, 72, closeButtonActionListener);

		renderer.drawClickAreaUnstable(100, 450, 100, 100, generateButtonActionListener);

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
