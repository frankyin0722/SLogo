package buttons;

import java.awt.Button;

@SuppressWarnings("serial")
public abstract class CustomButton extends Button {
	public CustomButton() {
		setupButtonName();
	}
	
	protected abstract void setupButtonName();
	
	protected void setText(String name) {
		this.setText(name);
	}
}
