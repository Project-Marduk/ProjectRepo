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


    public DrawingObject create(InputObject input){
//        if(input.shapeType.equalsIgnoreCase("Node")){
//            return new IFMLMail("1");
//        }
        return new IFMLAction("1", input);
    }
}
