package visual_elements;


//import parser.Parser;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

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
	
//	private void inputToParser() {
//		new Parser(myTextArea.getParagraphs());
//	}
	
}
