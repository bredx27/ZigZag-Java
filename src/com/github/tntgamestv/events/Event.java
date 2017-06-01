/**
 * 
 */
package com.github.tntgamestv.events;

/**
 * @author TnTGamesTV Project: School8 Date: 10-05-2017
 */
public class Event {

	public enum Group{
		SERVER,
		CLIENT,
		GAMEENGINE,
		RENDERENGINE,
		SHOP,
		UNKNOWN;
	}
	
	private boolean cancel = false;
	private String eventName;
	
	private Event.Group eventGroup;
	
	public Event(String eventName){
		this.eventName = eventName;
		this.eventGroup = Event.Group.UNKNOWN;
	}
	
	public Event(String eventName, Event.Group eventGroup){
		this.eventName = eventName;
		this.eventGroup = eventGroup;
	}

	/**
	 * @return the cancel
	 */
	public boolean isCancel() {
		return cancel;
	}

	/**
	 * @param cancel the cancel to set
	 */
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the eventGroup
	 */
	public Event.Group getEventGroup() {
		return eventGroup;
	}

	/**
	 * @param eventGroup the eventGroup to set
	 */
	public void setEventGroup(Event.Group eventGroup) {
		this.eventGroup = eventGroup;
	}
}
