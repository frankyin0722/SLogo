package view.supplements;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import turtle.Pen;

/**
 * 
 * @author elizabethshulman
 *
 * This class generates a pop-up Stage containing a Slider that modifies a given pen's width.
 */
public class StagePenChanger extends Stage {

	private Pen myPen;
	
	/**
	 * Initialize a StagePenChanger object
	 * @param toModify  pen receiving user modifications
	 */
	public StagePenChanger(Pen toModify) {
		myPen = toModify;
		this.setScene(new Scene(buildSliderBox()));
		this.setTitle("Modify Width");
	}

	/**
	 * Builds an HBox containing a Slider to modify pen width
	 */
	private HBox buildSliderBox() {
		HBox sliderAndButton = new HBox();
		sliderAndButton.getChildren().add(buildWidthSlider());
		sliderAndButton.setPadding(new Insets(10));
		return sliderAndButton;
	}

	/**
	 * Initializes a slider to change the width of a turtle's pen.
	 * Code modified from  <a href="https://docs.oracle.com/javafx/2/ui_controls/slider.htm">JavaFX Documentation</a>
	 * @return slider object to modify turtle's pen width
	 */
	private Slider buildWidthSlider() {
		Slider slider = new Slider(0,2,myPen.getWidth());
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
		            Number oldValue, Number newValue) {
				myPen.setWidth(newValue.doubleValue());;
		    }
		});
		return slider;
	}
}
