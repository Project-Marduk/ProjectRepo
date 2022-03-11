package IFML.Label;


import BasicShapes.Circle;

public class IFMLLabelEvent extends IFMLLabel{
    double diameter;
    public IFMLLabelEvent(String id, double dia){
        super(id, "");
        diameter = dia;
    }

    public void generateShape(){
        svgData = new Circle(diameter).getSVGData();
        //display text information
        //assign color?
    }
}
