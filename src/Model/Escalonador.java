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

    List<Operacao> operacoes;
    List<Operacao> transacoesGeradas;
    List<Operacao> listRead;
    List<Operacao> listWait;
    List<Variavel> dados;

    public Escalonador(List<Operacao> operacoes, List<Variavel> dados) {
        this.operacoes = operacoes;
        this.dados = dados;
        this.listRead = new ArrayList<>();
        this.listWait = new ArrayList<>();
    }

    public List<Operacao> escalonador() {
        transacoesGeradas = new ArrayList<>();

        for (Operacao operacao : operacoes) {
            if (operacao.getTipoOperacao().equals("R")) {
                if (lockS(operacao)) {
                    transacoesGeradas.add(operacao);
                }
            } else if (operacao.getTipoOperacao().equals("W")) {
                if (lockX(operacao)) {
                    transacoesGeradas.add(operacao);
                }
            } else if (operacao.getTipoOperacao().equals("C")) {
                transacoesGeradas.add(operacao);
                unlock(operacao);
            }

        }

        return transacoesGeradas;
    }

    public boolean lockS(Operacao operacao) {

//        char tipoLock = operacao.getDado().getTipoLock();

        if (operacao.getDado().getTipoLock().equals("U")) {
            operacao.getDado().setTipoLock("S");
            listRead.add(operacao);
            return true;
        } else if (operacao.getDado().getTipoLock().equals("S")) {
            listRead.add(operacao);
            return true;
        } else if (operacao.getDado().getTipoLock().equals("X")) {
            listWait.add(operacao);
        }
        return false;
    }

    public boolean lockX(Operacao operacao) {

//        char tipoLock = operacao.getDado().getTipoLock();

        if (operacao.getDado().getTipoLock().equals("U")) {
            operacao.getDado().setTipoLock("X");
            return true;
        } else {
            listWait.add(operacao);
        }
        return false;
    }

    public void unlock(Operacao operacao) {

//        char tipoLock = operacao.getTipoOperacao();

        if (operacao.getTipoOperacao().equals("X")) {
            operacao.getDado().setTipoLock("U");
            //desperta filaoperacao Wait
        } else if (operacao.getTipoOperacao().equals("U")) {
            listRead.remove(operacao);
            if (listRead.isEmpty()) {
                operacao.getDado().setTipoLock("U");
                //desperta fila Wait
            }
        }
    }

    public void unlock(Transacao transacao) {
        for (Operacao operacao : transacao.getOperacoes()) {
            unlock(operacao);
        }
    }

}
