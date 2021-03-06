package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.Group;

import static DrawingObjects.Functions.ShapeSVGFunctions.squareToSVG;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.squareToJavaFX;

/**
 * @author David Lindeman
 * Contains 1 large box and 2 smaller boxes on its left and right edges
 * the smaller box on the left edge will be
 * TextBoxes is size 1
 * InputObjects Params are size 2
 */
//@Table("IFML_Module")
public class IFMLModule extends DrawingObject {

    public IFMLModule(InputObject inObj){
        super(inObj);
        super.setTextBoxes(new TextBox[]{
            new TextBox( "",
            inObject.getXCord()+inObject.getParams()[0],
            inObject.getYCord()+inObject.getParams()[1])
        });
    }

    public String generateShape(){

        String leftBox = squareToSVG(new InputObject("Square",
                new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                "#OOOOOO", //hex code for black
                super.inObject.getStyle(),
                super.inObject.getXCord(), //x axis stays in line with the up left of the larger square
                super.inObject.getYCord() - super.inObject.getParams()[1]*.5, new String[]{""}) //move the y axis of the box to halfway down the height of the larger square
        );

        String rightBox = squareToSVG(new InputObject("Square",
                new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                "#000000", //hex code for black TODO: UPDATE FILL INPUT FOR DEFAULT VALUES
                super.inObject.getStyle(),
                super.inObject.getXCord() + super.inObject.getParams()[0], //move the x axis to the end of the box
                super.inObject.getYCord() - super.inObject.getParams()[1], new String[]{""}) //move the y axis of the box to halfway down the height of the larger square
        );

        return squareToSVG(super.getInObject()) + "\n" + leftBox + "\n" + rightBox;
    }

    @Override
    public void generateJavaFXGroup() {
        getChildren().addAll(
                new Group(
                    squareToJavaFX(new InputObject("Square",
                        new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                    "#OOOOOO", //hex code for black
                        super.inObject.getStyle(),
                        super.inObject.getXCord(), //x axis stays in line with the up left of the larger square
                        super.inObject.getYCord() - super.inObject.getParams()[1]*.5, new String[]{""}) //move the y axis of the box to halfway down the height of the larger square
                    ),
                    squareToJavaFX(new InputObject("Square",
                        new double[]{super.inObject.getParams()[1]*.15}, //2nd dim could be font size however there needs to be a font size to dimension conversion
                        "#000000", //hex code for black TODO: UPDATE FILL INPUT FOR DEFAULT VALUES
                        super.inObject.getStyle(),
                        super.inObject.getXCord() + super.inObject.getParams()[0], //move the x axis to the end of the box
                        super.inObject.getXCord() - super.inObject.getParams()[1], new String[]{""}) //move the y axis of the box to halfway down the height of the larger square
                    ),
                    squareToJavaFX(super.getInObject())).getChildren());
        addTextBoxesToJavaFXGroup();
    }
}
