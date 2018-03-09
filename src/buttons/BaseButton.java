package buttons;

import javafx.scene.control.Button;

public class BaseButton extends Button {

	public BaseButton(String titleText) {
		this.setText(titleText);
        this.setMaxWidth(Double.MAX_VALUE);
	}
}
