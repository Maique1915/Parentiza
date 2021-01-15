package parentiza.model;

import parentiza.model.util.ArvoreBinaria;
import parentiza.model.util.No;
import java.util.ArrayList;
import java.util.List;
import parentiza.model.util.TipoToken;
import java.util.Collections;
import java.util.HashMap;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import parentiza.model.util.ArvoreSintatica;

public class Parentiza {
    
    private final List<Token> tokens;
    private final List<String> expressoes;
    private List<String> subExpr;
    private final TextFlow output = new TextFlow();
    private int i;
    private int abre;
    private int fecha;
    
    private StringBuffer s;
    
    private final TipoToken[] precedencia = new TipoToken[]{
        TipoToken.NAO,
        TipoToken.E,
        TipoToken.OU,
        TipoToken.D_IMPLICA
    };
    private final HashMap<String, No<Token>> aux2 = new  HashMap<>();

    public Parentiza(List<Token> tokens) {
        expressoes = new ArrayList();
        subExpr = new ArrayList();
        this.tokens = tokens;
        
        output.setTextAlignment(TextAlignment.CENTER);
        output.setMinWidth(450);
        
        s = new StringBuffer();
        Text l2 = new Text(s.toString()+"\n");
        l2.setFont(new Font(15));
        l2.setFill(Color.web("#D8D8D8"));
        output.getChildren().add(l2);
        expressoes.add(s.toString());
        
        verifica();

    }

    private void verifica(){
        if (tokens.size() == 3) {
            parentiza();
            return;
        }
        for (TipoToken t : precedencia){
            for (i = 1; i < this.tokens.size()-1; i++){
                if(tokens.get(i).compareLexema(t)){
                    verificaDepois(i+1);
                    verificaAntes(i-1);
                    boolean tabre2 = tokens.get(abre-1).compareTToken(TipoToken.ABRE_PARENTESES);
                    boolean tfecha = tokens.get(fecha).compareTToken(TipoToken.FECHA_PARENTESES);
                    if(!(tabre2 && tfecha))
                        parentiza();
                }
            }
        }
        
        for (i = this.tokens.size()-1;i>0; i--){
            if(tokens.get(i).compareLexema(TipoToken.IMPLICA)){
                verificaAntes(i-1);verificaDepois(i+1);
                    boolean tabre2 = tokens.get(abre-1).compareTToken(TipoToken.ABRE_PARENTESES);
                    boolean tfecha = tokens.get(fecha).compareTToken(TipoToken.FECHA_PARENTESES);
                    if(!(tabre2 && tfecha))
                        parentiza();
            }
        }
        
    }

    private void verificaAntes(int i){
        if(tokens.get(this.i).compareLexema(TipoToken.NAO)){
            for(;i > 0;i--){
                if (!tokens.get(i-1).compareLexema(TipoToken.NAO))
                    break;
            }
            abre = i+1;
        }else if(tokens.get(i).compareTToken(TipoToken.ATOMO)){
            
            if(!subExpr.contains(tokens.get(i).toString()))
                subExpr.add(tokens.get(i).toString());
            
            for(;i > 0;i--)
                if (!tokens.get(i-1).compareLexema(TipoToken.NAO))
                    break;
            abre = i;
        }else if(tokens.get(i).compareTToken(TipoToken.FECHA_PARENTESES)){
            int ap = 0;
            for(;i > 0;i--){
                if (tokens.get(i).compareTToken(TipoToken.ABRE_PARENTESES)){
                    ap++;
                }else if (tokens.get(i).compareTToken(TipoToken.FECHA_PARENTESES))
                    ap--;
                if(ap == 0)
                    break;
            }
            abre = i;
        }
    }
    
    private void verificaDepois(int i){
        if(tokens.get(i).compareTToken(TipoToken.ATOMO)){
            fecha = i+1 <= tokens.size()?i+1:i;
            if(!subExpr.contains(tokens.get(i).toString()))
                subExpr.add(tokens.get(i).toString());
        }else if(tokens.get(i).compareLexema(TipoToken.NAO)){
            verificaDepois(i+1);
        }else if(tokens.get(i).compareTToken(TipoToken.ABRE_PARENTESES)){
            int fp  = 0;
            for(;i < tokens.size() -1;i++){
                
                if (tokens.get(i).compareTToken(TipoToken.FECHA_PARENTESES)){
                    fp--;
                }else if (tokens.get(i).compareTToken(TipoToken.ABRE_PARENTESES))
                    fp++;
                if(fp == 0)
                    break;
            }
            fecha = i+1 <= tokens.size()?i+1:i;
        }
    }
    
