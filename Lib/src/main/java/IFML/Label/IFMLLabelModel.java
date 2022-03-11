package IFML.Label;

import BasicShapes.Square;

public class IFMLLabelModel extends IFMLLabel{
    double sideLength;
    public IFMLLabelModel(String id, double sLen){
        super(id, "");
        sideLength = sLen;
    }


    public void generateShape(){
        svgData = new Square(sideLength).getSVGData();
        //display text information
        //assign color?
    }
}
