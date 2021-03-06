package DrawingObjects;

import FactoryElements.InputObject;

import static DrawingObjects.Functions.ShapeJavaFXFunctions.hexagonToJavaFX;
import static DrawingObjects.Functions.ShapeSVGFunctions.hexagonToSvg;

/**
 * @author David Lindeman
 * IFML Action,
 * Has 1 text box
 * InputObject params have 1 dim
 */
//@Table("IFML_Action")
public class IFMLAction extends DrawingObject {
    String componentName;
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    public IFMLAction(InputObject inObj){
        super(inObj);
        componentName = "";
        super.setTextBoxes(new TextBox[]{
                new TextBox("",
                        super.inObject.getXCord() + inObject.getParams()[0]*.5,
                        super.inObject.getYCord() + super.inObject.getParams()[0]*.5)
        });
    }

    public String generateShape(){
        return hexagonToSvg(super.inObject);
    }

    @Override
    public void generateJavaFXGroup() {
        getChildren().addAll(hexagonToJavaFX(super.inObject));
    }
}
