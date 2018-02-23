package visual_elements;


import parser.Parser;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class TextFieldInput extends VBox {
	private TextField myTextField;
	private TextArea myTextArea;
	private Button myRunButton;
	private Button myClearButton;
	public TextFieldInput() {
//		setupTextField();
		setupTextArea();
		setupTextFieldButtons();
		setButtonEvents();
	}
	
	private void setupTextField() {
		myTextField = new TextField();
		myTextField.setPromptText("Enter commands!");
		myTextField.setStyle("-fx-padding: 10;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-insets: 5;" + 
                "-fx-border-color: deepskyblue;");
//		myTextField.setMaxWidth(500);
//		this.setAlignment(Pos.TOP_LEFT);
		this.getChildren().add(myTextField);

	}
	
	private void setupTextArea() {
		myTextArea = new TextArea();
		myTextArea.setPromptText("Enter commands");
		myTextArea.setPrefRowCount(5);
		myTextArea.setStyle(
//				"-fx-padding: 5;" + 
                "-fx-border-style: solid inside;" + 
                "-fx-border-width: 5;" +
                "-fx-border-insets: 5;" + 
                "-fx-border-color: deepskyblue;");
		this.getChildren().add(myTextArea);
		
	}
	
	private void setupTextFieldButtons() {
		TextFieldButtons buttons = new TextFieldButtons();
		myRunButton = buttons.getRunButton();
		myClearButton = buttons.getClearButton();
		this.getChildren().add(buttons);
	}
	
	private void setButtonEvents() {
		myRunButton.setOnAction(e -> inputToParser());
		myClearButton.setOnAction(e -> myTextArea.clear());
	}
	
	private void inputToParser() {
//		new Parser(myTextField.getText());
//		new Parser(myTextArea.getParagraphs());
		System.out.print(myTextArea.getParagraphs());
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
