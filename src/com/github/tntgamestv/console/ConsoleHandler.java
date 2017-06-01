/**
 * 
 */
package com.github.tntgamestv.console;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.github.tntgamestv.frame.Frame;
import com.github.tntgamestv.frame.GameFrame;
import com.github.tntgamestv.frame.MainFrame;
import com.github.tntgamestv.frame.MainMenuFrame;
import com.github.tntgamestv.frame.RenderObject;
import com.github.tntgamestv.frame.ShopFrame;

/**
 * @author TnTGamesTV Project: School8 Date: 24-05-2017
 */
public class ConsoleHandler {

	private static BufferedReader	input			= new BufferedReader(new InputStreamReader(System.in));

	/* For setting frame and camera */
	private static MainFrame		mainInstance	= null;
	private static RenderObject		worldInstance	= null;

	public static void init(MainFrame givenMainInstance, RenderObject givenWorldInstance) {
		mainInstance = givenMainInstance;
		worldInstance = givenWorldInstance;

		// Thread
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						if (input.ready()) {
							handleConsoleInput(input.readLine());
						}
					} catch (Exception e) {
						// Ignore
					}
				}
			}
		}).start();
	}

	public static void handleConsoleInput(String in) {
		if (in.startsWith("frame:")) {
			handleFrame(in);
		}
		
		if(in.startsWith("camera:")){
			handleCamera(in);
		}
		
		if(in.startsWith("game:")){
			handleGame(in);
		}
	}

	public static void handleGame(String in){
		if(in.contains("game:setrunning(true)")){
			Frame currentFrame = mainInstance.getCurrentFrame();
			
			if(currentFrame instanceof GameFrame){
				GameFrame gameFrame = (GameFrame) currentFrame;
				gameFrame.getGameSettings().setSetting("game.running", true);
				out("Current game running state was set to true");
			}
		}else if(in.contains("game:setrunning(false)")){
			Frame currentFrame = mainInstance.getCurrentFrame();
			
			if(currentFrame instanceof GameFrame){
				GameFrame gameFrame = (GameFrame) currentFrame;
				gameFrame.getGameSettings().setSetting("game.running", false);
				out("Current game running state was set to false");
			}
		}
	}
	
	public static void handleCamera(String in){
		if(in.contains("camera:x:angle(")){
			String content = "camera:x:angle(";
			String angle = in.substring(content.length(), content.length() + 2);

			int angleInt = Integer.parseInt(angle);
			
			
		}
	}
	
	public static void handleFrame(String in) {
		
		if (in.contains("frame:exit")) {
			out("Exiting game with error code 0");
			out("Good bye :)");
			System.exit(0);
		}

		if (in.contains("frame:shopFrame")) {
			out("Trying to set ShopFrame as new frame");
			ShopFrame shopFrame = new ShopFrame(mainInstance, worldInstance);
			mainInstance.setNewFrame(shopFrame);

			out("New frame is now ShopFrame");
		}

		if (in.contains("frame:mainMenuFrame")) {
			out("Trying to set MainMenuFrame as new frame");
			MainMenuFrame mainMenuFrame = new MainMenuFrame(mainInstance, worldInstance);
			mainInstance.setNewFrame(mainMenuFrame);

			out("New frame is now MainMenuFrame");
		}
	}

	public static void out(String out) {
		System.out.println("[Console] " + out);
	}
}
