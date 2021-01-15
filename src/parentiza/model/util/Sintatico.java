package parentiza.model.util;

import java.util.ArrayList;
import java.util.List;
import parentiza.model.Token;

public class Sintatico {
    private List<Token> tokens;
    private Token tokensAux;
    private List<List<Token>> expressoes;
    private Token tokenAtual; //o ultimo token a ser lido da fila
    private int linha = 1;
    private boolean esquerda = false;
    private int prox = 1;
    private int cont = 0;
    
    public Sintatico(List<Token> tokens){
        this.tokens = tokens;
        tokensAux = new Token();
        tokenAtual = this.tokens.get(prox);
        expressoes = new ArrayList();
        
    }
   
    public boolean verifica(){
        return verifica_expressao();
    }

    private boolean verifica_expressao(){
        if(tokenAtual.compareTToken(TipoToken.ATOMO)){
            System.out.println(tokenAtual.getTipoToken()+" 1");
             proxToken();
            return verifica_operador();
        }else if(tokenAtual.compareLexema(TipoToken.NAO)){
            System.out.println(tokenAtual.getTipoToken()+" 2");
            proxToken();
            return verifica_expressao();
        }else if(tokenAtual.compareTToken(TipoToken.ABRE_PARENTESES)){
            System.out.println(tokenAtual.getTipoToken()+" 3");
            cont++;
            proxToken();
            return verifica_expressao();
        }else if(tokenAtual.compareTToken(TipoToken.FECHA_PARENTESES)){
            if(cont <= 0){
                erro_sintatico("sitático","Parentese não aberto", "(");
                return false;
            }
            System.out.println(tokenAtual.getTipoToken()+" 4");            
            cont--;
            proxToken();
           return verifica_operador();
        }else if(!tokenAtual.compareTToken(TipoToken.EOF)){
            System.out.println(" 66666666666");
            erro_operadores();
            return false;
        }else{
             if(cont > 0){
                erro_sintatico("sitático","Parentese não fechado", ")");
                System.out.println(tokenAtual.getTipoToken()+" 6.1");
                return false;
            }
            erro_sintatico("sitático","token inesperado",
            Token.getValorToken(TipoToken.ATOMO),
            Token.getValorToken(TipoToken.ABRE_PARENTESES),
            Token.getValorToken(TipoToken.NAO));
            System.out.println(tokenAtual.getTipoToken()+" 6.2");
        }
        return false;
    }
    
    private boolean verifica_operador(){
        if(tokenAtual.compareTToken(TipoToken.OPERADOR)
                && !tokenAtual.compareLexema(TipoToken.NAO)){
                System.out.println(tokenAtual.getTipoToken()+" 5");
                proxToken();
                return verifica_expressao();
            }else if(tokenAtual.compareTToken(TipoToken.EOF)){
                if (tokensAux.compareTToken(TipoToken.OPERADOR)) {
                    System.out.println(tokenAtual.getTipoToken()+" 8");
                    erro_sintatico("sitático","token inesperado",
                    Token.getValorToken(TipoToken.ATOMO),
                    Token.getValorToken(TipoToken.ABRE_PARENTESES),
                    Token.getValorToken(TipoToken.NAO));
                    System.out.println(tokenAtual.getTipoToken()+" 6.2");

                    return false;
                }
                tokensAux = tokenAtual;
                tokensAux = new Token();
                System.out.println(tokenAtual.getTipoToken()+" 6");
                return true;
            }else{
                return verifica_expressao();
            }
    }
    
    private void proxToken(){
        prox++;
        tokensAux = tokenAtual;
        tokenAtual = tokens.get(prox);
    }
    
    private void erro_operadores(){
            System.out.println(tokenAtual.getTipoToken()+" 22");
            System.out.println(tokenAtual.getTipoToken().equals(TipoToken.EOF));
            System.out.println((!esquerda));

            erro_sintatico("sitático","token inesperado",
            Token.getValorToken(TipoToken.D_IMPLICA),
            Token.getValorToken(TipoToken.E),
            Token.getValorToken(TipoToken.IMPLICA),
            Token.getValorToken(TipoToken.OU));                
    }
    
    private void erro_sintatico(String tipo, String ctx, String... exp) {
        String msg = "Erro: Símbolo ";

        if(!tokenAtual.compareTToken(TipoToken.EOF)){
            msg += tokenAtual.toString();
        }else{
            msg += tokens.get(prox -1).toString();
        }
        msg +=" inesperado";
        
        ctx = "Esperando: ";
        for(int i = 0; i< exp.length; i++){
            ctx += "'"+exp[i]+"'";
            if(i< exp.length -1)
                ctx +=", ";
        }
        ctx += ".";
        new NovaException(tipo,ctx,msg);
    }

}
