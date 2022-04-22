package DrawingObjects;

import FactoryElements.InputObject;
import FactoryElements.ShapeTypes;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

import static DrawingObjects.ShapeSVGFunctions.*;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.*;

@Getter @Setter
/**
 * @author David Lindeman
 * Wireframe objects are represented as a single shape with a text box,
 * The user will be able to treat this as a simplistic paint feature to design the UI they want to show
 */
//@Table("Wireframe_Object")
public class WireframeObject extends DrawingObject {

    public WireframeObject(String newId, InputObject inObj){
        super(newId, inObj);
//        setTxtSVGData(""); //default value of an empty string
//        generateShape();
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        inObj.getXCord(),
                        inObj.getYCord())
        });
    }

    /**
     * @author David Lindeman
     * Sets the shape SVG data to the type of shape given to it through the input object,
     * defaults to an empty string on invalid inputs
     */
    public String generateShape(){
        String shapeSVG;
        ShapeTypes type = ShapeTypes.enumOfString(super.getInObject().getShapeType());
        switch (type) {
            case rectangle:
                shapeSVG = rectToSVG(super.getInObject());
                break;
            case square:
                shapeSVG = squareToSVG(super.getInObject());
                break;
            case circle:
                shapeSVG = circleToSVG(super.getInObject());
                break;
            case hexagon:
                shapeSVG = hexagonToSvg(super.getInObject());
                break;
            case parallelogram:
                shapeSVG = parallelogramToSVG(super.getInObject());
                break;
            default:
                shapeSVG = "";
                break;
        }
        //combine SVG Data
        return shapeSVG;
    }

    @Override
    public Group generateJavaFXGroup() {
        Group g = new Group();
        ShapeTypes type = ShapeTypes.enumOfString(super.getInObject().getShapeType());
        switch (type) {
            case rectangle:
                g.getChildren().add(rectToJavaFX(super.getInObject()));
                break;
            case square:
                g.getChildren().add(squareToJavaFX(super.getInObject()));
                break;
            case circle:
                g.getChildren().add(circleToJavaFX(super.getInObject()));
                break;
            case hexagon:
                g.getChildren().add(hexagonToJavaFX(super.getInObject()));
                break;
            case parallelogram:
                g.getChildren().add(parallelogramToJAVAFX(super.getInObject()));
                break;
            default:
                break;
        }
        return g;
    }
}
