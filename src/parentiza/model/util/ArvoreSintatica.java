/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parentiza.model.util;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import parentiza.model.Token;

/**
 *
 * @author User
 */
public class ArvoreSintatica {

    private No<Token> noB;
    private Deque<No> pilha  = new LinkedList();
    private ArvoreBinaria a;
    
    public ArvoreSintatica(List<Token> tokens) {
                
        
        for (Token token : tokens)
            gera(token);
    }
    
    public No<Token> getNos(){
        return noB;
    }

    private void gera(Token token) {
        switch (token.getTipoToken()) {
            case FECHA_PARENTESES:
                noB = pilha.pop();

                break;
            case ABRE_PARENTESES:
                if (pilha.isEmpty()) {
                    a = new ArvoreBinaria(null);
                    pilha.push(a.getRaiz());
                }else{
                    pilha.push(a.inserir(pilha.peek(),null));
                }
                break;
            case ATOMO:
                a.inserir(pilha.peek(),token);
                break;
            case OPERADOR:
                pilha.peek().setValor(token);
                break;
            default:
                break;
        }
    }
    
      public static No<Token> toNoB(List<Token> list){
        No<Token> noB = null;
        if(list.size() == 3){//atomos : INIT ATOMO EOF
            noB = new No<>(list.get(1));
        }else{
            ArvoreSintatica av = new ArvoreSintatica(list);
            noB = av.getNos();
        }
        return noB;
    }


}
