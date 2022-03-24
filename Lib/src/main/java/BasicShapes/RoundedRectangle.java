package BasicShapes;

import FactoryElements.Interfaces.Shape;

public class RoundedRectangle implements Shape {
    double height;
    double width;

    public RoundedRectangle(double h, double w){
        height = h;
        width = w;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
