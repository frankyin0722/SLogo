package slogo_team08;

import javafx.scene.paint.Color;

/**
 * 
 * @author elizabethshulman
 * @author xlany
 * 
 * This interface works to centralize constant information, so as to allow for easier, univeral modification of constant values.
 * Classes requiring constants may implement this interface, allowing their constants to be easily shared across the program.
 *
 */
public interface IConstants {

	/*
	 * Scene sizing constants
	 */
	public static final int INITIAL_SCENE_WIDTH = 1235;
	public static final int INITIAL_SCENE_HEIGHT = 710;
	public static final double EXTERNAL_CANVAS_WIDTH = 695;
	public static final double EXTERNAL_CANVAS_HEIGHT = 500;
	public static final double INTERNAL_CANVAS_WIDTH = 5000;
	public static final double INTERNAL_CANVAS_HEIGHT = 5000;
	
	/*
	 * Turtle image generation constants
	 */
	public static final String DEFAULT_TURTLE = "resources/images/cute_turtle.png";
	public static final String DEFAULT_IMAGE_PATH = "resources/images/";
	public static final String DEFAULT_IMAGE_FOLDER = "./src/resources/images/";
	public static final int TURTLE_WIDTH = 25;
	public static final int TURTLE_HEIGHT = 30;
	public static final int THUMBNAIL_WIDTH = 50;
	public static final int THUMBNAIL_HEIGHT = 50;

	/*
	 * Color-related constants
	 */
	public static final Color INITIAL_COLOR = Color.ALICEBLUE;
	public static final String DEFAULT_COLOR_PALETTE = "BasicRainbowPalette";
	public static final String COLOR_RESOURCE_PACKAGE = "resources.colors/";
	
	/*
	 * Language-related constants
	 */
	public static final String DEFAULT_LANGUAGE_FOLDER = "./src/resources/languages/";
	public static final String LANGUAGE_RESOURCE_PACKAGE = "resources.languages/";
	public static final String DEFAULT_LANGUAGE = "English";
	public static final String LANGUAGE_MENU_KEY = "LanguageMenu";

}
