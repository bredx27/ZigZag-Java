/**
 * 
 */
package com.github.tntgamestv.events;

/**
 * @author TnTGamesTV Project: School8 Date: 25-05-2017
 */
public class EventThread extends Thread implements Runnable {

	private EventQueue eventQueue;

	public EventThread(EventQueue eventQueue) {
		this.eventQueue = eventQueue;
	}

	@Override
	public void run() {
		Event lastEvent = this.eventQueue.getLastEvent();

		if (lastEvent != null) {
			lastEvent = EventPipeline.handleEvent(lastEvent);

			EventFinalizer.finalizeEvent(lastEvent);
		}
	}
}
