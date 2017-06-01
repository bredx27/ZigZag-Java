/**
 * 
 */
package com.github.tntgamestv;

import java.awt.Toolkit;

/**
 * @author TnTGamesTV Project: School8 Date: 10-05-2017
 */
public class Constants {

	/* Frame settings */
	public static final int		FRAME_WIDTH						= (int) (Toolkit.getDefaultToolkit().getScreenSize()
			.getWidth());
	public static final int		FRAME_HEIGHT					= (int) (Toolkit.getDefaultToolkit().getScreenSize()
			.getHeight());

	/* Path settings */
	public static final int		PATH_OBJECT_SIZE				= 25;
	public static final int		PATH_OBJECT_MARGIN				= 25;

	public static final int		PATH_OBJECT_ZONE_BORDER_RIGHT	= FRAME_WIDTH - PATH_OBJECT_MARGIN;
	public static final int		PATH_OBJECT_ZONE_BORDER_LEFT	= PATH_OBJECT_MARGIN;

	public static final int		PATH_GENERATION_START			= 200;
	public static final int		PATH_GENERATION_END				= -500;

	/* Frame Correction */
	public static final double	FRAME_POSITION_CORRECTION		= FRAME_WIDTH % PATH_OBJECT_SIZE / 2;

	/* SHOP */

	/* Camera movement */
	public static final int		SHOP_START_BOX					= 1;
	public static final int		SHOP_MAX_BOX					= 8;
	public static final int		SHOP_BOX_Y_OFFSET				= 160;

	public static final int		SHOP_NORMAL_SPEED				= 1000;
	public static final int		SHOP_SLOW_SPEED					= 2500;

	public static final int		SHOP_BALL_SIZE					= 20;
	public static final int		SHOP_BALL_Y_OFFSET				= Constants.SHOP_BOX_Y_OFFSET + 130
			+ (Constants.SHOP_BALL_SIZE / 2);

	/* Selector */
	public static final int		SHOP_SELECTOR_Y_OFFSET			= SHOP_BALL_Y_OFFSET + SHOP_BALL_SIZE;

	/* EVENTS */
	public static final int		EVENT_QUEUE_MAX_SIZE			= 1000;
}
