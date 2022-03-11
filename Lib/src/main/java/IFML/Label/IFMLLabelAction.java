package IFML.Label;

import BasicShapes.Hexagon;

/**
 * @author David Lindeman
 * IFML Actions are hexagons with a name of the action in the center
 */
public class IFMLLabelAction extends IFMLLabel {
    double sideLength;
    public IFMLLabelAction(String id, int sLen){
        super(id, "Enter Text");
        sideLength = sLen;
    }


    public void generateShape(){
        svgData = new Hexagon(sideLength).getSVGData();
        //display text information
        //assign color?
    }
}
