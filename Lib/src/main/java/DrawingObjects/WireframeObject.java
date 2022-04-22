package DrawingObjects;

import FactoryElements.InputObject;
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
        switch (super.getInObject().getShapeType()) {
            case "Rectangle":
                shapeSVG = rectToSVG(super.getInObject());
                break;
            case "Square":
                shapeSVG = squareToSVG(super.getInObject());
                break;
            case "Circle":
                shapeSVG = circleToSVG(super.getInObject());
                break;
            case "Hexagon":
                shapeSVG = hexagonToSvg(super.getInObject());
                break;
            case "Parallelogram":
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
        switch (super.getInObject().getShapeType()) {
            case "Rectangle":
                g.getChildren().add(rectToJavaFX(super.getInObject()));
                break;
            case "Square":
                g.getChildren().add(squareToJavaFX(super.getInObject()));
                break;
            case "Circle":
                g.getChildren().add(circleToJavaFX(super.getInObject()));
                break;
            case "Hexagon":
                g.getChildren().add(hexagonToJavaFX(super.getInObject()));
                break;
            case "Parallelogram":
                g.getChildren().add(parallelogramToJAVAFX(super.getInObject()));
                break;
            default:
                break;
        }
        return g;
    }
}
