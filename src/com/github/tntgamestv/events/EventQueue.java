/**
 * 
 */
package com.github.tntgamestv.events;

import java.util.Stack;

import com.github.tntgamestv.execeptions.EventQueueOverflowException;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 10-05-2017
 */
public class EventQueue {

	private Stack<Event> queue;
	private int maxSize;
	
	public EventQueue(int maxSize){
		this.queue = new Stack<Event>();
		this.maxSize = maxSize;
	}
	
	public Event getLastEvent(){
		if(queue.size() > 0){
			return this.queue.pop();
		}else{
			return null;
		}
	}
	
	public void addEvent(Event event){
		if(this.queue.size() < this.maxSize){
			this.queue.push(event);
		}else{
			throw new EventQueueOverflowException();
		}
	}
}
