package DrawingBoard;

import FactoryElements.InputObject;
import DrawingObjects.DrawingObject;
import FactoryElements.DrawingObjectFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter @Setter
public class DrawingBoard {
    private final double SIZE_DEFAULT = 1000;
    private inputBoard inputBoard;
    public Integer id;
    public Integer folder_id;

    Map<String, DrawingObject> objects = new HashMap<>();
    DrawingObjectFactory drawingObjectFactory = new DrawingObjectFactory();


    public DrawingBoard(inputBoard d){
        inputBoard = d;
    }

    public inputBoard getDiagram(){
        return getDiagram();
    }

    /**
     * The function adds a Drawing object to the Hashmap and provides in IF
     *
     * @param inObj the input object for the thing you want to make.
     * @return The Drawing object just created
     */
    public DrawingObject addObject(InputObject inObj){
        //TODO the index system is flawed.
        // How does the system reconcile between the client and the server?
        int b = 5;

        String id = inObj.getId().toString();


        DrawingObject d = drawingObjectFactory.create(inObj, id);
        if (d == null){
            System.out.println("INVALID OBJECT, Factory returned Null");
        }
        objects.put(id, d);
        System.out.println("");
        return objects.get(id);
    }

    public void removeObject(String id){
        objects.remove(id);
    }

    public DrawingObject getObject(String id){
        return objects.get(id);
    }

    public Set<String> getIds(){
        return objects.keySet();
    }

    public String getSVGDataFromId(String id){
        return objects.get(id).getSVGData();
    }

    public String returnSVGData(){
        String svgData = "";
        for(String key : objects.keySet()){
            svgData += "\n" + objects.get(key).getSVGData();
            svgData += "\n" + objects.get(key).txtToSVG();
        }

        return "<svg contentScriptType=\"text/ecmascript\" width=\"" + Double.toString(inputBoard.xMax) + "px\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"full \"\n" +
                "    zoomAndPan=\"magnify\" contentStyleType=\"text/css\" height=\"" + Double.toString(inputBoard.yMax) + "px\" preserveAspectRatio=\"xMidYMid meet\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "    version=\"1.0\">"
                + svgData
                + "\n"
                + "</svg>";
    }

}