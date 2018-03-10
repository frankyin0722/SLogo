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
import slogo_team08.IConstants;
import turtle.Turtle;
import turtle.TurtleController;
/**
 * Menu that displays potential turtle images
 * User clicks on image to change active turtle icon to selected image
 * @author xlany, elizabethshulman
 *
 */
public class TurtleAvatarMenu extends TitledPane implements IConstants {
	private List<Image> myTurtleImages;
	private List<Button> myTurtleButtons;
	private TurtleController myTurtleController;
	/**
	 * Constructor takes in
	 * @param resources for language
	 * @param tc for active turtles
	 * Sets up all buttons and actions
	 */
	public TurtleAvatarMenu(ResourceBundle resources, TurtleController tc) {
		myTurtleController = tc;
		setupPane();
		initiateToInterp();
		setupAllButtons();
		initiateToInterp();
	}
	/**
	 * Method that initializes menu display
	 */
	private void setupPane() {
		this.setText("Change Turtle Image");
		this.setExpanded(false);
	}
	/**
	 * Method that initializes this class in Turtle Controller
	 * so users can choose image via code as well
	 */
	private void initiateToInterp() {
		myTurtleController.setImageMenu(myTurtleImages);
	}
	/**
	 * Method: for each image in image folder, make a click-able button
	 * that can change turtle image 
	 */
	private void setupAllButtons() {
		VBox turtleBox = new VBox();
		myTurtleImages = new ArrayList<>();
		myTurtleButtons = new ArrayList<>();
		File imageDir = new File(DEFAULT_IMAGE_FOLDER);
		for (File f: imageDir.listFiles()) {
			String name = f.getName();
			String lastThree = name.substring(name.length() - 3,name.length());
			if (lastThree.equals("png") || lastThree.equals("jpg")) {
				makeButton(DEFAULT_IMAGE_PATH+f.getName(), turtleBox);
			}
		}
		this.setContent(turtleBox);
	}
	/**
	 * Helper method to make button from 
	 * @param filepath, image path
	 * @param vbox, target display box 
	 */
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
	/**
	 * Change active turtles to selected image
	 * @param img
	 */
	private void changeTurtleImage(Image img) {
		for (Turtle t: myTurtleController.getActiveTurtles()) {
			t.setImage(img);
		}
	}
	
}
