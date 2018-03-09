package view.menus;

import buttons.CanvasColorButton;
import buttons.HelpButton;
import interpreter.CommandTreeInterpreter;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

public class HelpMenu extends TitledPane {
	
	private CommandTreeInterpreter interpreter;
	
	public HelpMenu(CommandTreeInterpreter i) {
		interpreter = i;
		setupHelpMenu();
	}
	
	private void setupHelpMenu() {
		this.setText("Help");
		this.setExpanded(false);
		VBox buttonList = new VBox();
		buttonList.setSpacing(10);
		buttonList.getChildren().addAll(
				new HelpButton(),
				new CanvasColorButton(interpreter));
		this.setContent(buttonList);
	}
	
	
}