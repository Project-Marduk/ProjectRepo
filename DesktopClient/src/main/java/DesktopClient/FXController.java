/**
 * Desktop Client
 * @Author Project Marduk: Tyler Kelley
 * Software Engineering project
 * Griffith
 */
package DesktopClient;

import FactoryElements.*;
import DrawingBoard.*;
import DrawingObjects.JavaFXConversion.*;
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

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class FXController {

    DrawingBoard testBoard = new DrawingBoard(3000,3000);

    InputObject inputObject = new InputObject(ShapeTypes.square.getValue(), new double[]{100, 100},"black","solid",20.0,20.0);
    InputObject inputObject2 = new InputObject(ShapeTypes.rectangle.getValue(), new double[]{100, 400},"black","solid",200.0,20.0);
    InputObject inputObject3 = new InputObject(ShapeTypes.circle.getValue(), new double[]{100, 200},"red","solid",60.0,40.0);
    InputObject inputObject4 = new InputObject(ShapeTypes.hexagon.getValue(), new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject5 = new InputObject(ShapeTypes.Line.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);

    InputObject inputObject6 = new InputObject(ShapeTypes.parallelogram.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject7 = new InputObject(ShapeTypes.IFML_Action.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    //doesnt work
    InputObject inputObject8 = new InputObject(ShapeTypes.IFML_Activation_Expression.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject9 = new InputObject(ShapeTypes.IFML_Container.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject10 = new InputObject(ShapeTypes.IFML_Line.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject11 = new InputObject(ShapeTypes.IFML_Event.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject12 = new InputObject(ShapeTypes.IFML_Module.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject13 = new InputObject(ShapeTypes.IFML_Line.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject14 = new InputObject(ShapeTypes.Wireframe_Object.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    //doesnt work
    InputObject inputObject15 = new InputObject(ShapeTypes.IFML_Parameter.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject16 = new InputObject(ShapeTypes.IFML_View_Component.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);
    InputObject inputObject17 = new InputObject(ShapeTypes.IFML_View_Component_Part.getValue(),new double[]{100,200}, "black","solid",50.0,50.0);




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
    @FXML ColorPicker colorPicker;
    @FXML TreeView<String> treeViewBasicShapes;
    @FXML TreeItem<String> treeRectangle;


    /**
     * Shapes elements for the application
     */
    @Getter double insertX = 550;
    @Getter double insertY = 350;
    @Getter double stroke = 3;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    private Node selectedNode;
    String svgData = "";

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

        edit();
    }

    /**
     * Generate a square from input object
     */
    @FXML
    public void addSquare(){parseToJavaFX(inputObject);}

    /**
     * Generate a square from input object
     */
    @FXML
    public void addRectangle(){parseToJavaFX(inputObject2);}

    /**
     * Generate a circle from an input object
     */
    @FXML
    public void addCircle(){parseToJavaFX(inputObject3);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addHexagon(){parseToJavaFX(inputObject4);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addLine(){parseToJavaFX(inputObject5);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addParallelogram(){parseToJavaFX(inputObject6);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addAction(){parseToJavaFX(inputObject7);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addActivationExpression(){parseToJavaFX(inputObject8);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addContainer(){parseToJavaFX(inputObject9);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addDataFlow(){parseToJavaFX(inputObject10);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addEvent(){parseToJavaFX(inputObject11);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addModule(){parseToJavaFX(inputObject12);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addNavigationFlow(){parseToJavaFX(inputObject13);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addObject(){parseToJavaFX(inputObject14);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addParameter(){parseToJavaFX(inputObject15);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addViewComponent(){parseToJavaFX(inputObject16);}

    /**
     * Generate a  from an input object
     */
    @FXML
    public void addViewComponentPart(){parseToJavaFX(inputObject17);}

    /**
     * TYLER I CHANGED THIS to test it, your code is copied and commented out below
     */
    /**
     testBoard = new DrawingBoard();
     JavaFXDrawingObject myTestObject = testBoard.addObject(inputObject3).getLinkedJavaFX();

     if (myTestObject == null){
     System.out.println("The JavaFxDrawingObject is screwed\n");
     }
     else if (myTestObject.getLinkedDrawing() == null){
     System.out.println("The Linked DrawingObject is screwed\n");
     }

     makeShapeMove(myTestObject);
     designCenter.getChildren().add(myTestObject);
     **/

    //hashmap of all stuff and mirror that has all the input objects and then update using the hash map update the shapes
    //to insert and delte objects

    //just create a method that has switch function that takes in inputs object and translate that into javafx representation
    //post man tests apis %23 to pound symbol
    //parameters are always passed in as strings for api
    //1-3 text boxes on new input objects

    /**
     * Convert an input object created into a javafx shape so the design center can insert
     * @param i
     */
    public void parseToJavaFX(InputObject i){
        testBoard = new DrawingBoard();
        JavaFXDrawingObject shapeObject = testBoard.addObject(i).getLinkedJavaFX();
        makeShapeMove(shapeObject);
        designCenter.getChildren().add(shapeObject);
    }

    /**
     * Returns the svg data inside of the design center to make sure it is giving the right data
     */
    public void getSVGData(){
        int i = 0;
        while (i < designCenter.getChildren().size()){
            svgData += designCenter.getChildren().get(i).toString() + "\n";
            i += 1;
        }
        System.out.println(svgData);
    }

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
     * I need to add bounds on this somehow so you cant drag all over the screen
     */
    /**
     *
     */
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
    public void edit() {
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
                    // do whatever you need with the treeItem...
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

// Make shapes be able to be resized with a rectangle around them
class ResizingControl extends Group {
    private Node targetNode = null;
    private final Rectangle boundary = new Rectangle();

    private Anchor topLeft = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
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
    private Anchor topCenter = new Anchor(Color.GOLD, false, true, (oldX, oldY, newX, newY) -> {
        double newHeight = boundary.getHeight() - (newY - oldY);
        if (newHeight > 0) {
            boundary.setY(newY);
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor topRight = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
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
    private Anchor rightCenter = new Anchor(Color.GOLD, true, false, (oldX, oldY, newX, newY) -> {
        double newWidth = boundary.getWidth() + (newX - oldX);
        if (newWidth > 0) {
            boundary.setWidth(newWidth);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor bottomRight = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
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
    private Anchor bottomCenter = new Anchor(Color.GOLD, false, true, (oldX, oldY, newX, newY) -> {
        double newHeight = boundary.getHeight() + (newY - oldY);
        if (newHeight > 0) {
            boundary.setHeight(newHeight);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });
    private Anchor bottomLeft = new Anchor(Color.GOLD, true, true, (oldX, oldY, newX, newY) -> {
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
    private Anchor leftCenter = new Anchor(Color.GOLD, true, false, (oldX, oldY, newX, newY) -> {
        double newWidth = boundary.getWidth() - (newX - oldX);
        if (newWidth > 0) {
            boundary.setX(newX);
            boundary.setWidth(newWidth);
        }

        updateAnchorPositions();
        resizeTargetNode();
    });

    ResizingControl(Node targetNode) {
        this.targetNode = targetNode;

        attachBoundingRectangle(targetNode);
        attachAnchors();

        boundary.toBack();
    }

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

// Handles the dragging of the border around the shape desired to resize
interface DragHandler {
    void handle(double oldX, double oldY, double newX, double newY);
}

// A draggable anchor displayed around a point.
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
