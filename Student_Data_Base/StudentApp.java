/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * StudentApp class extends Application and it is responsible to start the User Interface
 */
public class StudentApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * main
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
