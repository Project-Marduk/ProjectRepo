package FactoryElements;

import BasicShapes.PlainLine;
import FactoryElements.AbstractElementFactory;
import FactoryElements.InputObject;
import FactoryElements.Interfaces.Line;

/**
 * @author David Lindeman
 * The factory for generating line objects
 */
public class LineFactory extends AbstractElementFactory<Line> {
    public Line create(InputObject input){
        Line l = new PlainLine( input.params[0],2,3,4, "");
        return l;
    }
}
