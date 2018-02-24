package visual_elements;


//import parser.Parser;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import turtle.Turtle;

public class CommandWindow extends VBox {
	private TextArea myTextArea;
	public CommandWindow() {
		setupTextArea();
	}
	
	private void setupTextArea() {
		myTextArea = new TextArea();
		myTextArea.setPromptText("Enter commands");
		myTextArea.setPrefRowCount(5);
		this.getChildren().add(myTextArea);
	}
	
	public String getText() {
		return myTextArea.getText();
	}

	public void clearText() {
		myTextArea.clear();
	}
}
