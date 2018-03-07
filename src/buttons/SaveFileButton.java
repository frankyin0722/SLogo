package buttons;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import alerts.Alerts;
import interpreter.CommandTreeInterpreter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import variables.VariableManager;
import view.CommandWindow;

public class SaveFileButton extends Button {
	private Document myDocument;
	public SaveFileButton() {
		this.setText("Save");
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
