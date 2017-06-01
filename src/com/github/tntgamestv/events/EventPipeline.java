/**
 * 
 */
package com.github.tntgamestv.events;

import java.util.ArrayList;
import java.util.List;

import com.github.tntgamestv.execeptions.ListenerAlreadyRegisteredException;

/**
 * @author TnTGamesTV Project: School8 Date: 25-05-2017
 */
public class EventPipeline {

	private static List<Listener> registeredListeners = new ArrayList<>();

	public static void registerListener(Listener listener){
		if(!registeredListeners.contains(listener)){
			registeredListeners.add(listener);
		}else{
			throw new ListenerAlreadyRegisteredException("This listener is already registered!");
		}
	}
	
	public static Event handleEvent(Event event) {
		for (Listener listener : registeredListeners) {
			listener.handle(event);
		}

		return event;
	}
}
