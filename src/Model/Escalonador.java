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

    private List<Operacao> operacoes;
    private List<Operacao> agenda;
    private List<Variavel> variaveis;

    public Escalonador(List<Operacao> operacoes, List<Variavel> dados) {
        this.operacoes = operacoes;
        this.variaveis = dados;
        
    }

    public List<Operacao> escalonar() {
        agenda = new ArrayList<>();

        for (Operacao operacao : operacoes) {
            if (operacao.getTipoOperacao().equals("R")) {
                if (lockS(operacao)) {
                    agenda.add(operacao);
                }
            } else if (operacao.getTipoOperacao().equals("W")) {
                if (lockX(operacao)) {
                    agenda.add(operacao);
                }
            } else if (operacao.getTipoOperacao().equals("C")) {
                agenda.add(operacao);
                unlockAll(operacao.getId());
                escalonadorWait();
            }

        }

        return agenda;
    }

    private void escalonadorWait() {

        for (Operacao operacao : operacoes) {
            if (operacao.isWait()) {

                if (operacao.getTipoOperacao().equals("R")) {
                    if (lockS(operacao)) {
                        agenda.add(operacao);
                    }
                } else if (operacao.getTipoOperacao().equals("W")) {
                    if (lockX(operacao)) {
                        agenda.add(operacao);
                    }
                }
                operacao.setWait(false);
                break;
            }
        }

    }

    private boolean lockS(Operacao operacao) {

        if (operacao.getVariavel().getTipoLock().equals("U")) {
            operacao.getVariavel().setTipoLock("S");
            return true;
        } else if (operacao.getVariavel().getTipoLock().equals("S")) {
            return true;
        } else if (operacao.getVariavel().getTipoLock().equals("X")) {
            operacao.setWait(true);
        }
        return false;
    }

    private boolean lockX(Operacao operacao) {

        if (operacao.getVariavel().getTipoLock().equals("U")) {
            operacao.getVariavel().setTipoLock("X");
            return true;
        } else {
            operacao.setWait(true);
        }
        return false;
    }

    private void unlock(Operacao operacao) {

        if (operacao.getVariavel().getTipoLock().equals("X")) {
            operacao.getVariavel().setTipoLock("U");
        } else if (operacao.getVariavel().getTipoLock().equals("S")) {
            operacao.getVariavel().setTipoLock("U");
        }
    }

    private void unlockAll(int id) {
        for (Operacao operacao : agenda) {
            if (operacao.getId() == id) {
                unlock(operacao);
            }
        }
    }
    
    public void imprimir(){
        for (Operacao operacao : agenda) {
                System.out.print(operacao.getTipoOperacao() + operacao.getId()+ "(" + operacao.getVariavel().getDado()  + "); ");
        }
    }

}
