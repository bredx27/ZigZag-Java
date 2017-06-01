/**
 * 
 */
package com.github.tntgamestv.game.render;

import com.github.tntgamestv.Constants;

import javafx.scene.shape.Box;
import javafx.scene.transform.Translate;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

/**
 * @author TnTGamesTV Project: School8 Date: 23-05-2017
 */
public class PathTile {

	// Represents the PathTile on screen
	private Box	renderObject;

	// Locations on screen (same as Box#getTranslateX or Box#getTranslateY
	private int	x;
	private int	z;

	/*the current translation finish location*/
	private int nextX;
	private int nextZ;

	public PathTile(int x, int z) {
		this.x = x;
		this.z = z;

		this.nextX = x;
		this.nextZ = z;
		
		Box renderObject = new Box(Constants.PATH_OBJECT_SIZE, Constants.PATH_OBJECT_SIZE, Constants.PATH_OBJECT_SIZE);
		
		PhongMaterial material = new PhongMaterial();
		material.setDiffuseColor(Color.LIGHTSKYBLUE);
		material.setSpecularColor(Color.SKYBLUE);
		
		renderObject.getTransforms().add(new Translate(x, 0, z));
		renderObject.setMaterial(material);
		
		this.renderObject = renderObject;
	}

	/**
	 * @return the renderObject
	 */
	public Box getRenderObject() {
		return renderObject;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return this.z;
	}

	/**
	 * @return the nextX
	 */
	public int getNextX() {
		return nextX;
	}

	/**
	 * @param nextX the nextX to set
	 */
	public void setNextX(int nextX) {
		this.nextX = nextX;
	}

	/**
	 * @return the nextZ
	 */
	public int getNextZ() {
		return nextZ;
	}

	/**
	 * @param nextZ the nextZ to set
	 */
	public void setNextZ(int nextZ) {
		this.nextZ = nextZ;
	}
}
