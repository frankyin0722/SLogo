package visual_elements.menu_managers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

@SuppressWarnings("rawtypes")
public class CustomVarsMenuContent extends VBox {
	public CustomVarsMenuContent() {
//		setupTurtleCommandsTable();		
	}
	
	@SuppressWarnings("unchecked")
	private void setupTurtleCommandsTable(ObservableList<CommandInfo> details) {
		TableView<CommandInfo> turtleCommands = new TableView<>();
		turtleCommands.setEditable(true);
		TableColumn nameCol = new TableColumn("Name");
        TableColumn descripCol = new TableColumn("Description");
        nameCol.prefWidthProperty().bind(turtleCommands.widthProperty().divide(2));
        descripCol.prefWidthProperty().bind(turtleCommands.widthProperty().divide(2));
        turtleCommands.getColumns().addAll(nameCol, descripCol);
        
        nameCol.setCellValueFactory(data -> data.getValue().nameProperty());
        descripCol.setCellValueFactory(data -> data.getValue().descripProperty());
        turtleCommands.setItems(details);
        this.getChildren().add(turtleCommands);
	}
	
	private void readTextFile() {
		try {
			File myFile = new File("commands.txt");
//			Collection<CommandInfo> list = Files.readAllLines(new File("commands.txt").toPath())
//			        .stream()
//			        .map(line -> {
//			            String[] details = line.split("|");
//			            CommandInfo cd = new CommandInfo();
//			            cd.setName(details[0]);
//			            cd.setDescrip(details[1]);
//			            return cd;
//			        })
//			        .collect(Collectors.toList());
//	        ObservableList<CommandInfo> details = FXCollections.observableArrayList(list);
//	        setupTurtleCommandsTable(details);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Change to alert: cannot find commands.txt");
		}
	}
}
