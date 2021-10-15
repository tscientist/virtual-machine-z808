package z808;

public class Key { //interface
    private Integer value;
    private String opcode;

    public Key(String opcode, Integer value) {
        this.value = value;
        this.opcode = opcode;
    }
    
    public Key (Integer value){
        this.value = value;
        this.opcode = null;
    }
    
    public Key(String opcode){
        this.opcode = opcode;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
}
