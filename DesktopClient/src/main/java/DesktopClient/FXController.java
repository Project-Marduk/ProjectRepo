/**
 * Desktop Client
 * @Author Project Marduk: Tyler Kelley
 * Software Engineering project
 * Griffith
 */
package DesktopClient;

import DrawingObjects.DrawingObject;
import DrawingObjects.Functions.ShapeJavaFXFunctions;
import DrawingBoard.*;
import DrawingObjects.ShapeTypes;
import FactoryElements.InputObject;
import Server.Connection.*;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class FXController {

    /**
     * FXML files located in the resource folder. This allows for variables to be called instead
     * of path strings in order to create a new scene
     */
    @Getter public static final String Login_FXML = "/Login.fxml";
    @Getter public static final String CreateUser_FXML = "/CreateUser.fxml";
    @Getter public static final String Design_FXML = "/Design.fxml";


    /**
     * Elements for application
     */
    private App app;
    @FXML BorderPane Design;
    @FXML Pane designCenter;
    @FXML TreeView<String> treeViewBasicShapes;
    @FXML TreeItem<String> treeRectangle;
    @FXML TextField widthText;
    @FXML TextField heightText;
    @FXML TextField xCoordText;
    @FXML TextField yCoordText;
    @FXML ChoiceBox borderChoiceBox;
    @FXML ColorPicker colorPicker;

    /**
     * Shapes elements for the application
     */
    @Getter double insertX = 250;
    @Getter double insertY = 150;
    @Getter double stroke = 3;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    private Node selectedNode;
    InputObject shape;
    DrawingObject drawing;

    InputBoard inputBoard;
    InputObject inputObject;
    DrawingBoard testBoard;

    InputObject inputObject1;
    InputObject inputObject3;

    ServerConnection connection;

    LinkedList<Group> thingsToRender;

    /**
     * Default mainStage variable to control the change of scenes
     */
    @Getter public final Stage mainStage = new Stage();

    /**
     * Default constructor
     */
    public FXController(){}

    /**
     * Creates and shows the Main menu where you can select to start, load, or exit.
     *
     */
    public FXController(App application){
        this.app = application;
    }

    /**
     * Handles the exit button that will force a close of the app
     */
    @FXML
    public void handleExitButton(){
        System.exit(0);
    }

    @FXML
    /**
     * Once the app is loaded up, the login page is immediately shown to the user
     */
    public void showLogin() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource(Login_FXML));
        loginLoader.setController(this);

        mainStage.setScene(new Scene(loginLoader.load()));
        mainStage.setMaximized(true);
        mainStage.setTitle("Sign In");
        mainStage.show();

        connection = ServerConnection.instance();
        connection.initialize("7001","7001");
    }

    /**
     * When the user wants to create a new username, the create a new user page will be shown to the user
     * @throws IOException
     */
    @FXML
    public void showCreateUser() throws IOException{
        FXMLLoader userLoader = new FXMLLoader(getClass().getResource(CreateUser_FXML));
        userLoader.setController(this);
        mainStage.hide();
        mainStage.setScene(new Scene(userLoader.load()));
        mainStage.setMaximized(true);
        mainStage.setTitle("Create An Account");
        mainStage.show();

    }

    /**
     * Returns to the main login page either when a user has just created a username, or logs out
     * @throws IOException
     */
    @FXML
    public void returnToLogin() throws IOException{
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource(Login_FXML));
        loginLoader.setController(this);
        mainStage.hide();
        mainStage.setScene(new Scene(loginLoader.load()));
        mainStage.setMaximized(true);
        mainStage.setTitle("Sign In");
        mainStage.show();
    }

    /**
     * Goes to the main design page where the user can start creating projects
     * @throws IOException
     */
    @FXML
    public void goToDesign() throws IOException{

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource(Design_FXML));
        loginLoader.setController(this);

        mainStage.hide();
        mainStage.setScene(new Scene(loginLoader.load()));
        mainStage.setMaximized(true);
        mainStage.setTitle("Design");
        mainStage.show();

        inputBoard = new InputBoard();
        inputBoard.id = "Board1";
        inputBoard.name = "Test";
        inputBoard.xMax = 3000;
        inputBoard.yMax = 3000;
        inputBoard.folderId = 1;

        testBoard = new DrawingBoard(inputBoard);

        treeViewSelector();
    }

    public void testingGroups(){
        //Here is the basic flow of the Data Structures

        // There are 4 structure You care about.
        /*
        1. Input object = portable values of a shape.
        2. Input Board = portable values of a board.
        3. Drawing Board = the thing to make your stuff.
        4. Drawing object = the thing you want to actually use.

        InputObject -> DrawingObject
        InputBoard -> DrawingBoard

         */

        // Here is an Input Board
        inputBoard = new InputBoard();
        inputBoard.id = "Board1";
        inputBoard.name = "Test";
        inputBoard.xMax = 3000;
        inputBoard.yMax = 3000;
        inputBoard.folderId = 1;
        // In the final product you'll retrieve this from the server

        // for testing you can just make them;


        // Now lets plug it into our board.
        // DrawingBoard the central thing that makes you DrawingObjects
        testBoard = new DrawingBoard(inputBoard);

        // Now lets make our Input Objects

        // HERE IS THE Current contsructor:
        // public InputObject(String sType, double[] p, String c, String s, double x, double y, String[] t)
        inputObject1 = new InputObject(ShapeTypes.square.getValue(), new double[]{100, 100},"black","solid",20.0,20.0, new String[]{"Bitches"});
        // OR
        inputObject3 = new InputObject();
        inputObject3.setShapeType(ShapeTypes.circle.getValue());
        inputObject3.setParams(new double[]{100, 200});
        inputObject3.setColor("black");
        inputObject3.setStyle("solid");
        inputObject3.setXCord(60.0);
        inputObject3.setYCord(40.0);

        // IN the final build you will make an
        // input object, send it to the server, and recieve a finalized inputObject.
        // for right now you can just test using your own.
        // this is important to remember tho.


        // Now for the Good part:

        //null pointer error
        //input object isnt null but converting to a drawing object turns it null
        DrawingObject drawing1 = testBoard.addObject(inputObject1);

        System.out.println(drawing1);

        DrawingObject drawing2 = testBoard.addObject(inputObject3);

        //DrawingObjects are now JavaFX Groups & Drawing Objects.
        drawing1.update();// it is now ready to draw to the screen.

        drawing1.getInObject().setYCord(500);
        drawing1.getInObject().setXCord(412);

        drawing1.update(); // the JAVA FX shapes the group is made of have been updated.

        // Works with you functions:
        //makeSelectable(drawing1);
        makeShapeMove(drawing1);

        designCenter.getChildren().add(drawing1);



    }

    /**
     * Generate a square from input object
     */
    @FXML
    public void addSquare(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.square.getValue());
        setShape(shape);
    }

    /**
     * Generate a rectangle from input object
     */
    @FXML
    public void addRectangle(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.rectangle.getValue());
        setShape(shape);
    }

    /**
     * Generate a circle from an input object
     */
    @FXML
    public void addCircle(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.circle.getValue());
        setShape(shape);
    }

    /**
     * Generate a hexagon from an input object
     */
    @FXML
    public void addHexagon(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.hexagon.getValue());
        setShape(shape);
    }

    /**
     * Generate a line from an input object
     */
    @FXML
    public void addLine(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.Line.getValue());
        setShape(shape);
    }

    /**
     * Generate a parallelogram from an input object
     */
    @FXML
    public void addParallelogram(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.parallelogram.getValue());
        setShape(shape);
    }

    /**
     * Generate a action from an input object
     */
    @FXML
    public void addAction(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Action.getValue());
        setShape(shape);
    }

    /**
     * Generate a expression from an input object
     */
    @FXML
    public void addActivationExpression(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Activation_Expression.getValue());
        setShape(shape);
    }

    /**
     * Generate a container from an input object
     */
    @FXML
    public void addContainer(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Container.getValue());
        setShape(shape);
    }

    /**
     * Generate a data flow from an input object
     */
    @FXML
    public void addDataFlow(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Line.getValue());
        setShape(shape);
    }

    /**
     * Generate a event from an input object
     */
    @FXML
    public void addEvent(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Event.getValue());
        setShape(shape);
    }

    /**
     * Generate a module from an input object
     */
    @FXML
    public void addModule(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Module.getValue());
        setShape(shape);
    }

    /**
     * Generate a nav flow from an input object
     */
    @FXML
    public void addNavigationFlow(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Line.getValue());
        setShape(shape);
    }

    /**
     * Generate a object from an input object
     */
    @FXML
    public void addObject(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.Wireframe_Object.getValue());
        setShape(shape);
    }

    /**
     * Generate a parameter from an input object
     */
    @FXML
    public void addParameter(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_Parameter.getValue());
        setShape(shape);
    }

    /**
     * Generate a view component from an input object
     */
    @FXML
    public void addViewComponent(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_View_Component.getValue());
        setShape(shape);
    }

    /**
     * Generate a view component part from an input object
     */
    @FXML
    public void addViewComponentPart(){
        shape = new InputObject();
        shape.setShapeType(ShapeTypes.IFML_View_Component_Part.getValue());
        setShape(shape);
    }

    /**
     * This method shows off what the project functionality was aiming for in the presentation
     * but svg doesn't allow for these specific features
     */
    @FXML
    public void whatWeWanted(){
        Rectangle rectangle = new Rectangle(Double.parseDouble(widthText.getText()), Double.parseDouble(heightText.getText()));
        rectangle.setFill(colorPicker.getValue());
        makeSelectable(rectangle);
        designCenter.getChildren().add(rectangle);
    }

    /**
     * Convert an input object created into a javafx shape so the design center can insert
     * @param s input object
     */
    public void setShape(InputObject s){
        try{
            double w = Double.parseDouble(widthText.getText());
            double h = Double.parseDouble(heightText.getText());
            s.setParams(new double[]{w,h});
        }
        catch (NumberFormatException nfe){
            s.setParams(new double[]{200,200});
        }

        try {
            double x = Double.parseDouble(xCoordText.getText());
            double y = Double.parseDouble(yCoordText.getText());
            s.setXCord(x);
            s.setYCord(y);
        }
        catch (NumberFormatException nfe){
            s.setXCord(100);
            s.setYCord(100);
        }

        s.setColor(colorPicker.getValue().toString());
        s.setStyle(borderChoiceBox.getValue().toString());


        drawing = testBoard.addObject(s);
        drawing.update();
        drawing.getInObject().setYCord(200);
        drawing.getInObject().setXCord(400);
        makeShapeMove(drawing);
        designCenter.getChildren().add(drawing);


    }

    /**
     * Save the file to a users folder
     */
    @FXML
    public void saveFile(){
        connection.saveDrawingBoard(inputBoard);
    }

    /**
     * Open a file from a users folder
     */
    @FXML
    public void openFile(){
        connection.loadDrawingBoard("inputBoardToLoad");
    }

    /**
     * Export a design to a svg
     */
    @FXML
    public void exportSVG(){
        connection.renderSVG(inputBoard);
    }

    /**
     * Export a design to a png
     */
    @FXML
    public void exportPNG(){
        connection.renderPNG(inputBoard);
    }

    /**
     * Make a shape be able to move around the screen with the event handlers below
     * @param shape
     */
    public void makeShapeMove(Node shape){
        shape.setCursor(Cursor.MOVE);
        shape.setTranslateX(insertX);
        shape.setTranslateY(insertY);
        shape.setOnMousePressed(shapeOnMousePressedEventHandler);
        shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
    }

    /**
     * Open source code from http://java-buddy.blogspot.com/2013/07/javafx-drag-and-move-something.html
     * This allows for the java shapes to be moved around freely inside of the design center
     **/
    EventHandler<MouseEvent> shapeOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Node)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Node)(t.getSource())).getTranslateY();

                    if(t.getButton() == MouseButton.SECONDARY){
                        designCenter.getChildren().remove(t.getSource());
                    }

                }
            };

    EventHandler<MouseEvent> shapeOnMouseDraggedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    double offsetX = t.getSceneX() - orgSceneX;
                    double offsetY = t.getSceneY() - orgSceneY;
                    double newTranslateX = orgTranslateX + offsetX;
                    double newTranslateY = orgTranslateY + offsetY;

                    ((Node)(t.getSource())).setTranslateX(newTranslateX);
                    ((Node)(t.getSource())).setTranslateY(newTranslateY);
                    ((Node)(t.getSource())).toFront();
                }
            };

    /**
     * Allows for the tree view to be selectable and insert the shapes wanted
     */
    public void treeViewSelector() {
        treeViewBasicShapes.setCellFactory(tree -> {
            TreeCell<String> cell = new TreeCell<String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (empty) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
            /**
             * Determines which cell is clicked on and inserts the designated shape
             */
            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    TreeItem<String> treeItem = cell.getTreeItem();
                    // do whatever you need with the treeItem
                    System.out.println(treeItem.getValue());
                    if (treeItem.getValue().equals("Rectangle")) {addRectangle();}
                    if (treeItem.getValue().equals("Circle")) {addCircle();}
                    if (treeItem.getValue().equals("Hexagon")) {addHexagon();}
                    if (treeItem.getValue().equals("Line")) {addLine();}
                    if (treeItem.getValue().equals("Parallelogram")) {addParallelogram();}
                    if (treeItem.getValue().equals("Square")) {addSquare();}
                    if (treeItem.getValue().equals("Action")) {addAction();}
                    if (treeItem.getValue().equals("Activation Expression")) {addActivationExpression();}
                    if (treeItem.getValue().equals("Container")) {addContainer();}
                    if (treeItem.getValue().equals("Data Flow")) {addDataFlow();}
                    if (treeItem.getValue().equals("Event")) {addEvent();}
                    if (treeItem.getValue().equals("Module")) {addModule();}
                    if (treeItem.getValue().equals("Navigation Flow")) {addNavigationFlow();}
                    if (treeItem.getValue().equals("Object")) {addObject();}
                    if (treeItem.getValue().equals("Parameter")) {addParameter();}
                    if (treeItem.getValue().equals("View Component")) {addViewComponent();}
                    if (treeItem.getValue().equals("View Component Part")) {addViewComponentPart();}
                }
            });
            return cell ;
        });
    }

    /**
     * make selectable allows for basic shapes inside of javafx to be dragged and resized such as a rectangle.
     * Sadly this is hella bugged with our svg shape paths
     * @param nodes
     */
    private void makeSelectable(Node... nodes) {
        designCenter.setOnMouseClicked(event -> {
            final Parent parentNode = ((Node) event.getTarget()).getParent();
            if (selectedNode != null && !(parentNode instanceof ResizingControl)) {
                designCenter.getChildren().removeIf(candidate -> candidate instanceof ResizingControl);
                selectedNode = null;
            }
        });
        for (Node node: nodes) {
            node.setOnMouseClicked(event -> {
                if (selectedNode != node) {
                    designCenter.getChildren().removeIf(candidate -> candidate instanceof ResizingControl);
                    selectedNode = node;

                    node.toFront();
                    ResizingControl resizingControl = new ResizingControl(node);
                    designCenter.getChildren().add(resizingControl);
                }

                event.consume();
            });
        }
    }
}

