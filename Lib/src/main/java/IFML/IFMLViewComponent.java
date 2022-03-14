package IFML;

import FactoryElements.InputObject;
import FactoryElements.ShapeFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * IFML component, will be initialized as an empty rectangle
 */
@Getter @Setter
public class IFMLViewComponent extends IFMLObject {
    String componentName;
    String text;
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    public IFMLViewComponent(String id, InputObject inObj){
        super(id, inObj);
        componentName = "";
    }

    public void generateShape(){
        //TODO: Create a rounded edge rectangle shape
        ShapeFactory sF = new ShapeFactory();
        super.shapeSVG = sF.create(super.inObject).getSVGData();
        super.setSvgData(super.shapeSVG + super.txtToSVG(text));
    }
}
