package Utils;

import Model.Dado;
import Model.Escalonador;
import Model.Operacao;
import Model.Transacao;
import java.util.ArrayList;
import java.util.List;

/**
 * Document: Main
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 26, 2015, 3:23:32 PM
 */
public class Main {
    
    public static void main(String[] args) {
        
        Arquivo a = new Arquivo();
        List<Dado> dados = a.getDados("src/Entrada/transacoes.txt");
        for (Dado dado : dados) {
//            System.out.println("Dado: "+dado.getDado()+" tipo: " + dado.getTipoLock());
            
        }
        List<Transacao> list = a.readArquivo("src/Entrada/transacoes.txt", dados);
        
        for (Transacao l : list) {
            System.out.println("idTransacao: "  + l.getIdTransacao());
            System.out.println("Lista Operaçoes: size: " + l.getOperacoes().size());
            
            for (Operacao r : l.getOperacoes()) {
                System.out.println("tipo : "+r.getTipoOperacao() + " dado: "+r.getDado().getDado());
            }
        }
        
        Escalonador e = new Escalonador(list, dados);
        List<Operacao> operacao = new ArrayList<>();
        operacao = e.escalonador();
        for (Operacao op : operacao) {
            System.out.println("Op: "+op.getTipoOperacao() + " ("+ op.getDado().getDado()+ ")");
            
        }
        
    }

}
