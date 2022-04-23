package DrawingObjects;

import FactoryElements.InputObject;
import static DrawingObjects.Functions.ShapeSVGFunctions.rectToSVG;
import static DrawingObjects.Functions.ShapeJavaFXFunctions.rectToJavaFX;

import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * IFML component, will be initialized as an empty rectangle
 * InputObject params have 1 dim
 * Has 1 text box
 */
@Getter @Setter
//@Table("IFML_View_Component")
public class IFMLViewComponent extends DrawingObject {
    String fillColor = "#B7BDBB"; //Hex for light blue grey

    public IFMLViewComponent(InputObject inObj){
        super(inObj);
        super.setTextBoxes(new TextBox[]{
            new TextBox("",
                    super.inObject.getXCord() + inObject.getParams()[0]*.5,
                    super.inObject.getYCord() + super.inObject.getParams()[1]*.5)
        });
    }

    public String generateShape(){
        //TODO: Create a rounded edge rectangle shape
        return rectToSVG(super.inObject);
    }

    @Override
    public void generateJavaFXGroup() {
        getChildren().addAll(rectToJavaFX(super.inObject));
        addTextBoxesToJavaFXGroup();
    }
}