/**
 * Shapes are now going to be resizable groups inside of the java fx design page
 */
class ResizingControl extends Group {
    private Node targetNode = null;
    private final Rectangle boundary = new Rectangle();

    private Anchor topLeft = new Anchor(Color.GREY, true, true, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.NW_RESIZE);
        double newWidth = boundary.getWidth() - (newX - oldX);
        if (newWidth > 0) {
            boundary.setX(newX);
            boundary.setWidth(newWidth);
        }
        double newHeight = boundary.getHeight() - (newY - oldY);
        if (newHeight > 0) {
            boundary.setY(newY);
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor topCenter = new Anchor(Color.GREY, false, true, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.N_RESIZE);
        double newHeight = boundary.getHeight() - (newY - oldY);
        if (newHeight > 0) {
            boundary.setY(newY);
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor topRight = new Anchor(Color.GREY, true, true, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.NE_RESIZE);
        double newWidth = boundary.getWidth() + (newX - oldX);
        if (newWidth > 0) {
            boundary.setWidth(newWidth);
        }
        double newHeight = boundary.getHeight() - (newY - oldY);
        if (newHeight > 0) {
            boundary.setY(newY);
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor rightCenter = new Anchor(Color.GREY, true, false, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.E_RESIZE);
        double newWidth = boundary.getWidth() + (newX - oldX);
        if (newWidth > 0) {
            boundary.setWidth(newWidth);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor bottomRight = new Anchor(Color.GREY, true, true, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.SE_RESIZE);
        double newWidth = boundary.getWidth() + (newX - oldX);
        if (newWidth > 0) {
            boundary.setWidth(newWidth);
        }
        double newHeight = boundary.getHeight() + (newY - oldY);
        if (newHeight > 0) {
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor bottomCenter = new Anchor(Color.GREY, false, true, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.S_RESIZE);
        double newHeight = boundary.getHeight() + (newY - oldY);
        if (newHeight > 0) {
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor bottomLeft = new Anchor(Color.GREY, true, true, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.SW_RESIZE);
        double newWidth = boundary.getWidth() - (newX - oldX);
        if (newWidth > 0) {
            boundary.setX(newX);
            boundary.setWidth(newWidth);
        }
        double newHeight = boundary.getHeight() + (newY - oldY);
        if (newHeight > 0) {
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor leftCenter = new Anchor(Color.GREY, true, false, (oldX, oldY, newX, newY) -> {
        setCursor(Cursor.W_RESIZE);
        double newWidth = boundary.getWidth() - (newX - oldX);
        if (newWidth > 0) {
            boundary.setX(newX);
            boundary.setWidth(newWidth);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    /**
     * Attach the resizing features
     * @param targetNode
     */
    ResizingControl(Node targetNode) {
        this.targetNode = targetNode;

        attachBoundingRectangle(targetNode);
        attachAnchors();

        boundary.toBack();
    }

    /**
     * Attach the bounds and boundary style around the target shape
     * @param node
     */
    private void attachBoundingRectangle(Node node) {
        Bounds bounds = node.getBoundsInParent();

        boundary.setStyle(
                "-fx-stroke: black; " +
                        "-fx-stroke-width: 2px; " +
                        "-fx-stroke-dash-array: 12 2 4 2; " +
                        "-fx-stroke-dash-offset: 6; " +
                        "-fx-stroke-line-cap: butt; " +
                        "-fx-fill: rgba(255, 228, 118, .5);"
        );

        boundary.setX(bounds.getMinX());
        boundary.setY(bounds.getMinY());
        boundary.setWidth(bounds.getWidth());
        boundary.setHeight(bounds.getHeight());

        Util.makeDraggable(boundary, (oldX, oldY, newX, newY) -> {
            updateAnchorPositions();

            relocateTargetNode(newX, newY);
        });

        getChildren().add(boundary);
    }

    /**
     * Relocate the shape so its movable
     * @param newX
     * @param newY
     */
    private void relocateTargetNode(double newX, double newY) {
        if (targetNode instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) targetNode;
            ellipse.setCenterX(newX + ellipse.getRadiusX());
            ellipse.setCenterY(newY + ellipse.getRadiusY());
        } else if (targetNode instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) targetNode;
            rectangle.setX(newX);
            rectangle.setY(newY);
        }
    }

    /**
     * Resize the shape that the user wants to select
     */
    private void resizeTargetNode() {
        if (targetNode instanceof Ellipse) {
            Ellipse ellipse = (Ellipse) targetNode;
            ellipse.setRadiusX(boundary.getWidth() / 2);
            ellipse.setRadiusY(boundary.getHeight() / 2);

            relocateTargetNode(boundary.getX(), boundary.getY());
        } else if (targetNode instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) targetNode;
            rectangle.setWidth(boundary.getWidth());
            rectangle.setHeight(boundary.getHeight());

            relocateTargetNode(boundary.getX(), boundary.getY());
        }
    }

    /**
     * Add the circle anchors on the boundary around the shape
     */
    private void attachAnchors() {
        updateAnchorPositions();

        getChildren().addAll(
                topLeft,
                topCenter,
                topRight,
                rightCenter,
                bottomRight,
                bottomCenter,
                bottomLeft,
                leftCenter
        );
    }

    /**
     * Update the circle anchors on the boundary of the shape
     */
    private void updateAnchorPositions() {
        topLeft.setCenterX(boundary.getX());
        topLeft.setCenterY(boundary.getY());
        topCenter.setCenterX(boundary.getX() + boundary.getWidth() / 2);
        topCenter.setCenterY(boundary.getY());
        topRight.setCenterX(boundary.getX() + boundary.getWidth());
        topRight.setCenterY(boundary.getY());
        rightCenter.setCenterX(boundary.getX() + boundary.getWidth());
        rightCenter.setCenterY(boundary.getY() + boundary.getHeight() / 2);
        bottomRight.setCenterX(boundary.getX() + boundary.getWidth());
        bottomRight.setCenterY(boundary.getY() + boundary.getHeight());
        bottomCenter.setCenterX(boundary.getX() + boundary.getWidth() / 2);
        bottomCenter.setCenterY(boundary.getY() + boundary.getHeight());
        bottomLeft.setCenterX(boundary.getX());
        bottomLeft.setCenterY(boundary.getY() + boundary.getHeight());
        leftCenter.setCenterX(boundary.getX());
        leftCenter.setCenterY(boundary.getY() + boundary.getHeight() / 2);
    }
}

/**
 * Handles the dragging of the border around the shape desired to resize
 */
interface DragHandler {
    void handle(double oldX, double oldY, double newX, double newY);
}

/**
 *  A draggable anchor displayed around a point.
 */
class Anchor extends Circle {
    Anchor(Color color, boolean canDragX, boolean canDragY, DragHandler dragHandler) {
        super(0, 0, 5);
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);

        Util.enableDrag(this, canDragX, canDragY, dragHandler);
    }
}

// Utilities with x y width height etc. that all need to be updated on drag eventes
class Util {
    // make a targetNode movable by dragging it around with the mouse.
    static void enableDrag(Circle node, boolean canDragX, boolean canDragY, DragHandler dragHandler) {
        final Delta dragDelta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            // record a delta distance for the drag and drop operation.
            dragDelta.x = node.getCenterX() - mouseEvent.getX();
            dragDelta.y = node.getCenterY() - mouseEvent.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });
        node.setOnMouseReleased(mouseEvent -> {
            node.getScene().setCursor(Cursor.HAND);
        });
        node.setOnMouseDragged(mouseEvent -> {
            double oldX = node.getCenterX();
            double oldY = node.getCenterY();

            double newX = mouseEvent.getX() + dragDelta.x;
            if (canDragX && newX > 0 && newX < node.getScene().getWidth()) {
                node.setCenterX(newX);
            }

            double newY = mouseEvent.getY() + dragDelta.y;
            if (canDragY && newY > 0 && newY < node.getScene().getHeight()) {
                node.setCenterY(newY);
            }

            newX = node.getCenterX();
            newY = node.getCenterY();

            if (dragHandler != null && (newX != oldX || newY != oldY)) {
                dragHandler.handle(oldX, oldY, newX, newY);
            }
        });
        node.setOnMouseEntered(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.HAND);
            }
        });
        node.setOnMouseExited(mouseEvent -> {
            if (!mouseEvent.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
        });
    }

    // make a targetNode movable by dragging it around with the mouse.
    static void makeDraggable(Rectangle node, DragHandler dragHandler) {
        final Delta dragDelta = new Delta();

        node.setOnMouseEntered(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.HAND);
            }
        });
        node.setOnMouseExited(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        node.setOnMousePressed(me -> {
            if (me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
            dragDelta.x = me.getX() - node.getX();
            dragDelta.y = me.getY() - node.getY();
            node.getScene().setCursor(Cursor.MOVE);
        });
        node.setOnMouseReleased(me -> {
            if (!me.isPrimaryButtonDown()) {
                node.getScene().setCursor(Cursor.DEFAULT);
            }
        });
        node.setOnMouseDragged(me -> {
            double oldX = node.getX();
            double oldY = node.getY();

            node.setX(me.getX() - dragDelta.x);
            node.setY(me.getY() - dragDelta.y);

            double newX = node.getX();
            double newY = node.getY();

            if (dragHandler != null && (newX != oldX || newY != oldY)) {
                dragHandler.handle(oldX, oldY, newX, newY);
            }
        });
    }

    // records relative x and y co-ordinates.
    private static class Delta {
        double x, y;
    }

}
