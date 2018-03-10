package view.vis_elements;


import interpreter.CommandTreeInterpreter;
import javafx.scene.layout.VBox;
import view.menus.ColorPaletteMenu;
import view.menus.CustomVarsMenu;
import view.menus.HelpMenu;
import view.menus.TurtleInfoMenu;

public class ControlPanelRight extends VBox {
	
	private CommandTreeInterpreter interpreter;
	private Visualization vis;
	

	public ControlPanelRight(CommandTreeInterpreter i, Visualization v) {
		interpreter = i;
		vis = v;
		initializeMenus();
	}

	private void initializeMenus() {

		this.getChildren().addAll(
				new CustomVarsMenu(interpreter),
				new TurtleInfoMenu(interpreter.getTurtleController()),
				new HelpMenu(interpreter, vis),
				new ColorPaletteMenu(interpreter,"BluePalette"));
	}

}
