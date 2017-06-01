/**
 * 
 */
package com.github.tntgamestv.game.render;

import com.github.tntgamestv.Constants;

/**
 * @author TnTGamesTV Project: School8 Date: 28-05-2017
 */
public class LocationTranslator {

	public enum Location {
		A_A(8, -8), A_B(7, -7), A_C(6, -6), A_D(5, -5), A_E(4, -4), A_F(3, -3), A_G(2, -2), A_H(1, -1), B(0, 0), C_A(-1,
				1), C_B(-2, 2), C_C(-3, 3), C_D(-4, 4), C_E(-5, 5), C_F(-6, 6), C_G(-7, 7), C_H(-8, 8);

		int x;
		int z;

		Location(int xPos, int zPos) {
			this.x = Constants.PATH_GENERATION_START + (xPos * Constants.PATH_OBJECT_SIZE);
			this.z = Constants.PATH_GENERATION_START + (zPos * Constants.PATH_OBJECT_SIZE);
		}

		public int getX() {
			return this.x;
		}
		
		public int getZ() {
			return this.z;
		}
	}

	public Location[] getUseableLocations(Location oldLocation){
		return null;
	}
}
