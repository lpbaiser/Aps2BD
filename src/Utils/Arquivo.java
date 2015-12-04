package Utils;

import Model.Variavel;
import Model.Operacao;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Document: Arquivo
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com> & Emanuel Mazzer <emanuelgiroldo@gmail.com>
 * @since : Nov 26, 2015, 2:21:12 PM
 */
public class Arquivo {

    private FileReader fileRead;
    private BufferedReader buffArquivo;
    private Variavel variavel;
    private List<Variavel> variaveis;
    private Operacao operacao;
    private List<Operacao> operacoes;

    public List<Operacao> readAgenda(String path, List<Variavel> dados) {
        String linha = "";
        String t[];
        int id;

        try {
            fileRead = new FileReader(path);
            buffArquivo = new BufferedReader(fileRead);

            linha = buffArquivo.readLine();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }
        while (linha != null) {

            operacoes = new ArrayList<>();

            linha = linha.replaceAll(" ", "");
            t = linha.split(";");

            for (int i = 0; i < t.length; i++) {

                operacao = new Operacao();
                variavel = getVariavel(dados, t[i]);
                char c = t[i].charAt(1);
                id = Character.getNumericValue(c);
                if (t[i].contains("(")) {

                    if (t[i].contains("W")) {
                        operacao.setVariavel(variavel);
                        operacao.setTipoOperacao("W");
                    } else if (t[i].contains("R")) {
                        operacao.setVariavel(variavel);
                        operacao.setTipoOperacao("R");
                    }
                } else {
                    operacao.setVariavel(variavel);
                    operacao.setTipoOperacao("C");
                }
                operacao.setId(id);
                operacao.setWait(false);
                operacoes.add(operacao);
            }

            try {
                linha = buffArquivo.readLine();
            } catch (Exception ex) {
                System.out.println("ERRO: " + ex.getMessage());
            }

        }

        return operacoes;
    }

    public List<Variavel> getVariaveis(String path) {
        variaveis = new ArrayList<>();
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
                    variavel = new Variavel();
                    if (aux[i].contains("(")) {
                        String p[];
                        aux[i] = aux[i].replace(')', '(');
                        p = aux[i].split("\\(");
                        variavel.setDado(p[1]);
                        variavel.setTipoLock("U");

                        if (!variaveis.contains(variavel)) {
                            variaveis.add(variavel);
                        }
                    } else if (aux[i].contains("C")) {
                        variavel.setDado(aux[i]);
                        variavel.setTipoLock("C");
                        if (!variaveis.contains(variavel)) {
                            variaveis.add(variavel);
                        }
                    }
                }
                linha = buffArquivo.readLine();
            }

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
            return null;
        }

        return variaveis;
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

    public List<Operacao> writeAgenda(String caminho,List<Operacao> operacao) {
        try {
            BufferedWriter buffwirter = new BufferedWriter(new FileWriter("../"+caminho));
            for (Operacao op :operacao ) {
                if(op.getTipoOperacao().equals("C")){
                    buffwirter.append(op.getVariavel().getDado()+";");
                }else{
                    buffwirter.append(op.getTipoOperacao()+op.getId()+"("+op.getVariavel().getDado()+")"+";");
                }

            }
            buffwirter.append("\n");
            buffwirter.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        return null;
    }

}
