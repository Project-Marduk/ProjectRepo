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
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Getter;

import javafx.event.ActionEvent;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.WritableImage;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;


public class FXController {

    DrawingBoard testBoard = new DrawingBoard(3000,3000);
    InputObject inputObject = new InputObject("Square", new double[]{100, 100},"black","solid",20.0,20.0);
    InputObject inputObject2 = new InputObject("Rectangle", new double[]{100, 400},"black","solid",200.0,20.0);
    InputObject inputObject3 = new InputObject("Circle", new double[]{100, 200},"black","solid",600.0,400.0);
    InputObject inputObject4 = new InputObject("Hexagon",new double[]{100,200}, "black","solid",50.0,50.0);



    public void TRAAEtestSPACE(){

    }



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


    /**
     * Shapes for the application
     */
    @Getter double insertX = 700;
    @Getter double insertY = 400;
    @Getter double stroke = 3;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    private Node selectedNode;

    String svgData = "";
    WebEngine engine;
    Shape javaShape;
    SVGPath path;
    TextArea textArea;
    Text textHolder = new Text();


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
    }

    @FXML
    public void testAddText(){
    }

    @FXML
    public void testAdd(){
        Rectangle test = new Rectangle(200,200);
        path = new SVGPath();
        javaShape = path;
        javaShape.setFill(colorPicker.getValue());
        javaShape.setTranslateX(insertX);
        javaShape.setTranslateY(insertY);
        javaShape.setOnMousePressed(shapeOnMousePressedEventHandler);
        javaShape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
        designCenter.getChildren().add(javaShape);
    }

    /**
     * This is the main method that will add the shapes we are creating. Just press the circle button under the tree view
     * to insert shapes
     */
    @FXML
    public void testSVGPathMethods(){
        /**
        Circle testcirc = new Circle(30);
        testcirc.setFill(colorPicker.getValue());
        Image testImgae = testcirc.snapshot(new SnapshotParameters(),null);

        Rectangle test = new Rectangle(200,200);
        test.setFill(new ImagePattern(testImgae));
         **/

        javaShape = ShapeJavaFXFunctions.rectToJavaFX(inputObject);
        javaShape.setFill(colorPicker.getValue());

        javaShape.setCursor(Cursor.MOVE);
        javaShape.setTranslateX(insertX);
        javaShape.setTranslateY(insertY);
        javaShape.setOnMousePressed(shapeOnMousePressedEventHandler);
        javaShape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
        designCenter.getChildren().add(javaShape);
        makeSelectable(javaShape);
        getSVGData();

        //Method 2 failed
        /**
         * WARNING: Failed to configure svg path "rect fill="none" x="131.0" width="222.0"
         * height="146.0" y="79.0" stroke="#000000"/": invalid command (r) in SVG path at pos=1
         */
        /**
        path = new SVGPath();
        //path.setContent(testBoard.TYLERreturnSVGRectExample());
        javaShape = path;
        javaShape.setTranslateX(insertX);
        javaShape.setTranslateY(insertY);
        javaShape.setOnMousePressed(shapeOnMousePressedEventHandler);
        javaShape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
        designCenter.getChildren().add(javaShape);

        //Method 3 failed
        /**
         * WARNING: Failed to configure svg path "
         * <rect x="20.0" y="20.0" fill="#FFFFFF" fill-opacity=".25" width="100.0" stroke-linejoin="round" height="100.0" stroke="#000000"/>
         * <text x="20.0" y="20.0" font-family="Verdana" font-size="12.0" fill="black"></text>
         * ": invalid command (<) in SVG path at pos=2
         */
        /**
        testBoard.addObject(inputObject);
        path = new SVGPath();
        //path.setContent(testBoard.TYLERreturnSVGPathEdgeless());
        javaShape = path;
        javaShape.setTranslateX(insertX);
        javaShape.setTranslateY(insertY);
        javaShape.setOnMousePressed(shapeOnMousePressedEventHandler);
        javaShape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
        designCenter.getChildren().add(javaShape);

        //Method 4 failed
        /**
         * WARNING: Failed to configure svg path "
         * <rect x="20.0" y="20.0" fill="#FFFFFF" fill-opacity=".25" width="100.0" stroke-linejoin="round" height="100.0" stroke="#000000"/>
         * <text x="20.0" y="20.0" font-family="Verdana" font-size="12.0" fill="black"></text>
         * ": invalid command (<) in SVG path at pos=2
         */
        /**
        path = new SVGPath();
        //path.setContent(testBoard.TYLERreturnSVGPath());
        javaShape = path;
        javaShape.setTranslateX(insertX);
        javaShape.setTranslateY(insertY);
        javaShape.setOnMousePressed(shapeOnMousePressedEventHandler);
        javaShape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
        designCenter.getChildren().add(javaShape);
         **/
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

    /**
     * Open source code from http://java-buddy.blogspot.com/2013/07/javafx-drag-and-move-something.html
     * This allows for the java shapes to be moved around freely inside of the design center
     * I need to add bounds on this somehow so you cant drag all over the screen
     */
    EventHandler<MouseEvent> shapeOnMousePressedEventHandler =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Shape)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Shape)(t.getSource())).getTranslateY();

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

                    ((Shape)(t.getSource())).setTranslateX(newTranslateX);
                    ((Shape)(t.getSource())).setTranslateY(newTranslateY);
                    ((Shape)(t.getSource())).toFront();
                }
            };

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

interface DragHandler {
    void handle(double oldX, double oldY, double newX, double newY);
}

// a draggable anchor displayed around a point.
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
