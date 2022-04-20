package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.Group;
import javafx.scene.shape.Path;

import static DrawingObjects.ShapeSVGFunctions.getLineElement;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.addLinetoPath;

//@Table("Line")
public class Line extends LineObject {
    public Line(String id, InputObject inObj) {
        super(id, inObj);
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.getX(),
                        super.getY())
        });
    }

    public String generateShape(){
        return getLineElement(
                Double.toString(super.getX()),
                Double.toString(super.getY()),
                Double.toString(super.getSecondXCord()),
                Double.toString(super.getSecondYCord())
        );
    }

    @Override
    public void generateJavaFXGroup() {
        Path p = new Path();
        addLinetoPath(super.getX(), super.getY(), getSecondXCord(), getSecondYCord(), p);
        super.linkedJavaFXObject.getChildren().add(p);
    }
}
