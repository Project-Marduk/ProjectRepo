package IFML.Label;

import BasicShapes.Hexagon;
import FactoryElements.Shape;
import IFML.IFMLObject;

/**
 * @author David Lindeman
 * Utilizes the Shape Factory to create any shape with a text box inside it
 */
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
