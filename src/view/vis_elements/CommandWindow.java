package view.vis_elements;

import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
/**
 * Displayed by ControlTextInput
 * Class focused around text area for command input
 * @author xlany, elizabethshulman
 *
 */
public class CommandWindow extends VBox {
	private TextArea myTextArea;
	/**
	 * Constructor initializes text input area
	 */
	public CommandWindow() {
		setupTextArea();
	}
	/**
	 * Method initializes text area configurations
	 */
	private void setupTextArea() {
		myTextArea = new TextArea();
		myTextArea.setPromptText("Enter commands");
		myTextArea.setPrefRowCount(7);
		this.getChildren().add(myTextArea);
	}
	/**
	 * Method sets text area by
	 * @param text
	 */
	public void setText(String text) {
		myTextArea.setText(text);
	}
	/**
	 * Method adds @param text to text area
	 */
	public void addText(String text) {
		myTextArea.appendText(text);
	}
	/**
	 * 
	 * Method @return all text in text area
	 */
	public String getText() {
		return myTextArea.getText();
	}
	/**
	 * Method clears text area
	 */
	public void clearText() {
		myTextArea.clear();
	}
}
