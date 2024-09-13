package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calcInterface.fxml")));
        stage.setTitle("Calculator");
        stage.setScene(new Scene(root,350,370));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}