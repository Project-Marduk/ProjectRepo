/**
 * Desktop Client
 * @Author Project Marduk: Tyler Kelley
 * Software Engineering project
 * Griffith
 */
package DesktopClient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Getter;

import javafx.event.ActionEvent;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
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


    /**
     * Shapes for the application
     */
    Circle circle;
    Rectangle rectangle;
    Line line;
    @Getter double insertX = 700;
    @Getter double insertY = 400;
    @Getter double stroke = 3;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;


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

    /**
     * Create a circle based on what the user selected from the scroll bar
     * @param event
     * @throws IOException
     */
    @FXML
    public void createCircle(ActionEvent event) throws IOException{
        circle = new Circle(50.0f);
        makeShapeAttributes(circle);
        Design.getChildren().add(circle);
    }

    /**
     * Create a rectangle or square based on what the user selected from the scroll bar
     * @param event
     * @throws IOException
     */
    @FXML
    public void createRectangle(ActionEvent event) throws IOException{
        rectangle = new Rectangle(150,150);
        makeShapeAttributes(rectangle);
        Design.getChildren().add(rectangle);
    }

    @FXML
    public void createLine(ActionEvent event) throws IOException{
        line = new Line(0,0,200,0);
        makeShapeAttributes(line);
        Design.getChildren().add(line);
    }

    public void makeShapeAttributes(Shape shape){
        shape.setFill(Color.WHITE);
        shape.setStrokeWidth(stroke);
        shape.setStroke(Color.BLACK);
        shape.setCursor(Cursor.MOVE);
        shape.setTranslateX(insertX);
        shape.setTranslateY(insertY);
        shape.setOnMousePressed(shapeOnMousePressedEventHandler);
        shape.setOnMouseDragged(shapeOnMouseDraggedEventHandler);
    }


    /**
     * Open source code from http://java-buddy.blogspot.com/2013/07/javafx-drag-and-move-something.html
     * I do not own rights to this code. This is just in for now to demonstrate an easy way to move shapes
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
                        Design.getChildren().remove(t.getSource());
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
                }
            };
}
