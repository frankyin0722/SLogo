package visual_elements;




import java.util.ResourceBundle;

import interpreter.CommandTreeInterpreter;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import turtle.Turtle;

/**
 * @author elizabethshulman
 * @author xlany
 * @param scene
 * 
 * Initializes and arranges each of the elements within the scene
 * 
 */
public class Visualization {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources.languages/";
	public static final int INITIAL_SCENE_WIDTH = 1000;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	
	private Scene myScene;
	private BorderPane myPane;
	private ResourceBundle myResources;
	private ScrollingDrawingWindow myScrollingDrawingWindow;
	private CommandTreeInterpreter interpreter;
	private ControlTextInput myControlTextInput;
	private ControlPanelRight myControlPanelRight;
	private ControlPanelLeft myControlPanelLeft;
	private Turtle myDefaultTurtle;
	
	public Visualization() {
		myPane = new BorderPane();
		initializeAll();
		initializeLayout();
	}
	
	private void initializeAll() {
		myScrollingDrawingWindow = new ScrollingDrawingWindow();
		myDefaultTurtle = myScrollingDrawingWindow.getDefaultTurtle();
		myControlPanelRight = new ControlPanelRight();
		changeLanguage();
		
		interpreter = new CommandTreeInterpreter(myDefaultTurtle);
		myControlTextInput = new ControlTextInput(interpreter, this);
		
//		myControlTextInput = new ControlTextInput(myDefaultTurtle, this);
		myControlPanelLeft = new ControlPanelLeft(myDefaultTurtle, myResources);
	}

	private void initializeLayout() {		
		myPane.setPadding(new Insets(20,20,20,20));
		myPane.setTop(new InfoTop());
		myPane.setCenter(myScrollingDrawingWindow);
		myPane.setBottom(myControlTextInput);
		myPane.setRight(myControlPanelRight);
		myPane.setLeft(myControlPanelLeft);
		
		myScene = new Scene(myPane,INITIAL_SCENE_WIDTH,INITIAL_SCENE_HEIGHT);
	}
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void setupLanguageObservable() {
//        List<ResourceBundle> list = new ArrayList<>();
//        list.add(myResources);
//        ObservableList<ResourceBundle> observableList = FXCollections.observableList(list);
//        observableList.addListener(new ListChangeListener() {
//            @Override
//            public void onChanged(ListChangeListener.Change change) {
//            		System.out.print("changed languauge in vis!");
//                myResources = observableList.get(0);
//            }
//        });
//	}
	
	
	public void changeLanguage() {
		myResources = myControlPanelRight.getLanguage();
	}
	
	public ResourceBundle getLanguage() {
		changeLanguage();
		return myResources;
	}
	
	public void updateLanguage() {
		myResources = myControlPanelRight.getLanguage();
	}
	
	public Scene getScene() {
		return myScene;
	}
	
}
