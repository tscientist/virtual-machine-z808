
package z808;

public class Instrucao extends Thing {
    private int tipo; //tipo 1 = sem campo valor, tipo 2 = com campo valor
    public Instrucao(String opcode, int valor, int tipo){
        super(opcode, valor);
        this.tipo = tipo;
    }
    
    public Instrucao(String opcode, int tipo){
        super(opcode);
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
