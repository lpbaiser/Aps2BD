package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Document: Escalonador
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 29, 2015, 8:17:06 PM
 */
public class Escalonador {

    List<Transacao> transacoes;
    List<Operacao> transacoesGeradas;
    List<Operacao> listRead;
    List<Operacao> listWait;
    List<Dado> dados;

    public Escalonador(List<Transacao> transacoes, List<Dado> dados) {
        this.transacoes = transacoes;
        this.dados = dados;
        this.listRead = new ArrayList<>();
        this.listWait = new ArrayList<>();
    }

    public List<Operacao> escalonador() {
        transacoesGeradas = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            for (Operacao operacao : transacao.getOperacoes()) {
                if (operacao.getTipoOperacao() == 'R') {
                    if (lockS(operacao)) {
                        transacoesGeradas.add(operacao);
                    }
                } else if (operacao.getTipoOperacao() == 'W') {
                    if (lockX(operacao)) {
                        transacoesGeradas.add(operacao);
                    }
                }

            }
        }

        return transacoesGeradas;
    }

    public boolean lockS(Operacao operacao) {

        char tipoLock = operacao.getDado().getTipoLock();

        if (tipoLock == 'U') {
            operacao.getDado().setTipoLock('S');
            listRead.add(operacao);
            return true;
        } else if (tipoLock == 'S') {
            listRead.add(operacao);
            return true;
        } else if (tipoLock == 'X') {
            listWait.add(operacao);
        }
        return false;
    }

    public boolean lockX(Operacao operacao) {

        char tipoLock = operacao.getDado().getTipoLock();

        if (tipoLock == 'U') {
            operacao.getDado().setTipoLock('X');
            return true;
        } else {
            listWait.add(operacao);
        }
        return false;
    }

    public void unlock(Operacao operacao) {

        char tipoLock = operacao.getDado().getTipoLock();

        if (tipoLock == 'X') {
            operacao.getDado().setTipoLock('U');
            //desperta fila Wait
        } else if (tipoLock == 'S') {
            listRead.remove(operacao);
            if (listRead.isEmpty()) {
                operacao.getDado().setTipoLock('U');
                //desperta fila Wait
            }
        }
    }

}
