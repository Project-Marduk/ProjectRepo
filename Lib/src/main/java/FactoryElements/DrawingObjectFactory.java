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
            case "IFML Action":
                return new IFMLAction(id, input);
            case "IFML Activation Expression":
                return new IFMLActivationExpression(id, input);
            case "IFML Container":
                return new IFMLContainer(id, input);
            case "IFML Event":
                return new IFMLEvent(id, input);
            case "IFML Module":
                return new IFMLModule(id, input);
            case "IFML Parameter":
                return new IFMLParameter(id, input);
            case "IFML View Component":
                return new IFMLViewComponent(id, input);
            case "IFML View Component Part":
                return new IFMLViewComponentPart(id, input);
            case "IFML Line":
                return new IFMLLine(id, input);
            case "Wireframe Object":
                return new WireframeObject(id, input);
            case "Line":
                return new Line(id, input);
            default:
                return null;

        }
    }
}
