package Model;

/**
 * Document: Operacao
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 29, 2015, 8:38:34 PM
 */
public class Operacao {
    
    private Variavel dado;
    private String tipoOperacao;
    private int id;
    private boolean wait;

    public Variavel getDado() {
        return dado;
    }

    public void setDado(Variavel dado) {
        this.dado = dado;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }
    

}
