package DrawingObjects.JavaFXConversion;


import DrawingObjects.DrawingObject;
import javafx.scene.Group;

public class JavaFXDrawingObject extends Group {
    private DrawingObject linkedDrawing;


    public JavaFXDrawingObject(DrawingObject parent){
        super();
        this.linkedDrawing = parent;
    }

    public void update(){
        getChildren().clear();
        linkedDrawing.generateJavaFXGroup();
    }

    public DrawingObject getLinkedDrawing() {
        return linkedDrawing;
    }
}
