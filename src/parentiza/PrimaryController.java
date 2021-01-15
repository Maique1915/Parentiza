/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parentiza;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import parentiza.model.Parentiza;
import parentiza.model.Token;
import parentiza.model.util.Lexico;
import parentiza.model.util.MaskLogic;
import parentiza.model.util.Sintatico;

/**
 * FXML Controller class
 *
 * @author Maique
 */
public class PrimaryController implements Initializable {

    @FXML
    private BorderPane BPane;
    @FXML
    private TextField input;
    @FXML
    private ScrollPane scrollPane;

    private VBox teclado;
    Parentiza parentiza;
    private StringBuffer s;
    private boolean b;
    
    /**
    * Initializes the controller class.
    * @param url
    * @param rb
    */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        b = false;
        iniciaInput();
    }
    
    private void iniciaInput(){
        input.setText("a∧b→c∨k→o∨h");
        MaskLogic.Mask(input);

        input.setOnMouseReleased((event)->{
            tecladoVisivel(true);
        });
        
        input.setOnKeyReleased((KeyEvent e) -> {
            if(e.getCode() == KeyCode.ENTER){
                exec();
            }
        });
    }

    public void exec(){
        Lexico lx = new Lexico();
        List<Token> tokens = lx.SCANNER(input.getText());
        TextFlow output = new TextFlow();

        if(tokens != null){
            Sintatico sin = new Sintatico(tokens);
            if(sin.verifica()){
                Parentiza p = new Parentiza(tokens);
                output = p.imprime();
            }
        }
        getScrollPane().setPadding(new Insets(50,0,0,0));
        getScrollPane().setContent(output);
    }

    public void tecladoVisivel(boolean b) {
        if (b && BPane.getBottom() == null){

            teclado.translateYProperty().set(BPane.getHeight());
            BPane.setBottom(teclado);
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(teclado.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();

        }else if(!b && BPane.getBottom() != null){
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(teclado.translateYProperty(), teclado.getHeight(), Interpolator.EASE_OUT);
            KeyFrame kf = new KeyFrame(Duration.seconds(0.2), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t -> {
                BPane.setBottom(null);
            });
            timeline.play();
        }
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setTeclado(VBox teclado) {
        this.teclado = teclado;
    }

    public TextField getInput(){
        return this.input;
    }

}
