package Utils;

import Model.Variavel;
import Model.Escalonador;
import Model.Operacao;
import Model.Transacao;
import java.util.ArrayList;
import java.util.List;

/**
 * Document: Main
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 26, 2015, 3:23:32 PM
 */
public class Main {

    public static void main(String[] args) {

        Arquivo a = new Arquivo();
        List<Variavel> dados = a.getVariaveis("src/Entrada/transacoes.txt");
        for (Variavel dado : dados) {
//            System.out.println("Dado: "+dado.getDado()+" tipo: " + dado.getTipoLock());

        }
        List<Operacao> list = a.readAgenda("src/Entrada/transacoes.txt", dados);

        for (Operacao l : list) {

                System.out.println("tipo : " + l.getTipoOperacao() + " dado: " + l.getDado().getDado());
        }

        Escalonador e = new Escalonador(list, dados);
        List<Operacao> operacao = new ArrayList<>();
        operacao = e.escalonador();
        System.out.println("\n Escalonado");
        for (Operacao l : operacao) {
//            if (l.getTipoOperacao() == 'C') {
//                System.out.println("tipo : " + l.getTipoOperacao());
//            } else {
                System.out.println("tipo : " + l.getTipoOperacao() + " dado: " + l.getDado().getDado());
//            }
        }

    }

}
