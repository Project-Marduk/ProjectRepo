package DrawingObjects;

import FactoryElements.InputObject;

import static DrawingObjects.ShapeSVGFunctions.activationExpressionSVG;

/**
 * @author David Lindeman
 * IFML Parameters can be either DogEaredRectangles OR Parallelograms
 * TextBoxes have 2 elements, [1] = header, [0] = body
 * InputObject inParams size of 2
 */
public class IFMLParameterActivationFunction extends DrawingObject{

    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);

        TextBox t1, t2;
        t1 = new TextBox();
        t2 = new TextBox();
        t1.initialize("",
                super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                super.inObject.getYCord() - super.inObject.getParams()[1]*.5);
        t2.initialize("",
                super.inObject.getXCord() + super.inObject.getParams()[0]*.5,
                super.inObject.getYCord() - super.inObject.getParams()[1]*.25);
        super.setTextBoxes(new TextBox[]{t1, t2});
    }

    public String generateShape(){
        return activationExpressionSVG(super.inObject);
    }
}
