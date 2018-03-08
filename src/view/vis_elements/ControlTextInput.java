package view.vis_elements;

import java.util.ResourceBundle;
import buttons.ClearButton;
import buttons.LoadFileButton;
import buttons.ResetButton;
import buttons.RunButton;
import buttons.SaveFileButton;
import interpreter.CommandTreeInterpreter;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import parser.Parser;

public class ControlTextInput extends HBox {
	private final BooleanProperty shiftPressed = new SimpleBooleanProperty(false);
	private final BooleanProperty enterPressed = new SimpleBooleanProperty(false);
	private final BooleanBinding shiftAndEnterPressed = shiftPressed.and(enterPressed);

	private CommandWindow myCommandWindow;
	private RunButton myRunButton;
	private ClearButton myClearButton;
	private ResetButton myResetButton;
	private LoadFileButton myLoadFileButton;
	private SaveFileButton mySaveFileButton;
	private CommandTreeInterpreter interpreter;
	private ResourceBundle myResources;
	private Visualization myVisualization;
	
	public ControlTextInput(CommandTreeInterpreter i, Visualization visualization) {
		myVisualization = visualization;
		myResources = myVisualization.getLanguage();
		interpreter = i;
		myCommandWindow = new CommandWindow();
		this.getChildren().addAll(
				myCommandWindow,
				buttonBox());
		setButtonAction();
		this.setAlignment(Pos.BOTTOM_CENTER);
		//setupKeyInput();
	}

	private VBox buttonBox() {
		VBox buttons = new VBox();
		buttons.setFillWidth(true);
		myRunButton = new RunButton();
		myClearButton = new ClearButton();
		myLoadFileButton = new LoadFileButton();
		mySaveFileButton = new SaveFileButton();
		myResetButton = new ResetButton();
		addButton(buttons, myRunButton);
		addButton(buttons, myClearButton);
		addButton(buttons, myLoadFileButton);
		addButton(buttons, mySaveFileButton);
		addButton(buttons, myResetButton);
		return buttons;
	}
	
    private void addButton(VBox buttonBox, Button button) {
        button.setMaxWidth(Double.MAX_VALUE);
        buttonBox.getChildren().add(button);
    }
    
    private void setButtonAction() {
    		myRunButton.setOnAction(e -> inputToParser());
    		myClearButton.setOnAction(e -> resetCommandWindow());
    		myLoadFileButton.setOnAction(e -> myLoadFileButton.loadFile(myCommandWindow));
    		mySaveFileButton.setOnAction(e -> mySaveFileButton.save(interpreter));
    		myResetButton.setOnAction(e -> resetTurtle());
    }
    
	private void inputToParser() {
		getLanguage();
		Parser parser = new Parser(interpreter);
		parser.generateCommandTree(myCommandWindow.getText(), myResources);
		interpreter.addToHistory(myCommandWindow.getText());
		if(myCommandWindow.getText().substring(0, 2).equals("to")) {
//			interpreter.addToActiveUDC(myCommandWindow.getText().split("to ")[1].split(":")[0],
//					myCommandWindow.getText().split("to ")[1].split(":")[1]);
//			interpreter.addToActiveUDC(myCommandWindow.getText().split("to ")[1].split(" ")[0],"");

		}
		resetCommandWindow();
	}
	
	private void getLanguage() {
		myResources = myVisualization.getLanguage();

	}
		
	private void resetCommandWindow() {
		myCommandWindow.clearText();
	}
	
	private void resetTurtle() {

		interpreter.getTurtleController().resetTurtles();
	}

//	private void setupKeyInput() {
//		// How to respond to both keys pressed together:
//		PauseTransition pause = new PauseTransition(Duration.seconds(0.15));
//		shiftAndEnterPressed.addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> obs, Boolean werePressed, Boolean arePressed) {
//		        
//		        pause.setOnFinished(e -> inputToParser());
//		        pause.playFromStart();
//			}
//		});
//
////		// Wire up properties to key events:
////		this.setOnKeyPressed(new EventHandler<KeyEvent>() {
////		    @Override
////		    public void handle(KeyEvent ke) {
////		        if (ke.getCode() == KeyCode.SHIFT) {
////		        		System.out.print("shift_on");
////		            shiftPressed.set(true);
////		        } else if (ke.getCode() == KeyCode.ENTER) {
////	        			System.out.print("enter_on");
////		            enterPressed.set(true);
////		        }
////		    }
////		});
//
//		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
//		    @Override
//		    public void handle(KeyEvent ke) {
//		        if (ke.getCode() == KeyCode.SHIFT) {
//	        		System.out.print("shift_off");
//		        		shiftPressed.set(true);
//		        } else if (ke.getCode() == KeyCode.ENTER) {
//	        		System.out.print("enter_off");
//		        		enterPressed.set(true);
//		        }
//		    }
//		});
//	}


}