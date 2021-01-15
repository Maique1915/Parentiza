/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parentiza;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Maique
 */
public class Main extends Application {
    
    private static Scene scene;
    private PrimaryController controller;
    private SecondaryController controller2;
    private BorderPane root;
    private VBox root2;
    
    @Override
    public void start(Stage stage) throws IOException {
        principal();
        teclado();
        scene = new Scene(root, 450, 600);
        stage.setTitle("Parentiza");
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        stage.show();
    }
    
    private void teclado() throws IOException{
        FXMLLoader loader2 = loadFXML("teclado");
        root2 = (VBox) loader2.load();
        controller2 = loader2.getController();
        controller.setTeclado(root2);
        controller2.setPrimary(controller);
    }

    private void principal() throws IOException{
        FXMLLoader loader = loadFXML("primary");
        root = (BorderPane) loader.load();
        controller = loader.getController();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/"+fxml +".fxml"));
        return fxmlLoader;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}