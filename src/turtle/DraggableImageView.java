package turtle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DraggableImageView extends ImageView {
    private double mouseX ;
    private double mouseY ;
    public DraggableImageView(Turtle turtle, Image image) {
        super(image);

        this.setOnMouseDragged(event -> {
        		double deltaX = event.getX();
        		double deltaY = event.getY();
//           double deltaX = event.getSceneX() - mouseX ;
//           double deltaY = event.getSceneY() - mouseY ;
//        		turtle.getPen().setPen(true);
//        		turtle.changeX(getLayoutX() + deltaX);
//        		turtle.changeY(getLayoutY() + deltaY);
//        		turtle.update();
//        		turtle.getPen().setPen(false);
//        		turtle.inform();
//           this.relocate(getLayoutX() + deltaX, getLayoutY() + deltaY);
//           mouseX = event.getSceneX() ;
//           mouseY = event.getSceneY() ;
        });
    }
}

