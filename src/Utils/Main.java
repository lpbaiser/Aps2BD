package Utils;

import Model.Variavel;
import Model.Operacao;
import Model.Transacao;
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
            System.out.println("Dado: "+dado.getDado()+" tipo: " + dado.getTipoLock());

        }
        List<Operacao> list = a.readAgenda("src/Entrada/transacoes.txt", dados);

        for (Operacao op : list) {
            if (op.getDado() != null) {

                System.out.println(" --tipo : " + op.getTipoOperacao() + " dado: " + op.getDado().getDado());
            } else {
                System.out.println(" --tipo : " + op.getTipoOperacao() + " dado: NULL");
            }
        }

    }

}
