package IFML;

import FactoryElements.Lines.AbstractLine;
import FactoryElements.Lines.HeadType;
import lombok.Getter;
import lombok.Setter;

import static FactoryElements.ShapeSVGFunctions.headTriangleToSVG;

/**
 * @author David Lindeman
 * Line for UML diagrams
 * TODO: LINES NEED OUT OF BOUNDS CHECK CODE RAN WHEN HEAD LOCATIONS ARE DECIDED OR FRAMEWORK FOR OUT OF BOUNDS SVG ELEMENTS NEED TO NOT BE DISPLAYED
 * TODO: A way to determine
 */
@Getter @Setter
public class IFMLLine extends AbstractLine {
    boolean headIsLeft;
    boolean headIsVert;
    String headSVG;

    public IFMLLine(double lX, double lY, double rX, double rY, String lineType) {
        super(lX, lY, rX, rY, lineType);
        headIsLeft = true;
        headIsVert = true;
        headSVG = "";
    }

    /**
     * changes the heads direction to the opposite end
     * This will be set by the user
     */
    public void changeHeadLeft(){
        headIsLeft = !headIsLeft;
    }

    public void makeHeadSVG(){
        headSVG = headTriangleToSVG(super.getLeftXCord(), super.getLeftYCord(), headIsLeft, headIsVert);
    }

    public void drawLine(){
        if(super.getLeftYCord() <= super.getRightYCord()){
            headIsVert = true;
        }
        else{
            headIsVert = false;
        }
         super.getLineSVG();
         makeHeadSVG();
    }
}