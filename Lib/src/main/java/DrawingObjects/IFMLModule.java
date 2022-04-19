package DrawingObjects;

import FactoryElements.InputObject;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.squareToSVG;

/**
 * @author David Lindeman
 * Contains 1 large box and 2 smaller boxes on its left and right edges
 * the smaller box on the left edge will be
 * TextBoxes is size 1
 * InputObjects Params are size 2
 */
//@Table("IFML_Module")
public class IFMLModule extends DrawingObject {

    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);

        TextBox t1;
        t1 = new TextBox();
        t1.initialize( "",
                inObject.getXCord()+inObject.getParams()[0],
                inObject.getYCord()+inObject.getParams()[1]);
        super.setTextBoxes(new TextBox[]{t1});

    }

    public String generateShape(){

        InputObject left = new InputObject();
        left.initialize("Square",
                new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                "#OOOOOO", //hex code for black
                super.inObject.getStyle(),
                super.x, //x axis stays in line with the up left of the larger square
                super.y - super.inObject.getParams()[1]*.5); //move the y axis of the box to halfway down the height of the larger square

        String leftBox = squareToSVG(left);

        InputObject right = new InputObject();
        right.initialize("Square",
                new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                "#000000", //hex code for black TODO: UPDATE FILL INPUT FOR DEFAULT VALUES
                super.inObject.getStyle(),
                super.x + super.inObject.getParams()[0], //move the x axis to the end of the box
                super.y - super.inObject.getParams()[1]); //move the y axis of the box to halfway down the height of the larger square

        String rightBox = squareToSVG(right);

        return squareToSVG(super.getInObject()) + "\n" + leftBox + "\n" + rightBox;
    }
}
