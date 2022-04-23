package DrawingObjects;

import FactoryElements.InputObject;

import static DrawingObjects.Functions.ShapeSVGFunctions.activationExpressionSVG;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.activationExpressionJFX;
/**
 * @author David Lindeman
 * IFML Parameters can be either DogEaredRectangles OR Parallelograms
 * TextBoxes have 2 elements, [1] = header, [0] = body
 * InputObject inParams size of 2
 */
public class IFMLParameterActivationFunction extends DrawingObject{

    public IFMLParameterActivationFunction(String id, InputObject inObj){
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
        return activationExpressionSVG(super.inObject);
    }

    @Override
    public void generateJavaFXGroup() {
        getChildren().addAll(activationExpressionJFX(super.inObject));
        addTextBoxesToJavaFXGroup();
    }
}
