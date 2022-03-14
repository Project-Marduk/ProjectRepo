package FactoryElements;

import BasicShapes.*;
import FactoryElements.AbstractElementFactory;
import FactoryElements.InputObject;
import FactoryElements.Interfaces.Shape;

/**
 * @author David Lindeman
 * The factory for creating our shapes
 */
public class ShapeFactory extends AbstractElementFactory<Shape> {
//    HashMap<String, String> factoryMap = new HashMap<>(); //This would be used for the functional interface
    @Override
    public Shape create(InputObject input){
        // prevents blow up on the backend if no params are given
        if(input.params.length == 0){
            return null;
        }
        //1D input shapes
        if(input.shapeType.equalsIgnoreCase("Square")){
            return new Square(input.params[0]);
        }
        if(input.shapeType.equalsIgnoreCase("Circle")){
            return new Circle(input.params[0]);
        }
        if(input.shapeType.equalsIgnoreCase("Hexagon")){
            return new Hexagon(input.params[0]);
        }
        if(input.shapeType.equalsIgnoreCase("Octagon")){
            return new Octagon(input.params[0]);
        }
        if(input.shapeType.equalsIgnoreCase("EqTriangle")){
            return new EqTriangle(input.params[0]);
        }
        //2D input shapes
        if(input.shapeType.equalsIgnoreCase("Rectangle")) {
            if (input.params.length != 2) { //prevents blow up for incorrect value entry
                return null;
            }
            return new Rectangle(input.params[0], input.params[1]);
        }
        //no matching entry
        return null;
    }

}
