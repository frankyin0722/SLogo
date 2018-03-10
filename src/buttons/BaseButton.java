package buttons;

import javafx.scene.control.Button;

/**
 * 
 * @author elizabethshulman
 * This class contains basic, otherwise easily repeatable, core formatting code 
 * for the standard button style used throughout this IDE.
 */
public class BaseButton extends Button {


	/**
	 * Build an instance of BaseButton, using the
	 * @param titleText as the key visual text 
	 */
	public BaseButton(String titleText) {
		this.setText(titleText);
        this.setMaxWidth(Double.MAX_VALUE);
	}
}
