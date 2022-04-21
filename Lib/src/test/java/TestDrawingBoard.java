import DrawingBoard.DrawingBoard;
import FactoryElements.InputObject;
import DrawingObjects.DrawingObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


public class TestDrawingBoard {
    DrawingBoard testDrawBoard;

    /**
     * @author David Lindeman
     * @param objName
     * @param dim
     * @param x
     * @param y
     * @return
     * Boiler plate code to reduce the amount of repeated code written in each test
     */
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

    void canMakeShape(String shapeName, InputObject shapeInObj){
        testDrawBoard.addObject(shapeInObj);
        String[] keys = testDrawBoard.getIds().toArray(new String[testDrawBoard.getIds().size()]);
        DrawingObject objDW = testDrawBoard.getObject(keys[0]);
//        System.out.println(objDW.getInObject().getShapeType());
//        System.out.println(objDW.getSVGData());
        assertTrue(objDW.getInObject().getShapeType().equals(shapeName));
    }

    @BeforeEach
    void setUp(){

        testDrawBoard = new DrawingBoard(1000,1000);

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
    void canAddRect(){
        InputObject rectInObj = makeTwoDInputObject("Rectangle", 100, 50, 100, 100);
        canMakeShape("Rectangle", rectInObj);
    }

    @Test
    void canAddSquare(){
        InputObject sqInObj = makeOneDInputObject("Square", 100, 100, 100);
        canMakeShape("Square", sqInObj);
    }

    @Test
    void canAddCircle(){
        InputObject circleInObj = makeOneDInputObject("Circle", 100, 100, 100);
        canMakeShape("Circle", circleInObj);
    }

    @Test
    void canAddHexagon(){
        InputObject hexInObj = makeOneDInputObject("Hexagon", 40, 100, 100);
        canMakeShape("Hexagon", hexInObj);
    }

    @Test
    void canAddParallelogram(){
        InputObject rectInObj = makeTwoDInputObject("Parallelogram", 100, 50, 100, 100);
        canMakeShape("Parallelogram", rectInObj);
    }

    @Test
    void canAddIFMLAction(){
        InputObject ifmlActionObj = makeOneDInputObject("IFML_Action", 40, 100, 100);
        canMakeShape("IFML_Action", ifmlActionObj);
    }

    @Test
    void canAddIFMLActivationExpression(){
        InputObject ifmlActExpObj = makeTwoDInputObject("IFML_Activation_Expression", 100, 50, 100, 100);
        canMakeShape("IFML_Activation_Expression", ifmlActExpObj);
    }

    @Test
    void canAddIFMLContainer(){
        InputObject ifmlCont = makeTwoDInputObject("IFML_Container", 100, 50, 100, 100);
        canMakeShape("IFML_Container", ifmlCont);
    }

    @Test
    void canAddIFMLEvent(){
        InputObject ifmlEvent = makeOneDInputObject("IFML_Event", 40, 100, 100);
        canMakeShape("IFML_Event", ifmlEvent);
    }

    @Test
    void canAddIFMLLine(){
        //TODO: Doesnt correctly align triangle with line
        InputObject ifmlLine = makeTwoDInputObject("IFML_Line", 40, 40, 100, 100);
        canMakeShape("IFML_Line", ifmlLine);
    }

    @Test
    void canAddModule(){
        InputObject ifmlModule = makeTwoDInputObject("IFML_Module", 40, 40, 100, 100);
        canMakeShape("IFML_Module", ifmlModule);
    }

    @Test
    void canAddIFMLParameter(){
        InputObject ifmlParameter = makeTwoDInputObject("IFML_Parameter", 40, 40, 100, 100);
        canMakeShape("IFML_Parameter", ifmlParameter);
    }

    @Test
    void canAddIFMLViewComponent(){
        InputObject ifmlViewComponent = makeTwoDInputObject("IFML_View_Component", 60, 40, 100, 100);
        canMakeShape("IFML_View_Component", ifmlViewComponent);
    }

    @Test
    void canAddIFMLViewComponentPart(){
        InputObject ifmlViewComponent = makeTwoDInputObject("IFML_View_Component_Part", 100, 50, 100, 100);
        canMakeShape("IFML_View_Component_Part", ifmlViewComponent);
    }

//    @Test
//    void canUpdateIFMLViewComponentPart(){
//        InputObject ifmlViewComponent = makeTwoDInputObject("IFML_View_Component_Part", 100, 50, 100, 100);
//        testDrawBoard.addObject(ifmlViewComponent);
//        String[] keys = testDrawBoard.getIds().toArray(new String[testDrawBoard.getIds().size()]);
//        DrawingObject objDW = testDrawBoard.getObject(keys[0]);
////        System.out.println(objDW.getInObject().getShapeType());
////        System.out.println(objDW.getSVGData());
//        assertTrue(objDW.getInObject().getShapeType().equals(shapeName));
//    }

    @Test
    void canAddLine(){

    }
}
