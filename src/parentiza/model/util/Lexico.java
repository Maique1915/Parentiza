package parentiza.model.util;


import java.util.ArrayList;
import java.util.List;
import parentiza.model.Token;

public class Lexico {
    
    private List<Token> tokens = new ArrayList<Token>();
    //
    private Token token;
    private String compara = ""; //String utilizada para pegar tokens individualmente
    final char[] keys = new char[]{',',';','¬','∀','∃','∧','∨','→','↔','⊢','(',')'};
    private Sintatico l;
    
    public List<Token> SCANNER(String text) {
        text += " ";
        
        for (int j = 0; j < text.length(); j++) {
            identificaTokens(text.charAt(j));
        }
        tokens.add(0, new Token(TipoToken.INIT));
        tokens.add(new Token(TipoToken.EOF));
        printTokens();
        return tokens;
    }
    

    public List<Token> getExpressoes(){
        return tokens;
    }
        
    private void identificaTokens(char charAt) {

        switch(charAt){
            case ' ':
                if(!compara.trim().equals("")){
                    geraTokens(TipoToken.ESPACO, charAt);
                }
                break;
            case '¬':
                geraTokens(TipoToken.NAO, charAt);
                break;
            case '∧':
                geraTokens(TipoToken.E, charAt);
                break;
            case '∨':
                geraTokens(TipoToken.OU, charAt);
                break;
            case '→':
                geraTokens(TipoToken.IMPLICA, charAt);
                break;
            case '↔':
                geraTokens(TipoToken.D_IMPLICA, charAt);
                break;
            case '(':
                geraTokens(TipoToken.ABRE_PARENTESES, charAt);
                break;
            case ')':
                geraTokens(TipoToken.FECHA_PARENTESES, charAt);
                break;
            default:
                compara = compara.concat(Character.toString(charAt));
                break;
        }
    }

    private void geraTokens(TipoToken id, char charAt ) {
        
        if(compara.length()> 0){
            token = new Token(TipoToken.ATOMO, compara.trim(), " ");
            tokens.add(token);
            compara = "";
            if(id != TipoToken.ESPACO)
                    geraTokens(id, charAt);
        }else{
            if(id == TipoToken.ABRE_PARENTESES || id == TipoToken.FECHA_PARENTESES)
                token = new Token(id, String.valueOf(charAt), id.name());
            else
                token = new Token(TipoToken.OPERADOR, String.valueOf(charAt), id.name());
            tokens.add(token);
            compara = "";
        }
    }
    
    private boolean ehNumerico(String str){ //verifica se toda a string é um numero
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    
    private boolean ehString(String str){ //verifica se string começa com letras
        return str.matches("[a-zA-z]+\\W+");
    }
    
    private boolean ehNumerico(char c){ //verifica se toda a string é um numero
        return Character.isDigit(c);
    }
    
    private boolean ehLetra(char c){ //verifica se string começa com letras
        return Character.isLetter(c);

    }
    
    private void printTokens(){
        tokens.forEach((token1) -> {
            System.out.println("<"+token1.getTipoToken()+","+token1.getLexema()+">");
        });
        System.out.println("gfdf");
    }

    public void printTokens(List<Token> tokens) {
        tokens.forEach((token1) -> {
            System.out.println("<"+token1.getTipoToken()+","+token1.getLexema()+">");
        });
        System.out.println("gfdf");
    }

}