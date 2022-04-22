package DrawingObjects;

import FactoryElements.InputObject;
import javafx.scene.Group;
import javafx.scene.shape.Path;
import lombok.Getter;
import lombok.Setter;

import static DrawingObjects.ShapeSVGFunctions.getLineElement;
import static DrawingObjects.ShapeSVGFunctions.headTriangleToSVG;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.addLinetoPath;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.headTriangleToJavaFX;

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
                        super.getX(),
                        super.getY())
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
        return headTriangleToSVG(super.getX(), super.getY(), headIsLeft, headIsVert);
    }

    public String generateShape(){
        //check the bearing of the second x cord to determine
        if(super.getSecondXCord() >= super.getX()){
            headIsVert = true;
        }
        else{
            headIsVert = false;
        }
         return getLineElement(
                 Double.toString(super.getX()),
                 Double.toString(super.getSecondXCord()),
                 Double.toString(super.getY()),
                 Double.toString(super.getSecondYCord())
         ) + makeHeadSVG();
    }

    @Override
    public Group generateJavaFXGroup() {
        if(super.getSecondXCord() >= super.getX()){
            headIsVert = true;
        }
        else{
            headIsVert = false;
        }

        return new Group(
                addLinetoPath(super.getX(), super.getSecondXCord(), super.getY(), super.getSecondYCord(), new Path()),
                headTriangleToJavaFX(super.getX(), super.getY(), headIsLeft, headIsVert));

    }
}