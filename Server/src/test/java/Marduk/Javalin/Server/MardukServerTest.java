package Marduk.Javalin.Server;

import FactoryElements.InputObject;
import org.junit.jupiter.api.Test;

public class MardukServerTest {

    InputObject makeOneDInputObject(String objName, double dim, double x, double y){
        double[] inParams = new double[]{dim};
        InputObject inObj = new InputObject(objName, inParams, "000000", "bold", x, y, new String[]{""});
        return inObj;
    }

    InputObject makeTwoDInputObject(String objName, double dim1, double dim2, double x, double y){
        double[] inParams = new double[]{dim1, dim2};
        InputObject inObj = new InputObject(objName, inParams, "000000", "bold", x, y, new String[]{""});
        return inObj;
    }

    @Test
    void testCreateDrawingObject(){
        InputObject rectInObj = makeTwoDInputObject("Rectangle", 100, 50, 100, 100);
        String inObjJSON = rectInObj.toJSON();



    }
}
