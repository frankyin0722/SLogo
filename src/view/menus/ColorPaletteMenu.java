package view.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
import view.IVisualConstants;

public class ColorPaletteMenu extends TitledPane implements IVisualConstants {

	private ResourceBundle myResources;
	private HashMap<Integer, Paint> indexToColor;
	
	public ColorPaletteMenu() {
		this(DEFAULT_COLOR_PALETTE);
	}
		
	public ColorPaletteMenu(String colorPalette) {
		initializeFormat(colorPalette);
	}
	
	private void initializeFormat(String colorPalette) {
		try {
			myResources = ResourceBundle.getBundle(COLOR_RESOURCE_PACKAGE + colorPalette);
			buildMenu();
		} catch (MissingResourceException e) {
			new Alert(AlertType.ERROR, "Invalid color properties file: Check resource location and name", ButtonType.OK).showAndWait();
		}
		this.setText("Default Color Options");
		this.setExpanded(false);
	}
	
	private void buildMenu() {
		ArrayList<HBox> myColorOptions = new ArrayList<HBox>();
		indexToColor = new HashMap<Integer, Paint>();
		List<String> myKeys = Collections.list(myResources.getKeys());
		
		for(int i=0; i<myKeys.size(); i++) {
			try {
				indexToColor.put(i, Color.valueOf(myResources.getString(myKeys.get(i))));
				myColorOptions.add(buildHbox(myKeys.get(i), i));
			} catch (IllegalArgumentException e) {
				new Alert(AlertType.INFORMATION, "Illegal Paint Type on " + myKeys.get(i), ButtonType.OK).showAndWait();
			}
		}
		ListView<HBox> colorDisplay = new ListView<HBox>();
		colorDisplay.setItems(FXCollections.observableArrayList(myColorOptions));
		this.setContent(colorDisplay);
	}
	
	private HBox buildHbox(String key, int index) {
		HBox colorOption = new HBox();
		Rectangle colorSample = new Rectangle(15,15);
		colorSample.setFill(Color.valueOf(myResources.getString(key)));
		colorOption.getChildren().addAll(colorSample, 
				new Text("   " + key + " (" + Integer.toString(index) + ")"));
		return colorOption;
	}
	
	public Map<Integer,Paint> getIndexToColorMap() {
		return indexToColor;
	}
}
