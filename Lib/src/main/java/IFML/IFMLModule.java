package IFML;

import FactoryElements.InputObject;
import static FactoryElements.ShapeSVGFunctions.squareToSVG;

/**
 * @author David Lindeman
 * Contains 1 large box and 2 smaller boxes on its left and right edges
 * the smaller box on the left edge will be
 */
public class IFMLModule extends DrawingObject {
    String text;

    public IFMLModule(String id, InputObject inObj){
        super(id, inObj);
        text = "";
    }

    public void generateShape(){
        int fontSize = 18; //this should be a passed in variable that will be padded
        super.shapeSVG = squareToSVG(super.getInObject());

        String leftBox = squareToSVG(new InputObject("Square",
                new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                "#OOOOOO", //hex code for black
                super.inObject.getStyle(),
                super.x, //x axis stays in line with the up left of the larger square
                super.y - super.inObject.getParams()[1]*.5) //move the y axis of the box to halfway down the height of the larger square
        );

        String rightBox = squareToSVG(new InputObject("Square",
                new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                "#000000", //hex code for black TODO: UPDATE FILL INPUT FOR DEFAULT VALUES
                super.inObject.getStyle(),
                super.x + super.inObject.getParams()[0], //move the x axis to the end of the box
                super.y - super.inObject.getParams()[1]) //move the y axis of the box to halfway down the height of the larger square
        );

        //text box
        String textSvg = super.txtToSVG(text,
                inObject.getXCord()+inObject.getParams()[0],
                inObject.getYCord()+inObject.getParams()[1]);
        super.svgData = super.shapeSVG + leftBox + rightBox + textSvg;
    }
}
