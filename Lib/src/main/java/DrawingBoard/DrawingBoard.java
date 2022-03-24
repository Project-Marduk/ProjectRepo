package DrawingBoard;

import FactoryElements.InputObject;
import IFML.DrawingObject;
import IFML.DrawingObjectFactory;

import java.util.HashMap;
import java.util.Map;

public class DrawingBoard {
    Map<String, DrawingObject> objects = new HashMap<String, DrawingObject>();
    double xMax;
    double yMax;
    DrawingObjectFactory drawingObjectFactory = new DrawingObjectFactory();
    static int idIndex = 0;

    public DrawingBoard(double xSize, double ySize){
        xMax = xSize;
        yMax = ySize;
    }

    public void addObject(InputObject inObj){
        objects.put(Integer.toString(idIndex), drawingObjectFactory.create(inObj, Integer.toString(idIndex)));
        idIndex++;
    }

    public void removeObject(String id){
        objects.remove(id);
    }


}
