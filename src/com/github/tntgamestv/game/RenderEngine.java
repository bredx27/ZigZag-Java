/**
 * 
 */
package com.github.tntgamestv.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.github.tntgamestv.Constants;
import com.github.tntgamestv.execeptions.RenderObjectAlreadyRegisteredException;
import com.github.tntgamestv.execeptions.RenderObjectIsNullException;
import com.github.tntgamestv.frame.GameFrame;
import com.github.tntgamestv.frame.MainFrame;
import com.github.tntgamestv.game.render.PathTile;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author TnTGamesTV Project: School8 Date: 23-05-2017
 */
public class RenderEngine {

	/* Host */
	private GameFrame		host;
	private MainFrame		masterHost;

	/* Objects for renderering */
	private List<PathTile>	pathTilez	= new ArrayList<>();
	private Group			pathTiles	= new Group();

	/* Thread & Runnable */
	private TimerTask		moveTask;
	private TimerTask		creatingTask;
	private Timer			timer;

	public RenderEngine(GameFrame host) {
		this.host = host;
		this.timer = new Timer();

		this.host.addChildren(pathTiles);
	}

	public void registerRenderingObject(Node object) {
		if (object != null) {
			if (!this.pathTiles.getChildren().contains(object)) {
				this.pathTiles.getChildren().add(object);
			} else {
				throw new RenderObjectAlreadyRegisteredException("This render object was already registered");
			}
		} else {
			throw new RenderObjectIsNullException();
		}
	}

	public Group getRenderingObjects() {
		return pathTiles;
	}

	public List<PathTile> getPathTiles(){
		return pathTilez;
	}
	
	public void loop() {
		this.moveTask = new TimerTask() {

			@Override
			public void run() {
				if (host.getGameSettings().getSetting("game.running") && !host.getGameSettings().getSetting("game.paused")) {
					for (PathTile pathTile : pathTilez) {
						Node pathTileObject = pathTile.getRenderObject();
						TranslateTransition ttr1 = new TranslateTransition(Duration.millis(1000), pathTileObject);
						ttr1.setByX(-25);
						ttr1.setByZ(-25);
						ttr1.setInterpolator(Interpolator.LINEAR);

						ttr1.play();

						pathTile.setNextX(pathTile.getNextX() - 25);
						pathTile.setNextZ(pathTile.getNextZ() - 25);
					}
					
					TranslateTransition ttr2 = new TranslateTransition(Duration.millis(1000), host.ball);
					
					if(host.left){
						ttr2.setByX(12.5);
						ttr2.setByZ(-12.5);
					}else{
						ttr2.setByX(-12.5);
						ttr2.setByZ(12.5);
					}
					ttr2.setInterpolator(Interpolator.LINEAR);
					ttr2.play();
				}

				if (!host.getGameSettings().getSetting("game.paused") && host.getGameSettings().getSetting("game.running")) {
					for (Node node : pathTiles.getChildren()) {
						if (node.getTranslateX() <= Constants.PATH_GENERATION_END) {
							host.addToRemove(node);
						}
					}
				}
			}
		};

		this.creatingTask = new TimerTask() {

			@Override
			public void run() {
				if (!host.getGameSettings().getSetting("game.paused") && host.getGameSettings().getSetting("game.running")) {
					PathTile oldTile = pathTilez.size() > 0 ? pathTilez.get(pathTilez.size() - 1) : null;
					PathTile newTile = host.getGameEngine().generateNewTile(oldTile);
					pathTilez.add(newTile);
					host.addToAdd(newTile.getRenderObject());
				}
			}
		};

		this.timer.scheduleAtFixedRate(moveTask, 2010, 1000);
		this.timer.scheduleAtFixedRate(creatingTask, 0, 500);
	}
}
