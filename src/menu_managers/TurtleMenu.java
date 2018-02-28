package menu_managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
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
	
//	private void setupTurtleSelection() {
//		File f = new File("./images/");
//		for (File x : f.listFiles()) {
////			ImageView im = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(x));
//			try {
//				ImageView im = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(x.getCanonicalPath())));
//				this.getChildren().add(im);
////				Image image = new Image(getClass().getClassLoader().getResourceAsStream(x.getCanonicalPath()));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				System.out.print("Cannot find path");
//			}
//			
//			System.out.print(x);
//		}
//	}
	
	private void setupPane(ResourceBundle resources) {
		this.setText(resources.getString(TURTLE_MENU_KEY));
		this.setExpanded(false);
	}
	
	private void setupTurtleSelection() {
		myTurtleList = new VBox();
//		turtleList.setMaxWidth(100);
//		myTurtleButtons = new ArrayList<>();
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
