package BasicShapes;

import FactoryElements.Interfaces.Shape;

public class Circle implements Shape {
    double radius;
    public Circle(double r){
        radius = r;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
