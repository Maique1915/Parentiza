package parentiza.model.util;

public class No<T> {

    private T valor;   //valor
    private No esq;   //folha esquerda
    private No dir;   //folha direita
    private No pai;

    public No(T valor, No esq, No dir,No pai) {
        this.valor = valor;
        this.esq = esq;
        this.dir = dir;
        this.pai = pai;
    }
    public No(T valor, No esq, No dir) {
        this.valor = valor;        
        this.esq = esq;
        this.dir = dir;
        this.pai = null;
    }
       
    public No(T valor, No esq) {
        this.valor = valor;
        this.esq = esq;
        this.dir = null;
        this.pai = null;
    }
    
    
    public No(T valor) {
        this.valor = valor;
        this.esq = null;
        this.dir = null;
        this.pai = null;
    }
       
    public No() {
        this.valor = null;
        this.esq = null;
        this.dir = null;
        this.pai = null;
    }
    
    /**
     * @return the valor
     */
    public T getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(T valor) {
        this.valor = valor;
    }

    /**
     * @return the esq
     */
    public No getEsq() {
        return esq;
    }

    /**
     * @param esq the esq to set
     */
    public void setEsq(No esq) {
        this.esq = esq;
    }
    
    /**
     * @return the dir
     */
    public No getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(No dir) {
        this.dir = dir;
    }
  
    /**
     * @return the pai
     */
    public No getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(No pai) {
        this.pai = pai;
    }
    
}
