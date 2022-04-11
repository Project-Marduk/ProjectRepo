/**
 * Desktop Client
 * @Author Project Marduk: Tyler Kelley
 * Software Engineering project
 * Griffith
 */
package DesktopClientTest;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest class tests that the javaFX elements inside of the Login.fxml are properly placed on the desktop app
 */
public class LoginTest extends ApplicationTest{

    /**
     * Variables that will make testing easier
     */
    public static final String Login_FXML = "/Login.fxml";
    BorderPane root;
    Label loginLabel;
    Label createAccount;
    Button loginButton;
    Button createAccountButton;
    Button exitButton;
    TextField usersUsername;
    VBox rightVBox;
    VBox leftVBox;

    /**
     * Start the application test for testfx
     * @param stage
     * @throws Exception
     */
    @Override
    public void start (Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource(Login_FXML));
        stage.setMaximized(true);
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
        setVariables();
    }

    /**
     * This method sets all the variables that are inside of the Login.FXML file so they can be called later
     */
    public void setVariables(){
        loginLabel = (Label) root.lookup("#loginLabel");
        createAccount = (Label) root.lookup("#createAccount");
        loginButton = (Button) root.lookup("#loginButton");
        createAccountButton = (Button) root.lookup("#createAccountButton");
        exitButton = (Button) root.lookup("#exitButton");
        usersUsername = (TextField) root.lookup("#usersUsername");
        rightVBox = (VBox) root.lookup("#rightVBox");
        leftVBox = (VBox) root.lookup("#leftVBox");
    }

    /**
     * Testfx method needed to setup the application test
     * @throws Exception
     */
    @Before
    public void setUp () throws Exception {
    }

    /**
     * Close the stage after testing is done
     * @throws Exception
     */
    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    /**
     * Assert that the login label is properly laid out and displayed on the javafx page
     */
    @Test
    public void testLoginLabel(){
        MatcherAssert.assertThat(loginLabel.getText(), is("Sign In"));
        MatcherAssert.assertThat(loginLabel.getPrefHeight(), is(50.0));
        MatcherAssert.assertThat(loginLabel.getPrefWidth(), is(150.0));
    }

    /**
     * Assert that the create account label is properly laid out and displayed on the javafx page
     */
    @Test
    public void testCreateAccountLabel(){
        MatcherAssert.assertThat(createAccount.getText(), is("                  or create an account"));
        MatcherAssert.assertThat(createAccount.getPrefHeight(), is(50.0));
        MatcherAssert.assertThat(createAccount.getPrefWidth(), is(250.0));
    }

    /**
     * Assert that the login button is properly laid out and displayed on the javafx page
     */
    @Test
    public void testLoginButton(){
        MatcherAssert.assertThat(loginButton.getText(), is("LOGIN"));
        MatcherAssert.assertThat(loginButton.getPrefHeight(), is(50.0));
        MatcherAssert.assertThat(loginButton.getPrefWidth(), is(650.0));
    }

    /**
     * Assert that the create account button is properly laid out and displayed on the javafx page
     */
    @Test
    public void testCreateAccountButton(){
        MatcherAssert.assertThat(createAccountButton.getText(), is("Create Account"));
        MatcherAssert.assertThat(createAccountButton.getPrefHeight(), is(50.0));
        MatcherAssert.assertThat(createAccountButton.getPrefWidth(), is(650.0));
    }

    /**
     * Assert that the exit button is properly laid out and displayed on the javafx page
     */
    @Test
    public void testExitButton(){
        MatcherAssert.assertThat(exitButton.getText(), is("Exit"));
        MatcherAssert.assertThat(exitButton.getPrefHeight(), is(50.0));
        MatcherAssert.assertThat(exitButton.getPrefWidth(), is(650.0));
    }

    /**
     * Assert that the right vertical box is properly laid out and displayed on the javafx page
     */
    @Test
    public void testRightVBox(){
        MatcherAssert.assertThat(rightVBox.getSpacing(), is(20.0));
        MatcherAssert.assertThat(rightVBox.getMaxHeight(), is(700.0));
        MatcherAssert.assertThat(rightVBox.getMaxWidth(), is(800.0));
        MatcherAssert.assertThat(rightVBox.getAlignment(), is(Pos.TOP_LEFT));
    }

    /**
     * Assert that the left vertical box is properly laid out and displayed on the javafx page
     */
    @Test
    public void testLeftVBox(){
        MatcherAssert.assertThat(leftVBox.getSpacing(), is(10.0));
        MatcherAssert.assertThat(leftVBox.getMaxHeight(), is(700.0));
        MatcherAssert.assertThat(leftVBox.getMaxWidth(), is(800.0));
    }

    /**
     * Assert that the username text field is properly laid out and displayed on the javafx page
     * The last part of the assert tests that the text field can take in a string and have the string
     * be tested to be a valid username (valid username testing will be implemented soon)
     */
    @Test
    public void testUsersUsername(){
        MatcherAssert.assertThat(usersUsername.getPromptText(), is("Username"));
        MatcherAssert.assertThat(usersUsername.getPrefHeight(), is(50.0));
        MatcherAssert.assertThat(usersUsername.getPrefWidth(), is(150.0));

        /*
        clickOn(usersUsername);
        write("Test");
        clickOn(rightVBox);
        MatcherAssert.assertThat(usersUsername.getText(), is("Test"));
         */
    }

    /**
     * Always passing test just to make sure that Junit is working properly for the class
     */
    @Test
    public void testJunit(){
        int x = 1;
        assertEquals(1,x);


    }

}
