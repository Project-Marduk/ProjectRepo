package IFML;

import FactoryElements.InputObject;
import FactoryElements.ShapeFactory;
import IFML.IFMLObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * View Component Part can be placed by the user (via frontend implimentation) in other shapes such as Container
 * There are three text box components two of which are oriented as linked together the other can be floating above or below
 * All text boxes will need to be set with the setter method called from the front end (ex. type into a box then the method is called)
 */
@Setter @Getter
public class IFMLViewComponentPart extends IFMLObject {
    String text1;
    String text2;
    String text3;
    boolean floatUp; //default value is to float text1 above the second box

    public IFMLViewComponentPart(String id, InputObject inObj){
        super(id, inObj);
        //default values for text boxes
        text1 = ""; text2 = ""; text3 ="";
        floatUp = true;
    }

    public void generateShape(){
        ShapeFactory sF = new ShapeFactory();
        //TODO: Create the box for text2 and the inner box for text3
        //TODO: Create the box for text1 and orient it above or below based on floatUp
        int fontSize = 18; //this should be a passed in variable that will be padded

        //outlineShape will be entirely transparent
        String outlineShape = sF.create(super.inObject).getSVGData();

        //large text box
        String shape2 = sF.create(
                new InputObject(
                        "Rectangle",
                        new double[]{super.inObject.getParams()[0]-fontSize*1.25, super.inObject.getParams()[1]}, //first param is arbitrarily set to 18, this should be set to the font size of the text
                        super.inObject.getColor(),
                        super.inObject.getStyle()
                )).getSVGData();
        String text2svg = super.txtToSVG(text2);
        //text box above of below larger text box
        String text1svg = super.txtToSVG(text1);
        String shape1 = sF.create(
                new InputObject(
                        "Rectangle",
                        new double[]{fontSize*1.1, super.inObject.getParams()[1]}, //first param is arbitrarily set to 18, this should be set to the font size of the text
                        super.inObject.getColor(),
                        super.inObject.getStyle()
                )).getSVGData();
        //text box inside larger text box
        String text3svg = super.txtToSVG(text3);
        String shape3 = sF.create(
                new InputObject(
                        "Rectangle",
                        new double[]{fontSize*1.1, super.inObject.getParams()[1]}, //first param is arbitrarily set to 18, this should be set to the font size of the text
                        super.inObject.getColor(),
                        super.inObject.getStyle()
                )).getSVGData();
        super.setSvgData(shape1 + text1svg + shape2 + text2svg + shape3 + text3svg);
        System.out.println("IFML View Component Part generateShape");
    }
}
