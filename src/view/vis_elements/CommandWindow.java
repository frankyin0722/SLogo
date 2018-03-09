package view.vis_elements;


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
		myTextArea.setPrefRowCount(7);
		this.getChildren().add(myTextArea);
	}
	
	public void setText(String text) {
		myTextArea.setText(text);
	}
	
	public void addText(String text) {
		myTextArea.appendText(text);
	}
	
	public String getText() {
		return myTextArea.getText();
	}

	public void clearText() {
		myTextArea.clear();
	}
}
