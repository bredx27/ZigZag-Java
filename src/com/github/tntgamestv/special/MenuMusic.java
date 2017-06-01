/**
 * 
 */
package com.github.tntgamestv.special;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author TnTGamesTV Project: School8 Date: 17-05-2017
 */
public class MenuMusic {

	public static void playRandomMusic() {
		int selectedMusic = (int) (Math.random() * 4);

		String path = "";

		switch (selectedMusic) {
		default:
			path = "menu3.ogg";
			break;
		case 0:
			path = "menu1.ogg";
			break;
		case 1:
			path = "menu2.ogg";
			break;
		case 2:
			path = "menu3.ogg";
			break;
		case 3:
			path = "menu4.ogg";
			break;
		}

		path = "menu3.mp3";

		try {
			URL testURL = new URL("file:///" + MenuMusic.class.getResource(path).toExternalForm().replace("file:/", ""));
			System.out.println("URL: " + testURL.toExternalForm());
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
		
		URL url = null;
		try {
			url = new URL("file:///" + MenuMusic.class.getResource(path).toExternalForm().replace("file:/", ""));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		if (url != null) {
			MediaPlayer media = new MediaPlayer(url);

			try {
				media.playSound();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
}
