package Model;

/**
 * Document: Dado
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 29, 2015, 8:19:11 PM
 */
public class Variavel {

    private String dado;
    private String tipoLock;

    public String getTipoLock() {
        return tipoLock;
    }

    public void setTipoLock(String tipoLock) {
        this.tipoLock = tipoLock;
    }

  
    @Override
    public boolean equals(Object obj) {
        Variavel dado = (Variavel) obj;
        if (this.dado.equals(dado.getDado())) {
            return true;
        }
        return false;
//        return ((Simbolo) obj).terminal == this.terminal && ((Simbolo) obj).variavel == this.variavel;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

}
