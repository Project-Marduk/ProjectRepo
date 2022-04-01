package IFML;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

import static FactoryElements.ShapeSVGFunctions.getLineElement;
import static FactoryElements.ShapeSVGFunctions.headTriangleToSVG;

/**
 * @author David Lindeman
 * Line for UML diagrams
 * TODO: LINES NEED OUT OF BOUNDS CHECK CODE RAN WHEN HEAD LOCATIONS ARE DECIDED OR FRAMEWORK FOR OUT OF BOUNDS SVG ELEMENTS NEED TO NOT BE DISPLAYED
 *
 */
@Getter @Setter
@Table("IFML_Line")
public class IFMLLine extends LineObject {
    boolean headIsLeft;
    boolean headIsVert;
    String headSVG;

    public IFMLLine(String id, InputObject inObj) {
        super(id, inObj);
        headIsLeft = true;
        headIsVert = true;
        headSVG = "";
        generateShape();
    }

    /**
     * changes the heads direction to the opposite end
     * This will be set by the user
     */
    public void changeHeadLeft(){
        headIsLeft = !headIsLeft;
    }

    public void makeHeadSVG(){
        headSVG = headTriangleToSVG(super.getX(), super.getY(), headIsLeft, headIsVert);
    }

    public void generateShape(){
        //check the bearing of the second x cord to determine
        if(super.getSecondXCord() >= super.getX()){
            headIsVert = true;
        }
        else{
            headIsVert = false;
        }
         super.setShapeSVG(getLineElement(
                 Double.toString(super.getX()),
                 Double.toString(super.getY()),
                 Double.toString(super.getSecondXCord()),
                 Double.toString(super.getSecondYCord())
         ));
         makeHeadSVG();
    }
}