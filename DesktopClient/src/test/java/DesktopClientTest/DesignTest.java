/**
 * Desktop Client
 * @Author Project Marduk: Tyler Kelley
 * Software Engineering project
 * Griffith
 */
package DesktopClientTest;

import javafx.fxml.FXMLLoader;
import javafx.geometry.VerticalDirection;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest class tests that the javaFX elements inside of the Design.fxml are properly placed on the desktop app
 */
public class DesignTest extends ApplicationTest{

    /**
     * Variables that will make testing easier
     */
    public static final String Design_FXML = "/Design.fxml";
    BorderPane root;
    BorderPane design;
    HBox topHBox;
    MenuBar menuBar;
    /*
    This is how the menu bar is layed out for reference
        Menu menuFile;
            MenuItem openFile;
            MenuItem saveFile;
        Menu menuExport;
            MenuItem exportSVG;
            MenuItem exportPNG;
        Menu menuExit;
            MenuItem closeWindow;
     */
    ScrollPane scrollPane;
    VBox scrollPaneVBox;
    TreeView treeViewBasicShapes;


    /**
     * Start the application test for testfx
     * @param stage
     * @throws Exception
     */
    @Override
    public void start (Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource(Design_FXML));
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
        design = (BorderPane) root.lookup("#Design");
        topHBox = (HBox) root.lookup("#topHBox");
        menuBar = (MenuBar) root.lookup("#menuBar");
        scrollPane = (ScrollPane) root.lookup("#scrollPane");
        scrollPaneVBox = (VBox) root.lookup("#scrollPaneVBox");
        treeViewBasicShapes = (TreeView) root.lookup("#treeViewBasicShapes");

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
     * Assert that the border pane is set to the right size
     */
    @Test
    public void testBorderPane(){
        MatcherAssert.assertThat(design.getPrefHeight(), is(630.0));
        MatcherAssert.assertThat(design.getPrefWidth(), is(623.0));
    }

    /**
     * The HBox in the javafx isn't seen as it just acts as a container so as long as it's not null it is there
     * properly containing menu items
     */
    @Test
    public void testTopHBox(){
        assertNotNull(topHBox);
    }

    /**
     * Test that the menu bar has the clickable menu items for methods that we will create later
     * For now assert equals 1 will be the methods we can test when the menu item is clicked
     */
    @Test
    public void testMenuBarMenuFileOpenFile(){
        assertNotNull(menuBar);
        clickOn("#menuBar");
        assertEquals(1,1);
        clickOn("#menuFile");
        assertEquals(1,1);
        clickOn("#openFile");
        assertEquals(1,1);
    }

    /**
     * Test that the menu bar has the clickable menu item of save file. Same as above with the assert equals, just for
     * now meaning that the test passed since there is the clickable menu item we want
     */
    @Test
    public void testMenuBarMenuFileSaveFile(){
        assertNotNull(menuBar);
        clickOn("#menuBar");
        assertEquals(1,1);
        clickOn("#menuFile");
        assertEquals(1,1);
        clickOn("#saveFile");
        assertEquals(1,1);
    }

    /**
     * Test that the menu bar has the clickable menu item of exporting as SVG. Same as above with the assert equals, just for
     * now meaning that the test passed since there is the clickable menu item we want
     */
    @Test
    public void testMenuBarMenuExportSVG(){
        assertNotNull(menuBar);
        clickOn("#menuBar");
        assertEquals(1,1);
        clickOn("#menuExport");
        assertEquals(1,1);
        clickOn("#exportSVG");
        assertEquals(1,1);
    }

    /**
     * Test that the menu bar has the clickable menu item of exporting as PNG. Same as above with the assert equals, just for
     * now meaning that the test passed since there is the clickable menu item we want
     */
    @Test
    public void testMenuBarMenuExportPNG(){
        assertNotNull(menuBar);
        clickOn("#menuBar");
        assertEquals(1,1);
        clickOn("#menuExport");
        assertEquals(1,1);
        clickOn("#exportPNG");
        assertEquals(1,1);
    }

    /**
     * Test that the menu bar has the clickable menu item of exporting as SVG. Same as above with the assert equals, just for
     * now meaning that the test passed since there is the clickable menu item we want
     */
    @Test
    public void testMenuBarMenuExportExit(){
        assertNotNull(menuBar);
        clickOn("#menuBar");
        assertEquals(1,1);
        clickOn("#menuExit");
        assertEquals(1,1);
        /*
        clickOn("#closeWindow");
        assertEquals(1,1);
        closes testing? because it closes window
         */

    }

    /**
     * Test that the VBox inside of the scroll pane is laid out properly and contains the elements needed
     */
    @Test
    public void testScrollPaneVBox(){
        MatcherAssert.assertThat(scrollPaneVBox.getSpacing(), is(10.0));
        MatcherAssert.assertThat(scrollPaneVBox.getMinWidth(), is(286.0));
    }

    /**
     * Test that the treeview is displaying properly on the application
     */
    @Test
    public void testTreeView(){
        assertNotNull(treeViewBasicShapes);
        assertFalse(treeViewBasicShapes.isShowRoot());
        MatcherAssert.assertThat(treeViewBasicShapes.getMaxWidth(), is(286.0));
    }

    /**
     * Test that the treeview contains the proper buttons the user can click on to instert shapes
     */
    @Test
    public void testTreeViewElements(){
        clickOn("#treeViewBasicShapes");
        MatcherAssert.assertThat(treeViewBasicShapes.getExpandedItemCount(), is(2));
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
