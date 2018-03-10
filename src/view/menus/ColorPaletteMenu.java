package view.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import slogo_team08.IConstants;
/**
 * Menu to handle default color and color changes
 * Default colors read from properties files
 * Users can thus reference colors directly in code
 * @author elizabethshulman
 *
 */
public class ColorPaletteMenu extends TitledPane implements IConstants {

	private ResourceBundle myResources;
	private HashMap<Integer, Color> indexToColor;
	private CommandTreeInterpreter interpreter;
	/**
	 * Constructor takes
	 * @param i, interpreter
	 * to later initialize this menu in interpreter
	 */
	public ColorPaletteMenu(CommandTreeInterpreter i) {
		this(i, DEFAULT_COLOR_PALETTE);
	}
	/**
	 * Constructor takes
	 * @param i, interpreter
	 * @param colorPalette, default color palette
	 */
	public ColorPaletteMenu(CommandTreeInterpreter i, String colorPalette) {
		interpreter = i;
		initializeFormat(colorPalette);
	}
	/**
	 * Method visualizes default colors
	 * Initialized menu
	 * @param colorPalette
	 */
	private void initializeFormat(String colorPalette) {
		this.setText("Default Color Options");
		this.setExpanded(false);
		try {
			myResources = ResourceBundle.getBundle(COLOR_RESOURCE_PACKAGE + colorPalette);
			buildMenu();
		} catch (MissingResourceException e) {
			new Alert(AlertType.ERROR, "Invalid color properties file: Check resource location and name", ButtonType.OK).showAndWait();
		}
	}
	/**
	 * Helper function initializes menu that maps default indicies to 
	 * default colors. Colors are visualized.
	 */
	private void buildMenu() {
		ArrayList<HBox> myColorOptions = new ArrayList<>();
		indexToColor = new HashMap<Integer, Color>();
		List<String> myKeys = Collections.list(myResources.getKeys());
		
		for(int i=0; i<myKeys.size(); i++) {
			try {
				indexToColor.put(i, Color.valueOf(myResources.getString(myKeys.get(i))));
				myColorOptions.add(buildHbox(myKeys.get(i), i));
			} catch (IllegalArgumentException e) {
				new Alert(AlertType.INFORMATION, "Illegal Paint Type on " + myKeys.get(i), ButtonType.OK).showAndWait();
			}
		}
		interpreter.getTurtleController().setPalette(indexToColor);
		ListView<HBox> colorDisplay = new ListView<>();
		colorDisplay.setItems(FXCollections.observableArrayList(myColorOptions));
		this.setContent(colorDisplay);
	}
	/**
	 * Helper function aligns chosen
	 * @param key, stated color name
	 * @param index, index of color
	 * @return HBox that visualizes color shade and index
	 * Will be called for every color
	 * Main purpose for graphic organization
	 */
	private HBox buildHbox(String key, int index) {
		HBox colorOption = new HBox();
		Rectangle colorSample = new Rectangle(15,15);
		colorSample.setFill(Color.valueOf(myResources.getString(key)));
		colorOption.getChildren().addAll(colorSample, 
				new Text("   " + key + " (" + Integer.toString(index) + ")"));
		return colorOption;
	}
	/**
	 * Method to
	 * @return map of current default colors by each index
	 */
	public Map<Integer,Color> getIndexToColorMap() {
		return indexToColor;
	}
}
