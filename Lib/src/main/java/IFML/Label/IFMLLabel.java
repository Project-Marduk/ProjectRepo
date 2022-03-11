package IFML.Label;

import BasicShapes.Hexagon;
import FactoryElements.Shape;
import IFML.IFMLObject;

public abstract class IFMLLabel extends IFMLObject {
    String text;
    public IFMLLabel(String id, String t){
        super(id);
        text = t;
    }

    public void editText(String inputText){
        text = inputText;
    }
}
