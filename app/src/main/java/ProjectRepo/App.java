package ProjectRepo;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public String getGreeting() {
        return "Hello World!";
    }

    /**
     * Starts the JavaFX UI. Visuals are found in MainMenu.fxml in the resources' folder. Logic is in FXController.java.
     *
     * @param stage default stage for application
     * @throws IOException
     **/
    @Override
    public void start(Stage stage) throws IOException {
        Update update = new Update();
        FXController Login = update.UIController;
        Login.showMainMenu();
    }

    public static void main(String[] args) throws IOException {
        launch();
        System.out.println(new App().getGreeting());
    }
}
