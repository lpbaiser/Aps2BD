package Utils;

import Model.Variavel;
import Model.Operacao;
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
    private Variavel dado;
    private List<Variavel> dados;
    private Operacao operacao;
    private List<Operacao> operacoes;

    public List<Operacao> readAgenda(String path, List<Variavel> dados) {
        String linha = "";
        String t[];
        operacoes = new ArrayList<>();

        try {
            fileRead = new FileReader(path);
            buffArquivo = new BufferedReader(fileRead);

            linha = buffArquivo.readLine();
            while (linha != null) {

                linha = linha.replaceAll(" ", "");
                t = linha.split(";");

                for (int i = 0; i < t.length; i++) {

                    operacao = new Operacao();
                    dado = getVariavel(dados, t[i]);

                    if (t[i].contains("W")) {
                        operacao.setDado(dado);
                        operacao.setTipoOperacao("W");
                    } else if (t[i].contains("R")) {
                        operacao.setDado(dado);
                        operacao.setTipoOperacao("R");
                    } else if (t[i].contains("C")) {
                        operacao.setDado(dado);
                        operacao.setTipoOperacao("C");
                    }
                    operacoes.add(operacao);
                }
                linha = buffArquivo.readLine();

            }

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
        return operacoes;
    }

    public List<Variavel> getVariaveis(String path) {
        dados = new ArrayList<>();
        String linha = "";
        String aux[];

        try {
            fileRead = new FileReader(path);
            buffArquivo = new BufferedReader(fileRead);

            linha = buffArquivo.readLine();
            while (linha != null) {
                linha = linha.replaceAll(" ", "");
                aux = linha.split(";");
                for (int i = 0; i < aux.length; i++) {
                    if (aux[i].contains("(")) {
                        String p[];
                        aux[i] = aux[i].replace(')', '(');
                        p = aux[i].split("\\(");
                        dado = new Variavel();
                        dado.setDado(p[1]);
                        dado.setTipoLock("U");

                        if (!dados.contains(dado)) {
                            dados.add(dado);
                        }
                    } else if (aux[i].contains("C")) {
                        dado = new Variavel();
                        dado.setDado(aux[i]);
                        dado.setTipoLock("C");
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

    public Variavel getVariavel(List<Variavel> dados, String dado) {
        for (Variavel d : dados) {
            if (dado.contains("(")) {
                String p[];
                dado = dado.replace(')', '(');
                p = dado.split("\\(");
                dado = p[1];
            }
            if (dado.equals(d.getDado())) {
                return d;
            }
        }
        return null;
    }

    public List<Operacao> writeAgenda(){
        return null;
    }
    
    
}
