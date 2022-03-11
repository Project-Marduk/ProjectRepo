package BasicShapes;

import FactoryElements.Shape;

public class Rectangle implements Shape {
    double height;
    double width;

    public Rectangle(double h, double w){
        height = h;
        width = w;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
