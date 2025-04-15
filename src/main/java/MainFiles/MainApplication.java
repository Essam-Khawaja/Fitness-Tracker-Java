package MainFiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 800);

       Parameters params = getParameters();
       List<String> args = params.getRaw(); // Or params.getNamed() for key-value pairs

        ProjectController controller = fxmlLoader.getController();
        controller.setParameters(args); // Custom method in the controller

        scene.getStylesheets().add(MainApplication.class.getResource("Main.css").toExternalForm());
        stage.setTitle("Fitness Tracker!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}