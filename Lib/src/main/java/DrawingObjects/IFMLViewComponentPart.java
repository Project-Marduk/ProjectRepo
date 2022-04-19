package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

import static DrawingObjects.ShapeSVGFunctions.rectToSVG;

/**
 * @author David Lindeman
 * View Component Part can be placed by the user (via frontend implimentation) in other shapes such as Container
 * There are three text box components two of which are oriented as linked together the other can be floating above or below
 * All text boxes will need to be set with the setter method called from the front end (ex. type into a box then the method is called)
 */
@Setter @Getter
//@Table("IFML_View_Component_Part")
public class IFMLViewComponentPart extends DrawingObject {
    String text1;
    String text2;
    String text3;
    boolean floatUp; //default value is to float text1 above the second box
    double fontSize;

    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);
        text1 = ""; text2 = ""; text3 ="";
        floatUp = true;
        TextBox t1, t2, t3;
        t1 = new TextBox();
        t2 = new TextBox();
        t3 = new TextBox();
        t1.initialize(
                "",
                super.inObject.getXCord() + super.inObject.getParams()[0] *.1,
                0);
        t2.initialize(
                "",
                super.inObject.getXCord() + super.inObject.getParams()[0] + 2,
                0);
        t3.initialize("",
                super.inObject.getXCord() + super.inObject.getParams()[0] + 2,
                0);
        super.setTextBoxes(new TextBox[]{t1, t2, t3});
    }

    /**
     * @author David Lindeman
     * @return
     * The input object given by the user will be segmented to make the shape of the inner 3 squares
     */
    public String generateShape(){

        double largeBoxYPosScalar;
        double smallBoxYPosScalar;
        double smallBoxSizeScalar = .3;
        double largeBoxSizeScalar = .7;
        double smallBoxHeight = super.inObject.getParams()[1] * smallBoxSizeScalar;
        double largeBoxHeight = super.inObject.getParams()[1] * largeBoxSizeScalar;
        if(floatUp){
            largeBoxYPosScalar = smallBoxHeight;
            smallBoxYPosScalar = 0;
        }
        else{
            largeBoxYPosScalar = 0;
            smallBoxYPosScalar = largeBoxHeight;
        }

        //outlineShape will be entirely transparent
        String outlineShape = rectToSVG(super.inObject);

        //Large text box
        super.getTextBox(0).setYCord(super.inObject.getYCord() + super.inObject.getParams()[1] * smallBoxSizeScalar + 2);
        //header text inside larger text box
        super.getTextBox(1).setYCord(super.inObject.getYCord() + super.inObject.getParams()[1] * largeBoxYPosScalar + 2);
        //text box inside larger text box
        super.getTextBox(2).setYCord(super.inObject.getYCord() + super.inObject.getParams()[1] * largeBoxYPosScalar + 2);

        InputObject small = new InputObject();
        small.initialize("Rectangle",
                new double[]{super.inObject.getParams()[0] * .75,
                        super.inObject.getParams()[1] * smallBoxSizeScalar},
                super.inObject.getColor(),
                super.inObject.getStyle(),
                super.inObject.getXCord(),
                super.inObject.getYCord() + smallBoxYPosScalar
        );
        String smallBox = rectToSVG(small);

        //large text box
        InputObject large = new InputObject();
        large.initialize("Rectangle",
                //{width, height}
                new double[]{super.inObject.getParams()[0],
                        largeBoxHeight},
                super.inObject.getColor(),
                super.inObject.getStyle(),
                super.inObject.getXCord(),
                super.inObject.getYCord() + largeBoxYPosScalar + 2
        );
        String largeTextBox = rectToSVG(large);

        InputObject inner = new InputObject();
        inner.initialize(
                "Rectangle",
                new double[]{super.inObject.getParams()[0]*.8,
                        super.inObject.getParams()[1]*largeBoxSizeScalar*.5-2},
                super.inObject.getColor(),
                super.inObject.getStyle(),
                super.inObject.getXCord() + super.inObject.getParams()[0]*.05,
                super.inObject.getYCord() + largeBoxYPosScalar + largeBoxHeight*.5
        );
        String innerTextBox = rectToSVG(inner);

        return smallBox + "\n " +
                largeTextBox + "\n " +
                innerTextBox;
    }
}
