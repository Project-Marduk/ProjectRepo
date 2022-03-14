package IFML;

import FactoryElements.InputObject;
import FactoryElements.ShapeFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * This object will require a special SVG shape construction as it has 5 sides and is not a traditional shape
 */
@Getter @Setter
public class IFMLActivationExpression extends IFMLObject{
    String text;

    public IFMLActivationExpression(String id, InputObject inObj){
        super(id, inObj);
        text = "";
        super.inObject.setColor("#B7BDBB"); //light blue/grey, there are two colors to this shape the other is white
    }

    public void generateShape(){
        ShapeFactory sF = new ShapeFactory();
        //TODO: Shape generation for this object will consist of a triangle and rectangle missing a corner (as to look like the upper right corner was folded in on the rectangle)
        super.shapeSVG = sF.create(super.inObject).getSVGData();

        String txtBox = sF.create(new InputObject("Rectangle",
                new double[]{super.inObject.getParams()[0], super.inObject.getParams()[1]*.15},
                super.inObject.getColor(),
                super.inObject.getStyle())).getSVGData();
        super.setSvgData(super.shapeSVG+txtBox);
        System.out.println("Activation Expression generateShape");
    }

}
