package BasicShapes;

import FactoryElements.InputObject;
import FactoryElements.Interfaces.Shape;

public class Rectangle implements Shape {
    double height;
    double width;
    double x;
    double y;

    public Rectangle(InputObject inObj){
        height = inObj.getParams()[0];
        width = inObj.getParams()[1];
        x = inObj.getXCord();
        y = inObj.getYCord();
    }

    public String getSVGData(){
        return "getSVGData";
    }
}
