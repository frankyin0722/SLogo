package visual_elements;


import parser.Parser;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class TextFieldInput extends VBox {
	private TextField myTextField;
	private Button myRunButton;
	private Button myClearButton;
	public TextFieldInput() {
		setupTextField();
		setupTextFieldButtons();
		setButtonEvents();
	}
	
	private void setupTextField() {
		myTextField = new TextField();
		myTextField.setPromptText("Enter commands!");
//		myTextField.setMaxWidth(500);
//		this.setAlignment(Pos.TOP_LEFT);
		this.getChildren().add(myTextField);

	}
	
	private void setupTextFieldButtons() {
		TextFieldButtons buttons = new TextFieldButtons();
		myRunButton = buttons.getRunButton();
		myClearButton = buttons.getClearButton();
		this.getChildren().add(buttons);
	}
	
	private void setButtonEvents() {
		myRunButton.setOnAction(e -> inputToParser());
		myClearButton.setOnAction(e -> myTextField.clear());
	}
	
	private void inputToParser() {
		new Parser(myTextField.getText());
	}
	/**
	 * design q: how to put these things in controller?
	 */
	private void handleKeyInput(KeyCode code) {
		if (code.equals(KeyCode.ENTER)) {
			inputToParser();
		}
	}
	
}
