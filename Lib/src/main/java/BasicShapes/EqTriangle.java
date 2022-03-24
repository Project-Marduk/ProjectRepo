package BasicShapes;

import FactoryElements.Interfaces.Shape;

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
