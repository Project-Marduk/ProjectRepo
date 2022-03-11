package IFML.Connection;

import FactoryElements.Lines.AbstractLine;
import FactoryElements.Lines.HeadType;
import IFML.IFMLObject;

/**
 * @author David Lindeman
 * Line for UML diagrams
 */

public class IFMLLine extends AbstractLine {
    HeadType lHeadType;
    HeadType rHeadType;

    public IFMLLine(int lX, int lY, int rX, int rY, String lineType) {
        super(lX, lY, rX, rY, lineType);
    }

    public void drawLine(){
        System.out.println("draw");
    }
}