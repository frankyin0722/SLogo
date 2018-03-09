package turtle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DraggableImageView extends ImageView {
    private double mouseX;
    private double mouseY;
    public DraggableImageView(Turtle turtle, Image image) {
        super(image);
        this.setOnMouseClicked(e -> {
        		if (e.getClickCount() == 2) {
        			turtle.setDirection(turtle.getDirection() + Math.PI/2);
        		}
        });
        this.setOnMousePressed(e -> {
        		mouseX = e.getSceneX();
        		mouseY = e.getSceneY();
        });
        this.setOnMouseDragged(event -> {
		   double deltaX = event.getSceneX() - mouseX ;
		   double deltaY = event.getSceneY() - mouseY ;
		   turtle.getPen().setPen(true);
		   turtle.changeX(turtle.getX() + deltaX);
		   turtle.changeY(turtle.getY() + deltaY);
		   turtle.update();
		   turtle.getPen().setPen(false);
		   mouseX = event.getSceneX();
		   mouseY = event.getSceneY();
        });
    }
}

