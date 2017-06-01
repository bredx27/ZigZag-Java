/**
 * 
 */
package com.github.tntgamestv.game;

import com.github.tntgamestv.Constants;
import com.github.tntgamestv.events.EventQueue;
import com.github.tntgamestv.events.EventThread;
import com.github.tntgamestv.game.render.PathTile;

/**
 * @author TnTGamesTV Project: School8 Date: 23-05-2017
 */
public class GameEngine {

	private EventQueue	eventQueue;
	private EventThread	eventThread;

	public GameEngine() {
		this.eventQueue = new EventQueue(Constants.EVENT_QUEUE_MAX_SIZE);
		this.eventThread = new EventThread(eventQueue);

		// Starting thread(s)
		this.eventThread.start();
	}

	public PathTile generateNewTile(PathTile oldTile) {
		int rndm = (int) (Math.random() * 2);
		//
		int x = Constants.PATH_GENERATION_START;
		int z = Constants.PATH_GENERATION_START;
		
		if(!(oldTile == null)){
			if(oldTile.getNextX() < 200){
				//Top
				x = oldTile.getNextX() + Constants.PATH_OBJECT_SIZE;
				z = oldTile.getNextZ();
			}else if(oldTile.getNextZ() < 200){
				//Right
				x = oldTile.getNextX();
				z = oldTile.getNextZ() + Constants.PATH_OBJECT_SIZE;
			}else{
				if (rndm == 0) {
					//Right
					x = oldTile.getNextX();
					z = oldTile.getNextZ() + Constants.PATH_OBJECT_SIZE;
				} else {
					//Top
					x = oldTile.getNextX() + Constants.PATH_OBJECT_SIZE;
					z = oldTile.getNextZ();
				}
			}
		}

		PathTile newTile = new PathTile(x, z);

		return newTile;
	}
}
