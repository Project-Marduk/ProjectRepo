package FactoryElements;

import DrawingObjects.*;
import DrawingObjects.Line;
import DrawingObjects.WireframeObject;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DrawingObjectFactory {
    //Text for Container, for now we will use a Map if space becomes an issue we can move to arrays with magic numbers
    Map<String, String> containerHeaders = Stream.of(new String[][] {
            { "View" , "View Container" },
            { "XOR" , "XOR View Container" },
            { "Landmark" , "Landmark View Container"},
            { "Default" , "Default View Container"}
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));


    public DrawingObject create(InputObject input, String id){
        String shapeIdHandle = "";
        if(input.getShapeType().equals("Rectangle") ||
                input.getShapeType().equals("Square") ||
                input.getShapeType().equals("Circle") ||
                input.getShapeType().equals("Hexagon") ||
                input.getShapeType().equals("Parallelogram")){
            shapeIdHandle = "Wireframe_Object";
        }
        else{
            shapeIdHandle = input.getShapeType();
        }

        DrawingObject d;
        switch (shapeIdHandle) {
            case "IFML_Action":
                d = new IFMLAction();
                d.initialize(id, input);
                break;
            case "IFML_Activation_Expression":
                d = new IFMLActivationExpression();
                d.initialize(id, input);
                break;
            case "IFML_Container":
                d = new IFMLContainer();
                d.initialize(id, input);
                break;
            case "IFML_Event":
                d = new IFMLEvent();
                d.initialize(id, input);
                break;
            case "IFML_Module":
                d = new IFMLModule();
                d.initialize(id, input);
                break;
            case "IFML_Parameter":
                d = new IFMLParameterActivationFunction();
                d.initialize(id, input);
                break;
            case "IFML_View_Component":
                d = new IFMLViewComponent();
                d.initialize(id, input);
                break;
            case "IFML_View_Component_Part":
                d = new IFMLViewComponentPart();
                d.initialize(id, input);
                break;
            case "IFML_Line":
                d = new IFMLLine();
                d.initialize(id, input);
                break;
            case "Wireframe_Object":
                d = new WireframeObject();
                d.initialize(id, input);
                break;
            case "Line":
                d = new Line();
                d.initialize(id, input);
                break;
            default:
                d = null;
        }
        return d;
    }
}
