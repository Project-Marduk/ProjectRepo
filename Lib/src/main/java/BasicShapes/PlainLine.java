package BasicShapes;

import FactoryElements.Lines.AbstractLine;

/**
 * @author David Lindeman
 * Plain line
 * Can be used to compose complex objects
 */
public class PlainLine extends AbstractLine {
    public PlainLine(double lX, double lY, double rX, double rY, String lStyle) {
        super(lX, lY, rX, rY, lStyle);
    }

    public void drawLine(){
        System.out.println("draw");
    }
}