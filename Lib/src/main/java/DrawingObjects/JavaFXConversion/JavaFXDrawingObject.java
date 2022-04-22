package DrawingObjects.JavaFXConversion;


import DrawingObjects.DrawingObject;
import FactoryElements.InputObject;
import javafx.scene.Group;

public class JavaFXDrawingObject extends Group {
    public DrawingObject linkedDrawingObject;

    public JavaFXDrawingObject(DrawingObject parent){
        //saying its null here as well?
        super(parent.getLinkedJavaFXObject());
        this.linkedDrawingObject = parent;
    }





}