    private void parentiza(){
        if(tokens.size() > 3){

            tokens.add(fecha, new Token(TipoToken.FECHA_PARENTESES, ")"));
            tokens.add(abre, new Token(TipoToken.ABRE_PARENTESES, "("));

            this.i++;
            s = new StringBuffer();
            tokens.forEach(token -> {
                s.append(token.toString());
            });

            expressoes.add(s.toString());

            for (int j = 1; j < tokens.size()-1; j++) {
                Label l2 = new Label(tokens.get(j).toString());
                l2.setFont(new Font(15));
                if(j == abre || j == fecha+1)
                    l2.setTextFill(Color.web("#FA5882"));
                else
                    l2.setTextFill(Color.web("#D8D8D8"));
                output.getChildren().add(l2);
            }
            subExpr.add(s.substring(abre-1, fecha+1));
            output.getChildren().add(new Text("\n"));
        }else{
            subExpr.add(tokens.get(1).toString());
            Label l2 = new Label(tokens.get(1).toString());
            output.getChildren().add(l2);
        }
    }
    
    public TextFlow imprime(){
        return output;
    }
    
    public List<String> asStrings(){
        subExpr =  new ArrayList<>();
        for (List<Token> listToke : asLists()) {
            StringBuffer str = new StringBuffer();
            for (Token token : listToke)
                str.append(token.toString());
            subExpr.add(str.toString());
        }
        return subExpr;
    }

    public List<No<Token>> asNodes(){
                 
        List<No<Token>> nos = new ArrayList<>();
        for (List<Token> list : asLists()) {
            if(list.size() == 3){ //atomos : INIT ATOMO EOF
                No<Token> value = new No<>(list.get(1));
                String key = ArvoreBinaria.strPosOrdem(value);
                nos.add(value);
                aux2.put(key, value);
            }else{
                ArvoreSintatica av = new ArvoreSintatica(list);
                No<Token> value = av.getNos();
                String keyEsq = ArvoreBinaria.strPosOrdem(value.getEsq());
                String keyDir = ArvoreBinaria.strPosOrdem(value.getDir());                 
                
                if(!keyEsq.trim().isEmpty())
                    if(aux2.containsKey(keyEsq)){
                        aux2.get(keyEsq).setPai(value);
                        value.setEsq(aux2.get(keyEsq));
                    }

                if(!keyDir.trim().isEmpty())
                    if(aux2.containsKey(keyDir)){
                        aux2.get(keyDir).setPai(value);
                        value.setDir(aux2.get(keyDir));
                    }
                   
                nos.add(value);
                String key = ArvoreBinaria.strPosOrdem(value);
                aux2.put(key, value);
            }
        }
        aux2.clear();
        return nos;
    }

    public List<List<Token>> asLists() {
        List<List<Token>> list = new ArrayList<>();
        List<Token> aux  = new ArrayList<>();
        int abre = 0;
        while(abre < tokens.size()-1){
            int i = 0;
            for (int fecha = abre; tokens.get(abre).compareTToken(TipoToken.ABRE_PARENTESES) && fecha < tokens.size(); fecha++) {
                if (tokens.get(fecha).compareTToken(TipoToken.ABRE_PARENTESES)) {
                    i++;
                }else if (tokens.get(fecha).compareTToken(TipoToken.FECHA_PARENTESES)) {
                    i--;
                }else if (tokens.get(fecha).compareTToken(TipoToken.ATOMO) && !aux.contains(tokens.get(fecha))) {
                    List<Token> a = new ArrayList<>();
                    aux.add(tokens.get(fecha));
                    a.add(tokens.get(fecha));
                    a.add(new Token(TipoToken.EOF));
                    a.add(0, new Token(TipoToken.INIT));
                    list.add(a);
                }
                if (i == 0) {
                    List<Token> a = new ArrayList<>();
                    a.addAll(tokens.subList(abre, fecha+1));
                    a.add(new Token(TipoToken.EOF));
                    a.add(0, new Token(TipoToken.INIT));
                    list.add(a);
                    break;
                }
            }
            abre++;
        }
        
        Collections.sort (list, (List<Token> o1, List<Token> o2) -> {
            No<Token> n1 = ArvoreSintatica.toNoB(o1);
            No<Token> n2 = ArvoreSintatica.toNoB(o2);
            
            int p1 = ArvoreBinaria.contNos(n1);
            int p2 = ArvoreBinaria.contNos(n2);
            String q1 = ArvoreBinaria.strPosOrdem(n1);
            String q2 = ArvoreBinaria.strPosOrdem(n2);
            return (p1 > p2)?1:(p1 < p2)?-1:q2.compareTo(q1);
        });
        return list;
    }
    
    public List<String> getExpressoes(){
        return expressoes;
    } 

}