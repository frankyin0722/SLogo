package buttons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.transform.TransformerException;
import alerts.Alerts;
import interpreter.CommandTreeInterpreter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * Button for saving history to a file
 * @author shichengrao
 *
 */
public class SaveFileButton extends BaseButton {
	public SaveFileButton() {
		super("Save");
	}
	/**
	 * saves file given data from interpreter
	 * @param interpreter
	 */
	public void save(CommandTreeInterpreter interpreter) {
		
						FileChooser fc = new FileChooser();
						Stage stage = new Stage();
						fc.setInitialDirectory(new File("./data/examples"));
						fc.setTitle("Save File");
						File file = fc.showSaveDialog(stage);
						try {
							saveXMLFile(file.getCanonicalPath(), interpreter);
						} catch (Exception e) {
							Alerts.createAlert(null, "change");
						}
				
		
	}
//  This commented out method was the beginning of my attempt to save the current variables and methods directly to an xml file (no need for actual history of commands)
//	private void recordVariables(CommandTreeInterpreter interpreter) {
//		Element dataElement = myDocument.createElement("data");
//	    myDocument.appendChild(dataElement);
//		VariableManager manager = interpreter.getVariables();
//		for(String name: manager.getNames()) {
//			addElement("Variable", name +"-" +  String.valueOf((double)manager.getVariable(name).getValue()), dataElement);
//		}
//	}
	 /**
     * Method to save the XML file to the correct filePath, and then notify the user of its success
     * with a javafx Alert
     * modified from cell society 
     * @param filePath
     * @throws TransformerException
     * @throws IOException
     */
    public void saveXMLFile(String filePath, CommandTreeInterpreter interpreter) throws TransformerException, IOException {
        List<String> history = interpreter.getHistory();
        FileWriter fileWriter = new FileWriter(filePath);
        //use string builder, call write once
        for(String str: history) {
        		fileWriter.write(str+"\n");
        }
        fileWriter.close();
        Alerts.XMLCreated(filePath);
    }
}
