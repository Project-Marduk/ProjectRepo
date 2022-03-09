package UML;

import FactoryElements.Lines.AbstractLine;
import FactoryElements.Lines.HeadType;

/**
 * @author David Lindeman
 * Line for UML diagrams
 */

public class UMLLine extends AbstractLine {
    HeadType lHeadType;
    HeadType rHeadType;

    public UMLLine(int lX, int lY, int rX, int rY) {
        super(lX, lY, rX, rY);

    }

    public void drawLine(){
        System.out.println("draw");
    }
}