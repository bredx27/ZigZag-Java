/**
 * 
 */
package com.github.tntgamestv.execeptions;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 25-05-2017
 */
public class ListenerAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 7788465564120236943L;
	
	public ListenerAlreadyRegisteredException(String arg0) {
		super(arg0);
	}
}
