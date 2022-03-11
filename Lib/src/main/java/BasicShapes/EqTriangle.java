package BasicShapes;

import FactoryElements.Shape;

/**
 * @author David Lindeman
 * Generates an equilateral triangle
 */
public class EqTriangle implements Shape {
    double sideLength;
    public EqTriangle(double sLen){
        sideLength = sLen;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
