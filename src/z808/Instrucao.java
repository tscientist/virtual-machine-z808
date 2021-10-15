
package z808;

public class Instrucao extends Key {
    private Integer type; //tipo 1 = sem campo valor, tipo 2 = com campo valor
    public Instrucao(String opcode, Integer value, Integer type){
        super(opcode, value);
        this.type = type;
    }
    
    public Instrucao(String opcode, Integer type){
        super(opcode);
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
