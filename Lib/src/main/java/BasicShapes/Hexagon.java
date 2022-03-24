package BasicShapes;

import FactoryElements.Interfaces.Shape;

public class Hexagon implements Shape {
    double sideLength;

    public Hexagon(double sLen){
        sideLength = sLen;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
