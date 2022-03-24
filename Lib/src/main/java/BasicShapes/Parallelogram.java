package BasicShapes;

import FactoryElements.Interfaces.Shape;

public class Parallelogram implements Shape {
    double height;
    double width;
    double skew; //skew degrees

    public Parallelogram(int w, int h){
        height = h;
        width = w;
        skew = 30;
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
