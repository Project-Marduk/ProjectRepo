package FactoryElements;

import BasicShapes.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author David Lindeman
 * The factory for creating our shapes
 */
public class ShapeFactory implements AbstractElementFactory<Shape>{
    HashMap<String, String> factoryMap = new HashMap<>();
    double[] values; //size will be dynamically set by input array

    /**
     * Sets the values (if any) for the shape
     * @param vals
     */
    public void assignValues(double[] vals){
        values = vals;
    }

    public Shape create(String input){
        // prevents blow up on the backend if no params are given
        if(values.length == 0){
            return null;
        }
        //1D input shapes
        if(input.equals("Square")){
            return new Square(values[0]);
        }
        if(input.equals("Circle")){
            return new Circle(values[0]);
        }
        if(input.equals("Hexagon")){
            return new Hexagon(values[0]);
        }
        if(input.equals("Octagon")){
            return new Octagon(values[0]);
        }
        if(input.equals("EqTriangle")){
            return new EqTriangle(values[0]);
        }
        //2D input shapes
        if(input.equals("Rectangle")) {
            if (values.length != 2) { //prevents blow up for incorrect value entry
                return null;
            }
            return new Rectangle(values[0], values[1]);
        }
        //no matching entry
        return null;
    }

}
