/**
 * 
 */
package com.github.tntgamestv.data;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 17-05-2017
 */
public class DataManager {

	public static AudioInputStream currentSound = null;
	
	public static void playSound(String soundFile) {
		URL url = DataManager.class.getResource(""+ soundFile);
		System.out.println("URL: " + url.toString());
	    try {
//			currentSound = AudioSystem.getAudioInputStream(url);
			currentSound = AudioSystem.getAudioInputStream(url.openStream());
		    Clip clip = AudioSystem.getClip();
		    clip.open(currentSound);
		    clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
