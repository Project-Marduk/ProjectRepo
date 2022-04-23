package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

import static DrawingObjects.Functions.ShapeSVGFunctions.*;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.*;

@Getter @Setter
/**
 * @author David Lindeman
 * Wireframe objects are represented as a single shape with a text box,
 * The user will be able to treat this as a simplistic paint feature to design the UI they want to show
 */
//@Table("Wireframe_Object")
public class WireframeObject extends DrawingObject {

    public WireframeObject(InputObject inObj){
        super(inObj);
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
    public void generateJavaFXGroup() {
        ShapeTypes type = ShapeTypes.enumOfString(super.getInObject().getShapeType());
        switch (type) {
            case rectangle:
                getChildren().addAll(rectToJavaFX(super.getInObject()));
                break;
            case square:
                getChildren().addAll(squareToJavaFX(super.getInObject()));
                break;
            case circle:
                getChildren().addAll(circleToJavaFX(super.getInObject()));
                break;
            case hexagon:
                getChildren().addAll(hexagonToJavaFX(super.getInObject()));
                break;
            case parallelogram:
                getChildren().addAll(parallelogramToJAVAFX(super.getInObject()));
                break;
            default:
                getChildren().addAll(circleToJavaFX(super.getInObject()));
                break;
        }
        addTextBoxesToJavaFXGroup();
    }
}
