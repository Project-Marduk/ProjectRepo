package FactoryElements.Lines;

import BasicShapes.PlainLine;
import FactoryElements.AbstractElementFactory;

/**
 * @author David Lindeman
 * The factory for generating line objects
 */

public class LineFactory implements AbstractElementFactory<Line> {
    public Line create(String l){
        Line g = new PlainLine(1,2,3,4);
        return g;
    }
}
