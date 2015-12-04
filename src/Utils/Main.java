package Utils;

import Model.Escalonador;
import Model.Variavel;
import Model.Operacao;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Document: Main
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 26, 2015, 3:23:32 PM
 */
public class Main {

    static List<Operacao> agenda = null;

    public static void main(String[] args) {

        Arquivo arquivo = null;
        List<Variavel> variaveis = null;
        Escalonador escalonador = null;

        int op = 0;
        do {
            System.out.println("Digite uma opção: \n 1 - Carregar um arquivo de agenda não escalonado\n "
                    + "2 - Escalonar agenda \n "
                    + "3 - Imprimir transações \n "
                    + "4 - Gerar arquivo com transações escalonadas \n "
                    + "5 - Sair \n : ");
            Scanner scanner = new Scanner(System.in);
            op = scanner.nextInt();
            if (op == 1) {
                System.out.println("Digite o caminho para o arquivo \n : ");
                String path = scanner.next();
                arquivo = new Arquivo();
                agenda = new ArrayList<>();
                variaveis = new ArrayList<>();
                variaveis = arquivo.getVariaveis(path);
                agenda = arquivo.readAgenda(path, variaveis);
                System.out.println("OK\n");
            } else if (op == 2) {
                if (agenda != null && !agenda.isEmpty()) {
                    escalonador = new Escalonador(agenda, variaveis);
                    agenda = new ArrayList<>();
                    agenda = escalonador.escalonar();
                    System.out.println("Agenda escalonada!\n");
                } else {
                    System.out.println("Nenhuma agenda recebida!");
                }
            } else if (op == 3) {
                imprimir();
            } else if (op == 4) {
                if (arquivo != null) {
                    System.out.println("Digite o nome e a extenção para o arquivo \n >>> ");
                    String path = scanner.next();
                    arquivo.writeAgenda(path, agenda);
                    System.out.println("Agenda gravada!\n");
                } else {
                    System.out.println("Nenhuma agenda recebida!");
                }
            } else if (op == 5) {
                System.out.println("bye!");
            }

        } while (op != 5);

//        Arquivo a = new Arquivo();
//        List<Variavel> dados = a.getVariaveis("src/Entrada/transacoes.txt");
//        
//        for (Variavel dado : dados) {
//            System.out.println("Dado: "+dado.getDado()+" tipo: " + dado.getTipoLock());
//
//        }
//        List<Operacao> list = a.readAgenda("src/Entrada/transacoes.txt", dados);
//
//        for (Operacao op : list) {
//
//                System.out.println(" --tipo : " + op.getTipoOperacao() + " dado: " + op.getVariavel().getDado() + "  id: " + op.getId());
//        }
//        
//        Escalonador e = new Escalonador(list, dados);
//        
//        System.out.println("\n\nEscalonador");
//        List<Operacao> esc = e.escalonador();
//        for (Operacao es : esc) {
//            
//
//                System.out.print(es.getTipoOperacao() + es.getId()+ "(" + es.getVariavel().getDado()  + "); ");
//            
//        }
//
//        a.writeAgenda("AgendaEscalonada.txt", esc);
    }

    public static void imprimir() {
        if (agenda.isEmpty()) {
            System.out.println("Agenda vazia!");
            return;
        }
        System.out.println("Transações");
        for (Operacao operacao : agenda) {
            System.out.print(operacao.getTipoOperacao() + operacao.getId() + "(" + operacao.getVariavel().getDado() + "); ");
        }
        System.out.println("\nEnd Transações");
    }

}
