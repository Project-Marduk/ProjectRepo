package DrawingObjects.JavaFXConversion;


import DrawingObjects.DrawingObject;
import javafx.scene.Group;

public class JavaFXDrawingObject extends Group {
    private DrawingObject linkedDrawing;

    public JavaFXDrawingObject(DrawingObject parent){
        //saying its null here as well?
        super();
        this.getChildren().addAll(linkedDrawing.generateJavaFXGroup().getChildren());
        this.linkedDrawing = parent;
    }

    public void update(){
        getChildren().clear();
        getChildren().addAll(linkedDrawing.generateJavaFXGroup().getChildren());
    }

    public DrawingObject getParentDrawing() {
        return linkedDrawing;
    }
}
