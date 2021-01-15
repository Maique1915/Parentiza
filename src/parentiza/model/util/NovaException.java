package parentiza.model.util;

import javafx.scene.control.Alert;


public class NovaException{
    
    NovaException(String tipo, String contexto, String msg, Alert.AlertType al) {
        Alert alert = new Alert(al);
        String str = "";
        str = (al.equals(Alert.AlertType.ERROR))?"Erro ":(al.equals(Alert.AlertType.WARNING))?"Alerta ":(al.equals(Alert.AlertType.INFORMATION))?"Informação ":(al.equals(Alert.AlertType.CONFIRMATION))?"Confirmação ": " ";
            
        alert.setTitle(str+tipo);
        alert.setHeaderText(msg);
        alert.setContentText(contexto);
        alert.showAndWait();
    }
    
    NovaException(String tipo,String contexto, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro "+tipo);
        alert.setHeaderText(msg);
        alert.setContentText(contexto);
        alert.showAndWait();
    }
    
    NovaException(String tipo,String contexto) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro "+tipo);
        alert.setHeaderText("");
        alert.setContentText(contexto);
        alert.showAndWait();
    }
}
