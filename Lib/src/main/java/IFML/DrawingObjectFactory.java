package IFML;

import FactoryElements.InputObject;

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
            case "IFMLAction":
                return new IFMLAction(id, input);
            case "IFMLActivationExpression":
                return new IFMLActivationExpression(id, input);
            case "IFMLContainer":
                return new IFMLContainer(id, input);
            case "IFMLEvent":
                return new IFMLEvent(id, input);
            case "IFMLModule":
                return new IFMLModule(id, input);
            case "IFMLParameter":
                return new IFMLParameter(id, input);
            case "IFMLViewComponent":
                return new IFMLViewComponent(id, input);
            case "IFMLViewComponentPart":
                return new IFMLViewComponentPart(id, input);
            default:
                return null;

        }
    }
}
