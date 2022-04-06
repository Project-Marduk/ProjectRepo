import DrawingBoard.DrawingBoard;
import FactoryElements.InputObject;
import DrawingObjects.DrawingObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestDrawingBoard {
    DrawingBoard testDrawBoard;


    @BeforeEach
    void setUp(){
        testDrawBoard = new DrawingBoard(50,50);
    }

    @AfterEach
    void writeToSVG(){
        try {
            File myObj = new File("test.svg");
            FileWriter myWriter = new FileWriter("test.svg");
            myWriter.write(testDrawBoard.returnSVGData());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Test
    void canMakeShape(String shapeName, InputObject shapeInObj){
        testDrawBoard.addObject(shapeInObj);
        String[] keys = testDrawBoard.getIds().toArray(new String[testDrawBoard.getIds().size()]);
        DrawingObject objDW = testDrawBoard.getObject(keys[0]);
        System.out.println(objDW.getInObject().getShapeType());
        System.out.println(objDW.getSVGData());
        assertTrue(objDW.getInObject().getShapeType().equals(shapeName));
    }

    @Test
    void canAddRect(){
        double[] inParams = new double[]{18,20};
        InputObject rectInObj = new InputObject("Rectangle",
                inParams,"000000", "bold", 2, 4.0);
        canMakeShape("Rectangle", rectInObj);
    }

    @Test
    void canAddSquare(){
        double[] inParams = new double[]{16};
        InputObject sqInObj = new InputObject("Square",
                inParams, "000000", "bold", 2, 4);
        canMakeShape("Square", sqInObj);
    }

    @Test
    void canAddCircle(){
        double[] inParams = new double[]{20};
        InputObject circleInObj = new InputObject("Circle",
                inParams, "000000", "bold", 2, 4);
        canMakeShape("Circle", circleInObj);
    }

    @Test
    void canAddParallelogram(){
        double[] inParams = new double[]{5,10};
        InputObject rectInObj = new InputObject("Parallelogram",
                inParams,"000000", "bold", 2, 4.0);
        canMakeShape("Parallelogram", rectInObj);
    }
}
