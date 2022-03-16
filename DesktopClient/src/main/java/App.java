
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.net.URL;

import java.io.IOException;

public class App extends Application{

    @Getter public static final String Login_FXML = "/Login.fxml";
    @Getter public static final String CreateUser_FXML = "/CreateUser.fxml";

    @Getter Stage mainStage;

    static URL fxmlLocation;

    public static void main(String[] args) throws IOException {
        launch(args);

    }

    /**
     * Start the main stage
     */
    @Override
    public void start(Stage stage) throws Exception{

        this.mainStage = stage;
        stage.setTitle("Desktop UI Client");

        showLogin();


        stage.show();
    }
    /**
     * Show the Login page
     */
    public void showLogin() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new FXController(this));
        loader.setLocation(getClass().getResource(Login_FXML));
        Parent root = loader.load();
        Scene scene = new Scene(root, 1000, 800);
        mainStage.setScene(scene);
        mainStage.setMaximized(true);
    }

    public void exit(){
        mainStage.close();
        System.exit(0);
    }
}
