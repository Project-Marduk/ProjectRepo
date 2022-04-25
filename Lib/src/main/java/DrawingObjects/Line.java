package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.shape.Path;

import static DrawingObjects.Functions.ShapeSVGFunctions.getLineElement;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.addLinetoPath;

//@Table("Line")
public class Line extends LineObject {
    public Line(InputObject inObj) {
        super(inObj);
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        inObject.getXCord(),
                        inObject.getYCord())
        });
    }

    public String generateShape(){
        return getLineElement(
                Double.toString(inObject.getXCord()),
                Double.toString(inObject.getYCord()),
                Double.toString(super.getSecondXCord()),
                Double.toString(super.getSecondYCord())
        );
    }

    @Override
    public void generateJavaFXGroup() {
        getChildren().addAll(addLinetoPath(inObject.getXCord(), inObject.getYCord(), getSecondXCord(), getSecondYCord(), new Path()));
        addTextBoxesToJavaFXGroup();
    }
}
