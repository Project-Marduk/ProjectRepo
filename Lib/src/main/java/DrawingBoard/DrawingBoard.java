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
    String svgHead = "<svg contentScriptType=\"text/ecmascript\" width=\"600.0px\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"full\"\n" +
            "    zoomAndPan=\"magnify\" contentStyleType=\"text/css\" height=\"300.0px\" preserveAspectRatio=\"xMidYMid meet\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
            "    version=\"1.0\">";
    String svgTail;

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

    public String returnSVGData(){
        String svgData = "";
        for(String key : objects.keySet()){
            svgData += "\n" + objects.get(key).getSVGData();
        }

        return "<svg contentScriptType=\"text/ecmascript\" width=\"" + Double.toString(xMax) + "px\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"full\"\n" +
                "    zoomAndPan=\"magnify\" contentStyleType=\"text/css\" height=\"" + Double.toString(yMax) + "px\" preserveAspectRatio=\"xMidYMid meet\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "    version=\"1.0\">"
                + svgData
                + "\n"
                + "</svg>";
    }


}
