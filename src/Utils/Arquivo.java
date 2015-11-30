package Utils;

import Model.Dado;
import Model.Operacao;
import Model.Transacao;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Document: Arquivo
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 26, 2015, 2:21:12 PM
 */
public class Arquivo {

    private FileReader fileRead;
    private BufferedReader buffArquivo;
    private Transacao transacao;
    private List<Transacao> transacoes;
    private Dado dado;
    private List<Dado> dados;
    private Operacao operacao;
    private List<Operacao> operacoes;

    public List<Transacao> readArquivo(String path, List<Dado> dados) {
        transacoes = new ArrayList<>();
        int idTransacao = 0;
        String linha = "";
        String t[];

        try {
            fileRead = new FileReader(path);
            buffArquivo = new BufferedReader(fileRead);

            linha = buffArquivo.readLine();
            while (linha != null) {
                idTransacao++;
                transacao = new Transacao();
                operacoes = new ArrayList<>();

                linha = linha.replaceAll(" ", "");
                t = linha.split(";");

                for (int i = 0; i < t.length - 1; i++) {

                    operacao = new Operacao();
                    dado = getDado(dados, t[i]);
                    

                    if (t[i].contains("W")) {
                        
                        operacao.setDado(dado);
                        operacao.setTipoOperacao('W');
                    } else if (t[i].contains("R")) {
                        operacao.setDado(dado);
                        operacao.setTipoOperacao('R');
                    } else if (t[i].contains("C")) {
                        operacao.setDado(null);
                        operacao.setTipoOperacao('C');
                    }
                    operacoes.add(operacao);
                }
                transacao.setIdTransacao(idTransacao);
                transacao.setOperacoes(operacoes);
                transacoes.add(transacao);
                linha = buffArquivo.readLine();

            }

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
        return transacoes;
    }

    public List<Dado> getDados(String path) {
        dados = new ArrayList<>();
        String linha = "";
        String aux[];

        try {
            fileRead = new FileReader(path);
            buffArquivo = new BufferedReader(fileRead);

            linha = buffArquivo.readLine();
            while (linha != null) {
                linha = linha.replaceAll(" ", "");
                aux = linha.split("");
                for (int i = 0; i < aux.length; i++) {
                    if (aux[i].equals("(")) {
                        dado = new Dado();
                        dado.setDado(aux[i + 1].charAt(0));
                        dado.setTipoLock('U');
                        if (!dados.contains(dado)) {
                            dados.add(dado);
                        }
                    }
                }
                linha = buffArquivo.readLine();
            }

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }

        return dados;
    }

    public Dado getDado(List<Dado> dados, String dado) {
        for (Dado d : dados) {
            String aux = String.valueOf(d.getDado());
            if (dado.contains(aux)){
                return d;
            }
        }
        return null;
    }

}
