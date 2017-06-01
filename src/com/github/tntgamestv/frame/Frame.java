/**
 * 
 */
package com.github.tntgamestv.frame;

import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author TnTGamesTV
 * Project: School8
 * Date: 16-05-2017
 */
public abstract class Frame {

	protected MainFrame mainFrame;
	protected RenderObject world;
	protected Group children;
	public boolean isCameraRotationBlocked = false;
	
	public Frame(MainFrame mainFrame, RenderObject world){
		this.mainFrame = mainFrame;
		this.world = world;
		this.children = new Group();
	}
	
	public Frame(MainFrame mainFrame, Frame lastFrame){
		this.mainFrame = mainFrame;
		this.world = lastFrame.world;
		this.children = new Group();
	}
	
	public abstract void create();
	
	public void hide() {}
	
	public void loop() {}
	
	protected void setNewFrame(Frame frame){
		this.mainFrame.setNewFrame(frame);
	}
	
	public void show() {}
	
	protected void handleKeyboard(KeyEvent e) {}
	
	protected void handleMouse(MouseEvent e) {}
}
