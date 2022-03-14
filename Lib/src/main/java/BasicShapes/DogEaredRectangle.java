package BasicShapes;

import FactoryElements.Interfaces.Shape;

public class DogEaredRectangle implements Shape {
    double height;
    double width;

    public DogEaredRectangle(double h, double w){
        height = h;
        width = w;
    }

    public String getSVGData(){
        //TODO: Requires code for dog earing the upper right corner
        return "getSVGData";
    }
}
