package DrawingObjects.JavaFXConversion;


import DrawingObjects.DrawingObject;
import FactoryElements.InputObject;
import javafx.scene.Group;

public class JavaFXDrawingObject extends Group {
    DrawingObject linkedDrawingObject;

    public JavaFXDrawingObject(DrawingObject parent){
        super(parent.getLinkedJavaFXObject());
        this.linkedDrawingObject = parent;
    }





}
