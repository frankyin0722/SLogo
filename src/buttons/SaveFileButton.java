package buttons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;

import alerts.Alerts;
import interpreter.CommandTreeInterpreter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveFileButton extends BaseButton {
	private Document myDocument;
	public SaveFileButton() {
		super("Save");
	}
	
	public void save(CommandTreeInterpreter interpreter) {
		this.setOnAction(
				new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
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
			});
	}
//	 /**
//     * Helper method to add an element to the root for creating the XML file
//     * @param elementName
//     * @param elementData
//     * @param root
//     */
//	private void addElement(String elementName, String elementData, Element root) {
//        Element elem = myDocument.createElement(elementName);
//        elem.appendChild(myDocument.createTextNode(elementData));
//        root.appendChild(elem);
//    }
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
        for(String str: history) {
        		fileWriter.write(str+"\n");
        }
        fileWriter.close();
        Alerts.XMLCreated(filePath);
    }
}
