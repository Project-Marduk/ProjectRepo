package DesktopClient;

import javafx.application.Application;
import javafx.stage.Stage;

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
