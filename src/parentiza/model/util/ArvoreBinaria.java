/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parentiza.model.util;

import java.util.ArrayList;
import java.util.List;
import parentiza.model.Token;

/**
 *
 * @author User
 */
public class ArvoreBinaria {

 
    private List<Token> tokens = new ArrayList<>();
    private No<Token> raiz;
    private int i;

    public ArvoreBinaria(){
        tokens = new ArrayList<>();
        raiz = null;
        i = 0;
    }
    
    public ArvoreBinaria(Token token){
        
        No no = new No(token);
        raiz = no;
    }
    
    public ArvoreBinaria(No<Token> no, Token token){
        no.setEsq(new No(token));
    }
     
    public ArvoreBinaria(Token token, No esquerda, No direita){
        No<Token> no = new No(token);
        no.setEsq(esquerda);
        no.setDir(direita);
    }
       
    
    /**
     * @return the raiz
     */
    public No<Token> getRaiz() {
        return raiz;
    }
    
    public static void imprimirPreOrdem(No<Token> n){
        try {   
            System.out.print(n.getValor()+" ");
            if(n.getEsq() != null)
                imprimirPreOrdem(n.getEsq());
            if(n.getDir()!= null)    
                imprimirPreOrdem(n.getDir());
        } catch (Exception e) {
        }
    }
    
    public static void imprimirEmOrdem(No<Token> n){
        try {
            if(n.getEsq() != null){
                imprimirEmOrdem(n.getEsq());
            }
            if (n.getValor() != null)
            System.out.print(n.getValor()+" ");
            if(n.getDir()!= null){
                imprimirEmOrdem(n.getDir());
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    public static void imprimirPosOrdem(No<Token> n){
        try {   
            if(n.getEsq() != null)
                imprimirPosOrdem(n.getEsq());
            if(n.getDir()!= null)    
                imprimirPosOrdem(n.getDir());
            System.out.print(n.getValor()+" ");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
      
    public static String strEmOrdem(No<Token> n){
        String s = ""; 
        try {   
            if(n == null) return "";
                String esq = strEmOrdem(n.getEsq());
                String dir = strEmOrdem(n.getDir());
                s = esq + n.getValor().toString() + dir;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return s;
    }
      
    public static String strPreOrdem(No<Token> n){
        String s = ""; 
        try {   
            if(n == null) return "";
                String esq = strPreOrdem(n.getEsq());
                String dir = strPreOrdem(n.getDir());
                s = n.getValor().toString() + esq + dir;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return s;
    }
  
    public static String strPosOrdem(No<Token> n){
        String s = ""; 
        try {   
            if(n == null) return "";
                String esq = strPosOrdem(n.getEsq());
                String dir = strPosOrdem(n.getDir());
                s = esq + dir + n.getValor().toString();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return s;
    }
    
    public List<Token> preOrdem(No<Token> n){
        try {
            tokens.add(n.getValor());
            if(n.getEsq() != null)
                preOrdem(n.getEsq());
            if(n.getDir()!= null)    
                preOrdem(n.getDir());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return tokens;
    }
    
    public List<Token> emOrdem(No<Token> n){
        try {
            if(n != null){
                if(n.getEsq() != null)
                    emOrdem(n.getEsq());
                tokens.add(n.getValor());
                if(n.getDir()!= null)
                    emOrdem(n.getDir());
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return tokens;
    }
        
    public List<Token> posOrdem(No<Token> n){
        try {
            if(n.getEsq() != null)
                posOrdem(n.getEsq());
            if(n.getDir()!= null)    
                posOrdem(n.getDir());
            tokens.add(n.getValor());
        } catch (Exception e) {
            e.getStackTrace();
        }
        return tokens;
    }
    
    public static int contNos(No<Token> n){
        try {   
            if(n == null) return 0;
            int esq = contNos(n.getEsq());
            int dir = contNos(n.getDir());
            return esq + dir + 1;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }
    
    public int contOperadores(No<Token> n){
        try {
            if(n.getEsq() != null)
                contOperadores(n.getEsq());
            if(n.getDir()!= null)    
                contOperadores(n.getDir());
            if(n.getValor().compareTToken(TipoToken.OPERADOR))
                i++;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return i;
    }
        
    public int contOperandos(No<Token> n){
        try {
            if(n.getEsq() != null)
                contOperandos(n.getEsq());
            if(n.getDir()!= null)    
                contOperandos(n.getDir());
            if(!n.getValor().compareTToken(TipoToken.OPERADOR))
                i++;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return i;
    }
      
    public static int altura(No<Token> n){
        try {   
            if(n == null) return 0;
            int esq = altura(n.getEsq());
            int dir = altura(n.getDir());
            if(esq > dir)
                return esq + 1;
            return dir + 1;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return 0;
    }
    
    No inserir(No<Token> no, Token token) {
        if(no == null)
            return new No<Token>(token);
        if(raiz == null){
            if(no.getValor() != null)
                raiz = no;
        }
        if(no.getValor() != null && no.getDir() == null){
            no.setDir(new No(token));
            return no.getDir(); 
        }
        if(no.getEsq() == null){
            no.setEsq(new No(token));
            return no.getEsq();
        }
        if (token == null){
            return inserir(no.getEsq(), token);
        }
        if(no.getDir() == null){
            no.setDir(new No(token));
            return no.getDir();        
        }
        return inserir(no.getDir(), token);
    }
    
    public static No<Token> buscar(No<Token> no,Token chave) {
        if (no == null) return null;
        if (no.getValor() != chave) {
            No<Token> aux = buscar(no.getEsq(), chave);
            if(aux != null)
                return aux;
            return buscar(no.getDir(), chave);
        }
        return no;
    }
       
}
