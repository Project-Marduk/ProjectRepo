package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.Group;
import javafx.scene.shape.Path;
import lombok.Getter;
import lombok.Setter;
//import
//import DrawingObjects.ShapeSVGFunctions.rectToSVG;

import static DrawingObjects.ShapeSVGFunctions.getLineElement;
import static DrawingObjects.ShapeSVGFunctions.rectToSVG;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.rectToJavaFX;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.addLinetoPath;


/**
 * @author David Lindeman
 * The IFML Container object can have 4 different headers as per below
 * View, XOR, Landmark, Default
 * To cut down on the size of this file we will be storing the data that will be presented in the factory object
 * Takes in a rectangle for input object type
 * TextBox has 1 element
 */
@Getter @Setter
//@Table("IFML_Container")
public class IFMLContainer extends DrawingObject {
    String containerHeader;
    String text;
    ShapeSVGFunctions shapeSVGFunctions = new ShapeSVGFunctions();


    public IFMLContainer(String id, InputObject inObj){ //String cHeader, String defaultText,
        super(id, inObj);
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.inObject.getXCord() + 2,
                        super.inObject.getYCord() + 2)
        });
    }

    public String generateShape(){
        //update the text position
        super.getTextBox(0).setYCord(super.inObject.getYCord() + super.getTextBox(0).getFontSize() + 2);
        super.getTextBox(0).setXCord(super.inObject.getXCord() + super.getTextBox(0).getFontSize() + 2);

        String containerBox = rectToSVG(super.inObject);
        String headerLine = getLineElement(
                Double.toString(super.inObject.getXCord()),
                Double.toString(super.inObject.getXCord() + super.inObject.getParams()[0]),
                Double.toString(super.inObject.getYCord() + super.getTextBox(0).getFontSize() + 4),
                Double.toString(super.inObject.getYCord() + super.getTextBox(0).getFontSize() + 4)
                );

        //this could be refactored for the second text box to just be a line 15% down the top of the input rectangle however this doesnt work because our lines have set x,y cords
        return headerLine + "\n" + containerBox; //the shape SVG should be the combination of the two boxes, this may need to be changed depending on how we have to format the text
    }

    @Override
    public Group generateJavaFXGroup() {

        //update the text position
        super.getTextBox(0).setYCord(super.inObject.getYCord() + super.getTextBox(0).getFontSize() + 2);
        super.getTextBox(0).setXCord(super.inObject.getXCord() + super.getTextBox(0).getFontSize() + 2);

        return new Group(
                rectToJavaFX(super.inObject),
                addLinetoPath(
                (super.inObject.getXCord()),
                (super.inObject.getXCord() + super.inObject.getParams()[0]),
                (super.inObject.getYCord() + super.getTextBox(0).getFontSize() + 4),
                (super.inObject.getYCord() + super.getTextBox(0).getFontSize() + 4),
                new Path())
        );

    }
}
