package BasicShapes;

import FactoryElements.Lines.AbstractLine;

/**
 * @author David Lindeman
 * Plain line
 * Can be used to compose complex objects
 */
public class PlainLine extends AbstractLine {
    public PlainLine(int lX, int lY, int rX, int rY) {
        super(lX, lY, rX, rY);
    }

    public void drawLine(){
        System.out.println("draw");
    }
}