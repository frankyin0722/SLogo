package visual_elements.user_input_presentation;

import java.util.List;

import javafx.scene.text.Text;

public class ListToTextHelper extends Text {

	public ListToTextHelper(List<String> myList) {
		if(myList!=null) {
			this.setText(buildParagraph(myList));
		}
	}
	
	private String buildParagraph(List<String> myList) {
		String newParagraph = new String();
		for(int i=0; i<myList.size()-1; i++) {
			newParagraph += (myList.get(i)+"\n");
		} newParagraph += myList.get(myList.size()-1);
		System.out.println(newParagraph);
		return newParagraph;
	}
}
