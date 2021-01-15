package parentiza.model;

import java.util.HashMap;
import java.util.Map;
import parentiza.model.util.TipoToken;

public class Token implements Comparable<TipoToken> {
        
    private TipoToken tipoToken;
    private int precedencia;
    private String lexema;
    private String valor;
    
     private static final Map<TipoToken, String> map = new HashMap(){{
        put(TipoToken.NAO, "¬");
        put(TipoToken.E, "∧");
        put(TipoToken.OU, "∨");
        put(TipoToken.D_IMPLICA, "↔");
        put(TipoToken.IMPLICA, "→");
        put(TipoToken.ABRE_PARENTESES, "(");
        put(TipoToken.ATOMO, "atomo");
        put(TipoToken.INIT, "");
        put(TipoToken.EOF, "");
        put(TipoToken.ESPACO, " ");
        put(TipoToken.FECHA_PARENTESES, ")");
    }};
  
    
    public Token(TipoToken tipoToken, String lexema, String valor){
        this.tipoToken = tipoToken;
        this.lexema  = lexema;
        this.valor = valor;
    }
         
    public Token(TipoToken tipoToken, String lexema){
        this.tipoToken = tipoToken;
        this.lexema  = lexema;
    }
      
    public Token(TipoToken tipoToken){
        this.tipoToken = tipoToken;
        this.lexema  = "";
    }
    
     /**
     * @return the tipotoken
     */
    public TipoToken getTipoToken() {
        return tipoToken;
    }

    /**
     * @return the precedencia
     */
    public int getPrecedencia() {
        return precedencia;
    }

    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }
    
    public void setLexema(String lexema){
        this.lexema = lexema;
    }
 
    @Override
    public String toString(){
            return getLexema();
    }
    
    public void dados(){
        System.out.println("DEBUG: "
                + "\nToken: " + this.getTipoToken()
                + "\nLEXEMA: " + this.getLexema()+ "\n");
    }

    public Token() {
    }

    @Override
    public int compareTo(TipoToken t) {
        return (t != null && t.equals(this.getTipoToken())?0:-1);
    }

    public static String getValorToken(TipoToken t){
        return map.get(t);
    }
    
    public boolean compareLexema(Token t) {
        return t != null && (this.getLexema().equals(t.getLexema()));
    }
    
    public boolean compareTToken(Token t) {
        return t != null && (this.getTipoToken().equals(t.getTipoToken()));
    }
    
    public boolean compareLexema(TipoToken t) {
        return t != null && (getLexema().equals(map.get(t)));
    }

    
    public boolean compareTToken(TipoToken t) {
        return (this.getTipoToken().equals(t));
    }

    public boolean isEmpty() {
        return tipoToken == null;
    }
    
}
