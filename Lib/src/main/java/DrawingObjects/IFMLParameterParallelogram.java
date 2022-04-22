package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.Group;

import static DrawingObjects.ShapeSVGFunctions.parallelogramToSVG;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.parallelogramToJAVAFX;

/**
 * @author David Lindeman
 * IFML Parameters can be either DogEaredRectangles OR Parallelograms
 * TextBoxes have 2 elements, [1] = header, [0] = body
 * InputObject inParams size of 2
 */
//@Table("IFML_Parameter")
public class IFMLParameterParallelogram extends DrawingObject {

    public IFMLParameterParallelogram(String id, InputObject inObj){
        super(id, inObj);
        super.setTextBoxes(new TextBox[]{
            new TextBox("",
                super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                super.inObject.getYCord() - super.inObject.getParams()[1]*.5),
            new TextBox("",
                    super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                    super.inObject.getYCord() - super.inObject.getParams()[1]*.25)
        });
    }

    public String generateShape(){
        return parallelogramToSVG(super.inObject);
    }

    @Override
    public void generateJavaFXGroup() {
        linkedJavaFX.getChildren().addAll(parallelogramToJAVAFX(super.inObject));
    }
}
