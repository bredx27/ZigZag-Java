/**
 * 
 */
package com.github.tntgamestv.console;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 27-05-2017
 */
public class Out {

	public static void renderEngine(String out){
		out("RenderEngine", out);
	}
	
	public static void gameEngine(String out){
		out("GameEngine", out);
	}
	
	public static void serverThread(String out){
		out("ServerThread", out);
	}
	
	public static void clientThread(String out){
		out("ClientThread", out);
	}
	
	public static void answerThread(String out){
		out("AnswerThread", out);
	}
	
	public static void clientServerManager(String out){
		out("ClientServerManager", out);
	}
	
	private static void out(String name, String out){
		System.out.println("[" + name + "] " + out);
	}
}
