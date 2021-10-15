package z808;

public class Thing { //interface 
    private int valor;
    private String opcode;

    public Thing(String opcode, int valor) {
        this.valor = valor;
        this.opcode = opcode;
    }
    
    public Thing(int valor){
        this.valor = valor;
        this.opcode = null;
    }
    
    public Thing(String opcode){
        this.opcode = opcode;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
}
