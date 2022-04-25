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

    private int indexes = 150000;
    private InputBoard inputBoard;
    public Integer id;
    public Integer folder_id;

    Map<String, DrawingObject> objects;
    DrawingObjectFactory drawingObjectFactory;


    public DrawingBoard(InputBoard in){
        inputBoard = in;
        objects = new HashMap<>();
        drawingObjectFactory = new DrawingObjectFactory();
        for (InputObject io : inputBoard.getInputObjectsList()){
            DrawingObject drawing = drawingObjectFactory.create(io);
            String Id = String.valueOf(drawing.getInObject().getId());
            objects.put(Id, drawing);
        }
    }

    /**
     * Refreshes the inputBoard and gets it.
     * @return the filled out InputBoard
     */
    public InputBoard getInputBoard(){
        inputBoard.getInputObjectsList().clear();
        for (DrawingObject d : objects.values()){
            inputBoard.getInputObjectsList().add(d.getInObject());
        }
        return inputBoard;
    }

    /**
     * The function adds a Drawing object to the Hashmap and provides in IF
     *
     * @param inObj the input object for the thing you want to make.
     * @return The Drawing object just created
     */
    public DrawingObject addObject(InputObject inObj){
        DrawingObject d = drawingObjectFactory.create(inObj);
        objects.put(String.valueOf(d.getInObject().getId()), d);
        return d;
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