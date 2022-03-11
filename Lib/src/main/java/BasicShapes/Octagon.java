package BasicShapes;

import FactoryElements.Shape;

public class Octagon implements Shape {
    double sideLength;

    public Octagon(double sLen){
        sideLength = sLen;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
