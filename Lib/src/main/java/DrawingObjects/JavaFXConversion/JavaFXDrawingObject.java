package DrawingObjects.JavaFXConversion;


import DrawingObjects.DrawingObject;
import javafx.scene.Group;

public class JavaFXDrawingObject extends Group {
    private DrawingObject linkedDrawing;


    public JavaFXDrawingObject(DrawingObject parent){
        super();
        this.linkedDrawing = parent;

        update();
    }

    public void update(){
        getChildren().clear();
        getChildren().addAll(linkedDrawing.generateJavaFXGroup().getChildren());
    }

    public DrawingObject getLinkedDrawing() {
        return linkedDrawing;
    }
}
