package DrawingBoard;

import FactoryElements.InputObject;
import IFML.DrawingObject;
import FactoryElements.DrawingObjectFactory;
import lombok.Getter;
import org.javalite.activejdbc.annotations.Table;

import java.util.HashMap;
import java.util.Map;

@Table("IFML_Drawing_Board")
@Getter
public class DrawingBoard {
    Map<String, DrawingObject> objects = new HashMap<>();
    String[] objectTypes = new String[]{
        "IFML_Action",
        "IFML_Activation_Expression",
        "IFML_Container",
        "IFML_Event",
        "IFML_Module",
        "IFML_Parameter",
        "IFML_View_Component",
        "IFML_View_Component_Part",
        "IFML_Line",
        "Line",
        "Wireframe_Object"
    };
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

    public void updateObject(String id, InputObject inObj){
        objects.put(id, drawingObjectFactory.create(inObj, id));
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
