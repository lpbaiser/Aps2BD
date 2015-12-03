package Utils;

import Model.Escalonador;
import Model.Variavel;
import Model.Operacao;
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
            System.out.println("Dado: "+dado.getDado()+" tipo: " + dado.getTipoLock());

        }
        List<Operacao> list = a.readAgenda("src/Entrada/transacoes.txt", dados);

        for (Operacao op : list) {

                System.out.println(" --tipo : " + op.getTipoOperacao() + " dado: " + op.getDado().getDado() + "  id: " + op.getId());
        }
        
        Escalonador e = new Escalonador(list, dados);
        
        System.out.println("\n\nEscalonador");
        List<Operacao> esc = e.escalonador();
        for (Operacao es : esc) {
            

                System.out.print(es.getTipoOperacao() + es.getId()+ "(" + es.getDado().getDado()  + "); ");
            
        }

        a.writeAgenda("AgendaEscalonada.txt", esc);
    }

}
