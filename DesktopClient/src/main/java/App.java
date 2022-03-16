
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.net.URL;

import java.io.IOException;

public class App extends Application{

    /**
     * Launch the application when the app is open
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        launch();

    }

    /**
     * Start the main stage
     */
    @Override
    public void start(Stage stage) throws Exception{

        Update update = new Update();
        FXController Login = update.UIController;
        Login.showLogin();

        stage.setTitle("Desktop UI Client");
    }

}
