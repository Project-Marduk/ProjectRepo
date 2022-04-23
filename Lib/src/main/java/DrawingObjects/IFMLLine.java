package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.shape.Path;
import lombok.Getter;
import lombok.Setter;

import static DrawingObjects.Functions.ShapeSVGFunctions.getLineElement;
import static DrawingObjects.Functions.ShapeSVGFunctions.headTriangleToSVG;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.addLinetoPath;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.headTriangleToJavaFX;

/**
 * @author David Lindeman
 * Line for UML diagrams
 * TODO: changing head svg is not reachable with current implementation
 */
@Getter @Setter
//@Table("IFML_Line")
public class IFMLLine extends LineObject {
    boolean headIsLeft;
    boolean headIsVert;

    public IFMLLine(String id, InputObject inObj) {
        super(id, inObj);
        headIsLeft = true;
        headIsVert = true;
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        inObject.getXCord(),
                        inObj.getYCord())
        });
    }

    /**
     * changes the heads direction to the opposite end
     * This will be set by the user
     */
    public void changeHeadLeft(){
        headIsLeft = !headIsLeft;
    }

    public String makeHeadSVG(){
        return headTriangleToSVG(inObject.getXCord(), inObject.getYCord(), headIsLeft, headIsVert);
    }

    public String generateShape(){
        //check the bearing of the second x cord to determine
        if(super.getSecondXCord() >= inObject.getXCord()){
            headIsVert = true;
        }
        else{
            headIsVert = false;
        }
         return getLineElement(
                 Double.toString(inObject.getXCord()),
                 Double.toString(super.getSecondXCord()),
                 Double.toString(inObject.getYCord()),
                 Double.toString(super.getSecondYCord())
         ) + makeHeadSVG();
    }

    @Override
    public void generateJavaFXGroup() {
        if(super.getSecondXCord() >= inObject.getXCord()){
            headIsVert = true;
        }
        else{
            headIsVert = false;
        }
        getChildren().addAll(
                addLinetoPath(inObject.getXCord(), super.getSecondXCord(), inObject.getYCord(), super.getSecondYCord(), new Path()),
                headTriangleToJavaFX(inObject.getXCord(), inObject.getYCord(), headIsLeft, headIsVert));
        addTextBoxesToJavaFXGroup();
    }
}