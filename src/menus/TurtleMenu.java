package menus;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import turtle.Turtle;

public class TurtleMenu extends TitledPane {
	
	public static final int THUMBNAIL_WIDTH = 50;
	public static final int THUMBNAIL_HEIGHT = 50;
	public static final String TURTLE_MENU_KEY = "TurtleMenu";
	private List<Button> myTurtleButtons;
	private VBox myTurtleList;
	private Turtle myTurtle;
	
	public TurtleMenu(ResourceBundle resources, Turtle turtle) {
		myTurtle = turtle;
		setupPane(resources);
		setupTurtleSelection();
	}
	
	private void setupPane(ResourceBundle resources) {
		this.setText(resources.getString(TURTLE_MENU_KEY));
		this.setExpanded(false);
	}
	
	private void setupTurtleSelection() {
		myTurtleList = new VBox();
		myTurtleButtons = new ArrayList<>();
		makeButton("franklin.jpg", myTurtleList);
		makeButton("cute_turtle.png", myTurtleList);
		this.setContent(myTurtleList);

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
		myTurtle.setImage(img);
	}
	
	public List<Button> getTurtleButtons() {
		return myTurtleButtons;
	}
	
	public VBox getTurtleList() {
		return myTurtleList;
	}
	
}
