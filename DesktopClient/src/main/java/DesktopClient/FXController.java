/**
 * Desktop Client
 * @Author Project Marduk: Tyler Kelley
 * Software Engineering project
 * Griffith
 */
package DesktopClient;

import FactoryElements.*;
import DrawingBoard.*;
import DrawingObjects.*;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Getter;

import javafx.event.ActionEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;



public class FXController {

    DrawingBoard testBoard = new DrawingBoard(3000,3000);
    InputObject inputObject = new InputObject("Square", new double[]{100, 100},"black","solid",20.0,20.0);
    InputObject inputObject2 = new InputObject("Rectangle", new double[]{100, 400},"black","solid",200.0,20.0);
    InputObject inputObject3 = new InputObject("Circle", new double[]{100, 200},"black","solid",600.0,400.0);




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
    @FXML WebView designWebView;


    /**
     * Shapes for the application
     */
    @Getter double insertX = 700;
    @Getter double insertY = 400;
    @Getter double stroke = 3;
    String shape;
    WebEngine engine;
    Shape Circle;


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
    public void testCreate(){
        testBoard.addObject(inputObject3);

        StringBuilder svgBuilder = new StringBuilder();
        svgBuilder.append("<!DOCTYPE html>");
        svgBuilder.append("<html>");
        svgBuilder.append("<body>");
        svgBuilder.append(testBoard.returnSVGData());
        svgBuilder.append("</body>");
        svgBuilder.append("</html>");
        String html = svgBuilder.toString();

        Design.getChildren().remove(designWebView);

        engine = designWebView.getEngine();
        engine.loadContent(html);

        Design.getChildren().add(designWebView);
    }

    @FXML
    public void testWrite(){
        testBoard.addObject(inputObject);
        shape = testBoard.returnSVGData();
        System.out.println("Test");
        System.out.println(shape);

        StringBuilder svgBuilder = new StringBuilder();
        svgBuilder.append("<!DOCTYPE html>");
        svgBuilder.append("<html>");
        svgBuilder.append("<body>");
        svgBuilder.append(testBoard.returnSVGData());
        svgBuilder.append("</body>");
        svgBuilder.append("</html>");
        String html = svgBuilder.toString();

        engine = designWebView.getEngine();
        engine.loadContent(html);
        Design.getChildren().add(designWebView);
    }

    @FXML
    public void testAdd() throws IOException {
        testBoard.addObject(inputObject2);

        StringBuilder svgBuilder = new StringBuilder();
        svgBuilder.append("<!DOCTYPE html>");
        svgBuilder.append("<html>");
        svgBuilder.append("<body>");
        svgBuilder.append(testBoard.returnSVGData());
        svgBuilder.append("</body>");
        svgBuilder.append("</html>");
        String html = svgBuilder.toString();

        Design.getChildren().remove(designWebView);

        engine = designWebView.getEngine();
        engine.loadContent(html);

        Design.getChildren().add(designWebView);
    }
}
