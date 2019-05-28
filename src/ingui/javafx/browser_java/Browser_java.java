/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingui.javafx.browser_java;

import java.io.InputStream;
import javafx.application.Application;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Miguel González Socas
 * @author Pablo Viera
 */
public class Browser_java extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        boolean rest = true;
        String[] error = {""};
        rest = configurar(stage, error);
        if (rest) {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        }

        if (rest == false) {
            System.out.println(error[0]);
        }
    }

    public boolean configurar(Stage stage, String[] error) {
        boolean rest = true;
        stage.setTitle("Browser_java");
        
        ObservableList<Image> observableList = stage.getIcons();
       
        // https://icon-icons.com/es/download/24729/PNG/256/
       InputStream inputStream = Browser_java.class.getResourceAsStream(
             "/browser_java/recursos/Web_Folders_24729.png");
       Image image = new Image(inputStream);
        observableList.add(image);

        return rest;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
