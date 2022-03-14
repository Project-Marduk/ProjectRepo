package IFML;

import FactoryElements.InputObject;
import FactoryElements.ShapeFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * Utilizes the Shape Factory to create any shape with a text box inside it
 */
@Getter @Setter
public class IFMLLabel extends IFMLObject {
    String text;

    public IFMLLabel(String id, String t, InputObject input){
        super(id, input);
        super.inObject.setColor("#B7BDBB");//Hex for light blue grey
        text = t;
    }

    public void generateShape(){
        ShapeFactory sF = new ShapeFactory();
        String svgText = txtToSVG(text);
        super.setSvgData(sF.create(super.inObject).getSVGData() + svgText); //shape will be Hexagon

        System.out.println("IFML Label generate Shape");
    }
}
