package BasicShapes;

import FactoryElements.Interfaces.Shape;

/**
 * @author David Lindeman
 * Square class
 */

public class Square implements Shape {
    double sideLength;
    public Square(double sLen){
        sideLength = sLen;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
