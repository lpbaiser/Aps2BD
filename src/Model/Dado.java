package Model;

/**
 * Document: Dado
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 29, 2015, 8:19:11 PM
 */
public class Dado {

    private char dado;
    private char tipoLock;

    public char getDado() {
        return dado;
    }

    public void setDado(char dado) {
        this.dado = dado;
    }

    public char getTipoLock() {
        return tipoLock;
    }

    public void setTipoLock(char tipoLock) {
        this.tipoLock = tipoLock;
    }

    @Override
    public boolean equals(Object obj) {
        Dado dado = (Dado) obj;
        if (this.dado == dado.getDado()) {
            return true;
        }
        return false;
//        return ((Simbolo) obj).terminal == this.terminal && ((Simbolo) obj).variavel == this.variavel;
    }

}
