package view.menus;

import buttons.CanvasColorButton;
import buttons.HelpButton;
import buttons.LanguageChangeButton;
import interpreter.CommandTreeInterpreter;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import view.vis_elements.Visualization;

public class HelpMenu extends TitledPane {
	
	private CommandTreeInterpreter interpreter;
	private Visualization vis;
	
	public HelpMenu(CommandTreeInterpreter i, Visualization v) {
		interpreter = i;
		vis = v;
		setupHelpMenu();
	}
	
	private void setupHelpMenu() {
		this.setText("Preferences");
		this.setExpanded(false);
		VBox buttonList = new VBox();
		buttonList.setSpacing(10);
		buttonList.getChildren().addAll(
				new HelpButton(),
				new CanvasColorButton(interpreter),
				new LanguageChangeButton(vis));
		this.setContent(buttonList);
	}
	
	
	
}