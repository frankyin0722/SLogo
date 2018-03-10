package view.menus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import turtle.Turtle;
import turtle.TurtleController;

public class TurtleAvatarMenu extends TitledPane {
	
	public static final int THUMBNAIL_WIDTH = 50;
	public static final int THUMBNAIL_HEIGHT = 50;
	public static final String TURTLE_MENU_KEY = "TurtleMenu";
	public static final String DEFAULT_IMAGE_PATH = "resources/images/";
	public static final String DEFAULT_IMAGE_FOLDER = "./src/resources/images/";
	private List<Image> myTurtleImages;
	private List<Button> myTurtleButtons;
	private VBox myTurtleBox;
	private TurtleController myTurtleController;
	
	public TurtleAvatarMenu(ResourceBundle resources, TurtleController tc) {
		myTurtleController = tc;
		setupPane(resources);
		initiateToInterp();
		setupAllButtons();
		initiateToInterp();
	}
	
	private void setupPane(ResourceBundle resources) {
		this.setText(resources.getString(TURTLE_MENU_KEY));
		this.setExpanded(false);
	}
	
//	private void setupTurtleSelection() {
//		myTurtleBox = new VBox();
//		myTurtleImages = new ArrayList<>();
//		myTurtleButtons = new ArrayList<>();
//		makeButton("resources/images/franklin.jpg", myTurtleBox);
//		makeButton("resources/images/cute_turtle.png", myTurtleBox);
//		this.setContent(myTurtleBox);
//	}
	
	private void initiateToInterp() {
		myTurtleController.setImageMenu(myTurtleImages);
	}
	
	private void setupAllButtons() {
		myTurtleBox = new VBox();
		myTurtleImages = new ArrayList<>();
		myTurtleButtons = new ArrayList<>();
		File imageDir = new File(DEFAULT_IMAGE_FOLDER);
		for (File f: imageDir.listFiles()) {
			String name = f.getName();
			if (name.substring(name.length() - 3,name.length()).equals("png") | 
					name.substring(name.length() - 3,name.length()).equals("jpg")) {
				makeButton(DEFAULT_IMAGE_PATH+f.getName(), myTurtleBox);
			}
		}
		this.setContent(myTurtleBox);

	}
	
	private void makeButton(String filepath, VBox vbox) {
		Button button = new Button();
		Image img = new Image(getClass().getClassLoader().getResourceAsStream(filepath));
		ImageView imgview = new ImageView(img);
		imgview.setFitWidth(THUMBNAIL_WIDTH);
		imgview.setFitHeight(THUMBNAIL_HEIGHT);
		button.setGraphic(imgview);
		button.setOnAction(e -> changeTurtleImage(img));
		myTurtleButtons.add(button);
		vbox.getChildren().add(button);
	}
	
	private void changeTurtleImage(Image img) {
		for (Turtle t: myTurtleController.getActiveTurtles()) {
			t.setImage(img);
		}
	}
	
	public List<Button> getTurtleButtons() {
		return myTurtleButtons;
	}
	
	
}
