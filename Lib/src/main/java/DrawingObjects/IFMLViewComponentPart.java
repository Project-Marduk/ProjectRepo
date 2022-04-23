package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

import static DrawingObjects.Functions.ShapeSVGFunctions.rectToSVG;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.rectToJavaFX;

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

    public IFMLViewComponentPart(String id, InputObject inObj){
        super(id, inObj);
        super.setTextBoxes(new TextBox[]{
            new TextBox("",
                    super.inObject.getXCord() + super.inObject.getParams()[0] *.1,
                    0),
            new TextBox("",
                    super.inObject.getXCord() + super.inObject.getParams()[0] + 2,
                    0),
            new TextBox("",
                    super.inObject.getXCord() + super.inObject.getParams()[0] + 2,
                    0)
        });
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

        String smallBox = rectToSVG(
                new InputObject(
                        "Rectangle",
                        new double[]{super.inObject.getParams()[0] * .75,
                                super.inObject.getParams()[1] * smallBoxSizeScalar},
                        super.inObject.getColor(),
                        super.inObject.getStyle(),
                        super.inObject.getXCord(),
                        super.inObject.getYCord() + smallBoxYPosScalar, new String[]{""}
                ));

        //large text box
        String largeTextBox = rectToSVG(
                new InputObject(
                        "Rectangle",
                        //{width, height}
                        new double[]{super.inObject.getParams()[0],
                                largeBoxHeight},
                        super.inObject.getColor(),
                        super.inObject.getStyle(),
                        super.inObject.getXCord(),
                        super.inObject.getYCord() + largeBoxYPosScalar + 2, new String[]{""}
                ));

        String innerTextBox = rectToSVG(
                new InputObject(
                        "Rectangle",
                        new double[]{super.inObject.getParams()[0]*.8,
                                super.inObject.getParams()[1]*largeBoxSizeScalar*.5-2},
                        super.inObject.getColor(),
                        super.inObject.getStyle(),
                        super.inObject.getXCord() + super.inObject.getParams()[0]*.05,
                        super.inObject.getYCord() + largeBoxYPosScalar + largeBoxHeight*.5, new String[]{""}
                ));

        return smallBox + "\n " +
                largeTextBox + "\n " +
                innerTextBox;
    }

    @Override
    public void generateJavaFXGroup() {
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

        //Large text box
        super.getTextBox(0).setYCord(super.inObject.getYCord() + super.inObject.getParams()[1] * smallBoxSizeScalar + 2);
        //header text inside larger text box
        super.getTextBox(1).setYCord(super.inObject.getYCord() + super.inObject.getParams()[1] * largeBoxYPosScalar + 2);
        //text box inside larger text box
        super.getTextBox(2).setYCord(super.inObject.getYCord() + super.inObject.getParams()[1] * largeBoxYPosScalar + 2);

        getChildren().addAll(
                rectToJavaFX(super.inObject),
                rectToJavaFX(
                new InputObject(
                        "Rectangle",
                        new double[]{super.inObject.getParams()[0] * .75,
                                super.inObject.getParams()[1] * smallBoxSizeScalar},
                        super.inObject.getColor(),
                        super.inObject.getStyle(),
                        super.inObject.getXCord(),
                        super.inObject.getYCord() + smallBoxYPosScalar, new String[]{""}
                )),
                //large text box
                rectToJavaFX(
                new InputObject(
                        "Rectangle",
                        //{width, height}
                        new double[]{super.inObject.getParams()[0],
                                largeBoxHeight},
                        super.inObject.getColor(),
                        super.inObject.getStyle(),
                        super.inObject.getXCord(),
                        super.inObject.getYCord() + largeBoxYPosScalar + 2, new String[]{""}
                )),
                rectToJavaFX(
                new InputObject(
                        "Rectangle",
                        new double[]{super.inObject.getParams()[0]*.8,
                                super.inObject.getParams()[1]*largeBoxSizeScalar*.5-2},
                        super.inObject.getColor(),
                        super.inObject.getStyle(),
                        super.inObject.getXCord() + super.inObject.getParams()[0]*.05,
                        super.inObject.getYCord() + largeBoxYPosScalar + largeBoxHeight*.5, new String[]{""}
                )));
        addTextBoxesToJavaFXGroup();
    }
}
