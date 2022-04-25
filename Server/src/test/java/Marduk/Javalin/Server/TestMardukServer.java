package Marduk.Javalin.Server;

import FactoryElements.InputObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class TestMardukServer {
    MardukServer testMS;

    @BeforeEach
    public void start(){
        testMS = new MardukServer();
        testMS.start();
    }


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

//    @Test
//    void validateDBConnection(){
//
//    }

    //{"parent_id":1,"shapeType":"Rectangle","xCord":100.0,"yCord":100.0,"params":[100.0,50.0],"color":"000000","style":"bold","fill":"#FFFFFF","text":[""]}
    @Test
    void testCreateDrawingObject(){



        InputObject rectInObj = makeTwoDInputObject("Rectangle", 100, 50, 100, 100);
        String inObjJSON = rectInObj.toJSON();



        System.out.println(inObjJSON);
        assertTrue(true);
    }

    //JSON string for testing
}
