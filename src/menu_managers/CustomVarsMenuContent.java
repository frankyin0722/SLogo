package menu_managers;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class CustomVarsMenuContent extends VBox {
	public CustomVarsMenuContent() {
		setupTurtleCommandsTable();
		
	}
	
	@SuppressWarnings("unchecked")
	private void setupTurtleCommandsTable() {
		TableView turtleCommands = new TableView();
		turtleCommands.setEditable(true);
		TableColumn nameCol = new TableColumn("Name");
        TableColumn descripCol = new TableColumn("Description");
        nameCol.prefWidthProperty().bind(turtleCommands.widthProperty().divide(2));
        descripCol.prefWidthProperty().bind(turtleCommands.widthProperty().divide(2));
        turtleCommands.getColumns().addAll(nameCol, descripCol);
        this.getChildren().add(turtleCommands);
	}
}
