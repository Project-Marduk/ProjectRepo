package IFML;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

import static FactoryElements.ShapeSVGFunctions.getLineElement;
import static FactoryElements.ShapeSVGFunctions.headTriangleToSVG;

@Getter @Setter
public class LineObject extends DrawingObject{
    double secondXCord;
    double secondYCord;
    boolean headIsLeft;
    boolean headIsVert;
    String headSVG;

    public LineObject(String id, InputObject inObj) {
        super(id, inObj);
        secondXCord = inObj.getParams()[0];
        secondYCord = inObj.getParams()[1];
        headIsLeft = true;
        headIsVert = true;
        headSVG = "";
    }

    public void generateShape(){
    }
}
