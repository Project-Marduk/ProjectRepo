package BasicShapes;

import FactoryElements.Interfaces.Shape;

public class Octagon implements Shape {
    double sideLength;

    public Octagon(double sLen){
        sideLength = sLen;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
