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
    List<Variavel> dados;

    public Escalonador(List<Operacao> operacoes, List<Variavel> dados) {
        this.operacoes = operacoes;
        this.dados = dados;
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
                unlockAll(operacao.getId());
                escalonadorWait();
            }

        }

        return transacoesGeradas;
    }

    public void escalonadorWait() {

        for (Operacao operacao : operacoes) {
            if (operacao.isWait()) {

                if (operacao.getTipoOperacao().equals("R")) {
                    if (lockS(operacao)) {
                        transacoesGeradas.add(operacao);
                    }
                } else if (operacao.getTipoOperacao().equals("W")) {
                    if (lockX(operacao)) {
                        transacoesGeradas.add(operacao);
                    }
                }
                operacao.setWait(false);
                break;
            }
        }

    }

    public boolean lockS(Operacao operacao) {

        if (operacao.getDado().getTipoLock().equals("U")) {
            operacao.getDado().setTipoLock("S");
            return true;
        } else if (operacao.getDado().getTipoLock().equals("S")) {
            return true;
        } else if (operacao.getDado().getTipoLock().equals("X")) {
            operacao.setWait(true);
            System.out.println("ADD wait S: " + operacao.getDado().getDado());
        }
        return false;
    }

    public boolean lockX(Operacao operacao) {

        if (operacao.getDado().getTipoLock().equals("U")) {
            operacao.getDado().setTipoLock("X");
            return true;
        } else {
            operacao.setWait(true);
            System.out.println("ADD wait X: " + operacao.getDado().getDado());
        }
        return false;
    }

    public void unlock(Operacao operacao) {

        if (operacao.getDado().getTipoLock().equals("X")) {
            operacao.getDado().setTipoLock("U");
        } else if (operacao.getDado().getTipoLock().equals("S")) {
            operacao.getDado().setTipoLock("U");
        }
    }

    public void unlockAll(int id) {
        for (Operacao operacao : transacoesGeradas) {
            if (operacao.getId() == id) {
                unlock(operacao);
            }
        }
    }

}
