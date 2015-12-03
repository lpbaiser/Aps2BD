package Model;

import java.util.List;

/**
 * Document: Transacao
 *
 * @author : Leonardo Baiser <lpbaiser@gmail.com>
 * @since : Nov 26, 2015, 2:36:16 PM
 */
public class Transacao {

    private List<Operacao> operacoes;
    private String commit;


    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }


  
    
  
}
