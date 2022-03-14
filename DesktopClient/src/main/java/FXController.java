import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import javafx.application.Platform;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class FXController {

    @Getter public static final String Login_FXML = "/Login.fxml";
    @Getter public static final String CreateUser_FXML = "/CreateUser.fxml";

    private App app;
    /**
     * Elements for MainMenu
     */
    @FXML
    private Button exitButton;

    @Getter public final Stage mainStage = new Stage();

    public FXController(){}

    /**
     * Creates and shows the Main menu where you can select to start, load, or exit.
     *
     */
    public FXController(App application){
        this.app = application;
    }

    @FXML
    protected void handleExitButton(ActionEvent event){
        app.exit();
    }

    public void handleCreateUserButton(FXMLLoader loader){
        Platform.setImplicitExit(false);
    }

    public void showLogin() throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource(Login_FXML));
        loginLoader.setController(this);
        mainStage.setScene(new Scene(loginLoader.load()));
    }
}
