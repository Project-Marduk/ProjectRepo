package IFML;

import FactoryElements.InputObject;
import static FactoryElements.ShapeSVGFunctions.rectToSVG;

import lombok.Getter;
import lombok.Setter;
import org.javalite.activejdbc.annotations.Table;

/**
 * @author David Lindeman
 * IFML component, will be initialized as an empty rectangle
 */
@Getter @Setter
@Table("IFML_View_Component")
public class IFMLViewComponent extends DrawingObject {
    String componentName;
    String text;
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    public IFMLViewComponent(String id, InputObject inObj){
        super(id, inObj);
        componentName = "";
    }

    public void generateShape(){
        //TODO: Create a rounded edge rectangle shape
        super.shapeSVG = rectToSVG(super.inObject);
        super.setSvgData(super.shapeSVG + super.txtToSVG(text,
                super.inObject.getXCord() + inObject.getParams()[0]*.5,
                super.inObject.getYCord() - super.inObject.getParams()[1]*.5));
    }
}
