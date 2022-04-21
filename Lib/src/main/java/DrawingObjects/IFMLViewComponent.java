package DrawingObjects;

import FactoryElements.InputObject;
import static DrawingObjects.ShapeSVGFunctions.rectToSVG;
import static DrawingObjects.JavaFXConversion.ShapeJavaFXFunctions.rectToJavaFX;
import javafx.scene.Group;
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

    public IFMLViewComponent(String id, InputObject inObj){
        super(id, inObj);
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
        super.linkedJavaFXObject.getChildren().addAll(rectToJavaFX(super.inObject));
    }
}
