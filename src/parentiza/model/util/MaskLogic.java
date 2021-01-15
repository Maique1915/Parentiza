/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parentiza.model.util;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class MaskLogic {
    
    private static final String[] KEYWORDS = new String[]{
    "→","↔","¬","∧","∨","⊢","∀","∃","(",")"};

    public static void Mask(final TextField textField) {
        textField.lengthProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
            String value = textField.getText();
            value = value.replaceAll("[0-9]", "");
            value = value.replaceAll("->", "→");
            value = value.replaceAll("<->", "↔ ");
            value = value.replaceAll("<→", "↔ ");
            value = value.replaceAll("~", "¬");
            value = value.replaceAll("\\*", "∧");
            value = value.replaceAll("\\+", "∨");
            value = value.replaceAll("\\^", "∧");
            value = value.replaceAll("  ", " ");

            textField.setText(value);
            //positionCaret(textField);
        }
    });

    }
    
    private static void positionCaret(final TextField textField) {
    Platform.runLater(new Runnable() {
        @Override
        public void run() {
            // Posiciona o cursor sempre a direita.
            textField.positionCaret(textField.getText().length());
        }
    });
}
    
}
