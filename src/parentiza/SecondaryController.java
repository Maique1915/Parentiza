package parentiza;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SecondaryController implements Initializable {
   

     
    @FXML
    private Button btnQ;
    @FXML
    private Button btnW;
    @FXML
    private Button btnE;
    @FXML
    private Button btnR;
    @FXML
    private Button btnT;
    @FXML
    private Button btnY;
    @FXML
    private Button btnU;
    @FXML
    private Button btnI;
    @FXML
    private Button btnO;
    @FXML
    private Button btnP;
    @FXML
    private Button btnA;
    @FXML
    private Button btnS;
    @FXML
    private Button btnD;
    @FXML
    private Button btnF;
    @FXML
    private Button btnG;
    @FXML
    private Button btnH;
    @FXML
    private Button btnJ;
    @FXML
    private Button btnK;
    @FXML
    private Button btnL;
    @FXML
    private Button btnZ;
    @FXML
    private Button btnX;
    @FXML
    private Button btnC;
    @FXML
    private Button btnV;
    @FXML
    private Button btnB;
    @FXML
    private Button btnN;
    @FXML
    private Button btnM;
    @FXML
    private Button btnNot;
    @FXML
    private Button btnAnd;
    @FXML
    private Button btnOr;
    @FXML
    private Button btnImp;
    @FXML
    private Button btnAp;
    @FXML
    private Button btnFp;
    @FXML
    private Button btnMai;
    @FXML
    private Button btnTec;
    @FXML
    private Button btnEsp;
    @FXML
    private Button btnAc;
    @FXML
    private Button btnRes1;
    
    private  boolean up = false;
    private PrimaryController telaConttroller;
    private ArrayList<Button> buttons =  new ArrayList();
    private TextField input;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Button[] Abutton = {btnQ, btnW, btnE, btnR, btnT, btnY, btnU, btnI, btnO, 
            btnP, btnA, btnS, btnD, btnF, btnG, btnH, btnJ,btnK, btnL, btnZ, btnX, 
            btnC, btnV, btnB, btnN, btnM, btnNot, btnAnd, btnOr, btnImp, btnEsp,btnAp,btnFp};
        
        buttons = new ArrayList<>(Arrays.asList(Abutton));
        buttons.forEach((Button button) -> {
            button.setOnMouseReleased((MouseEvent e) -> {
                input.setText(input.getText()+button.getText());
            });
        });
    
    
    }

    void setPrimary(PrimaryController controller) {
        input = controller.getInput();
        
        btnRes1.setOnMouseReleased((MouseEvent e)->{
            telaConttroller.exec();
        });
        
        btnMai.setOnMouseReleased((MouseEvent e)->{
            up = !up;
            if(up){
                btnMai.setStyle("-fx-text-fill: #00FF7F;");
                btnMai.setText("A/a");
            buttons.forEach((Button button) -> {
                button.setText(button.getText().toUpperCase());
            });
            }else{
                btnMai.setStyle("-fx-text-fill: #fff;");
                btnMai.setText("a/A");
            buttons.forEach((Button button) -> {
                button.setText(button.getText().toLowerCase());
            });
            }
    
        });
        
        btnTec.setOnMouseReleased((MouseEvent e)->{
            controller.tecladoVisivel(false);
        });
         
        btnAc.setOnMouseReleased((MouseEvent e)->{
            input.setText(input.getText().substring(0, input.getText().length()-1));
        });
        
        this.telaConttroller = controller;
    }

    public Button getBtnRes() {
        return btnRes1;
    }
}