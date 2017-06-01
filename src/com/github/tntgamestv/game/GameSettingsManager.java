/**
 * 
 */
package com.github.tntgamestv.game;

import java.util.HashMap;

import com.github.tntgamestv.execeptions.UnknownSettingException;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 27-05-2017
 */
public class GameSettingsManager {

	private HashMap<String, Boolean> settings;
	
	public GameSettingsManager(){
		this.settings = new HashMap<String, Boolean>();
	}
	
	public void setSetting(String name, boolean value){
		if(this.settings.containsKey(name)){
			this.settings.put(name, value);
		}else{
			throw new UnknownSettingException("This setting has not been initiated");
		}
	}
	
	public void init(){
		this.settings.put("game.running", false);
		this.settings.put("game.paused", false);
		this.settings.put("game.server-application", false);
	}
	
	public boolean getSetting(String name){
		if(this.settings.containsKey(name)){
			return this.settings.get(name);
		}else{
			throw new UnknownSettingException("This setting has not been initiated");
		}
	}
}
