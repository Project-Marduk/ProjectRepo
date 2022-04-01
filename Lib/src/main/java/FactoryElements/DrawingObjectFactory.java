package FactoryElements;

import FactoryElements.InputObject;
import IFML.*;
import Wireframe.Line;
import Wireframe.WireframeObject;

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
        switch (input.getShapeType()) {
            case "IFML_Action":
                return new IFMLAction(id, input);
            case "IFML_Activation Expression":
                return new IFMLActivationExpression(id, input);
            case "IFML_Container":
                return new IFMLContainer(id, input);
            case "IFML_Event":
                return new IFMLEvent(id, input);
            case "IFML_Module":
                return new IFMLModule(id, input);
            case "IFML_Parameter":
                return new IFMLParameter(id, input);
            case "IFML_View_Component":
                return new IFMLViewComponent(id, input);
            case "IFML_View_Component_Part":
                return new IFMLViewComponentPart(id, input);
            case "IFML_Line":
                return new IFMLLine(id, input);
            case "Wireframe_Object":
                return new WireframeObject(id, input);
            case "Line":
                return new Line(id, input);
            default:
                return null;

        }
    }
}
