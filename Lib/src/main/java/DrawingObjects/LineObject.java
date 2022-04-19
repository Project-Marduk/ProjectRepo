package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class LineObject extends DrawingObject{
    double secondXCord;
    double secondYCord;
    boolean headIsLeft;
    boolean headIsVert;
    String headSVG;

    @Override
    public void initialize(String id, InputObject inObj){
        super.initialize(id, inObj);
        secondXCord = inObj.getParams()[0];
        secondYCord = inObj.getParams()[1];
        headIsLeft = true;
        headIsVert = true;
        headSVG = "";
    }
}
