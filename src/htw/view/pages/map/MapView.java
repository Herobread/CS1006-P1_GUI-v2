package htw.view.pages.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import htw.controllers.game.GameStateManager;
import htw.controllers.view.ViewBase;
import htw.controllers.view.ViewManager;
import htw.model.caves.CaveSystem;
import htw.model.caves.WorldGenerator;
import htw.model.entities.Entity;
import htw.model.entities.Player;
import htw.model.map.ExploredMap;
import htw.model.map.ExploredMap.TileState;
import htw.view.Renderer;

public class MapView extends ViewBase {
	private GameStateManager gameStateManager = GameStateManager.getInstance();
	private ViewManager viewManager = ViewManager.getInstance();
	private Renderer renderer = Renderer.getInstance();
	private boolean SHOW_ENTITIES = false;

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

		ExploredMap exploredMap = gameStateManager.getExploredMap();
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
				if (exploredMap.getTile(x, y) == null) {
					continue;
				}

				// map tile
				renderer.drawTexture("map-" + caves.getConnectionsString(x, y), x * TILE_SIZE + START_POS_X,
						y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);

				// mark on map tile
				if (exploredMap.getTile(x, y) == TileState.HAZARD) {
					renderer.drawTexture("map-danger", x * TILE_SIZE + START_POS_X,
							y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);
				}

				if (player.getX() == x && player.getY() == y) {
					renderer.drawTexture("map-player", x * TILE_SIZE + START_POS_X,
							y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);
				}

				if (SHOW_ENTITIES) {

					List<Entity> entities = caves.getCaveEntities(x, y);

					if (entities == null) {
						continue;
					}

					if (entities.size() == 0) {
						continue;
					}

					for (Entity entity : entities) {
						renderer.drawTexture(entity.getTextureName(), x * TILE_SIZE + START_POS_X,
								y * TILE_SIZE + START_POS_Y, TILE_SIZE, TILE_SIZE);
					}
				}

			}
		}

		renderer.drawTexture("cross", 8, 8, 72, 72);
		renderer.drawClickAreaUnstable(8, 8, 72, 72, closeButtonActionListener);

		renderer.drawClickAreaUnstable(100, 450, 100, 100, generateButtonActionListener);

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
