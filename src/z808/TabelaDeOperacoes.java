package z808.z808;

import java.util.HashMap;

public class TabelaDeOperacoes{
    private HashMap<String, String> tabela;

    public TabelaDeOperacoes() {
        this.tabela = new HashMap<>();

        tabela.put("ADD", "00000011");
        tabela.put("DIV", "11110111");
        tabela.put("SUB", "00101011");
        tabela.put("MUL", "11111111");
        tabela.put("AND", "00100011");
        tabela.put("NOT", "11100111");
        tabela.put("OR", "00001011");
        tabela.put("XOR", "00110011");
        tabela.put("JZ", "01110100");
        tabela.put("JNZ", "01110101");
        tabela.put("JMP", "11101011");
        tabela.put("AX", "11000000");
        tabela.put("DX", "11000010");
        tabela.put("SI", "11110110");
        tabela.put("MOV", "10001011");
    }

    public HashMap<String, String> getTabela() {
        return tabela;
    }

    public void setTabela(HashMap<String, String> tabela) {
        this.tabela = tabela;
    }

    public void adicionaElemento(String chave, String elemento){
        this.tabela.put(chave, elemento);
    }
}