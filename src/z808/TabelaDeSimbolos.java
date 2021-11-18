package z808.z808;
import java.util.HashMap;

public class TabelaDeSimbolos {
    private HashMap<String, String[]> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public HashMap<String, String[]> getTabela() {
        return tabela;
    }

    public void setTabela(HashMap<String, String[]> tabela) {
        this.tabela = tabela;
    }

    public void adicionaElemento(String chave, String[] elemento){
        this.tabela.put(chave, elemento);
    }
}