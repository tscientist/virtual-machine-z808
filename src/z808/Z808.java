package z808.z808;

import z808.z808.Flags;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Z808 extends Flags {
    private ArrayList<Integer> index;
    private Hashtable<Integer,Key> memory;//Hashtable<Key,Value>
    private File entrada;

    public Z808(Integer constantsMemory, Integer dataMemory, Integer instructionMemory, Integer index_hlt,
                Integer SP, Integer IP, Integer AX, Integer DX, Integer SI, Integer DS, Integer SS, Integer CS, Integer ZF, Integer SF, Integer SR) {
        super(constantsMemory, dataMemory, instructionMemory, index_hlt, SP, IP, AX, DX, SI, DS, SS, CS, ZF, SF, SR);
        this.memory = new Hashtable<Integer, Key>();
        this.index = new ArrayList<Integer>();
    }
    public ArrayList<Integer> getIndex() {
        return index;
    }

    public void setIndex(ArrayList<Integer> index) {
        this.index = index;
    }

    public Hashtable<Integer, Key> getMemory() {
        return memory;
    }

    public void setMemory(Hashtable<Integer, Key> memory) {
        this.memory = memory;
    }

    public File getLig() {
        return entrada;
    }

    public void setLig(File entrada) {
        this.entrada = entrada;
    }

    public void loadFirstData() { //pega os primeiros 4 valores do arquivo
        try {
            Scanner sc = new Scanner(new FileReader(entrada)).useDelimiter("\\||\\n");
            Integer nextPosition = 0;

            constantsMemory = Integer.parseInt(sc.next());// area de memoria reservada para constantes
            instructionMemory = Integer.parseInt(sc.next());// area de instruções
            dataMemory = Integer.parseInt(sc.next());// area de memoria reservada para dados

            for (int i = 0; i < constantsMemory; i++) {
                Integer value = Integer.parseInt(sc.next());
                index.add(nextPosition);
                Value newValue = new Value(value);
                memory.put(nextPosition, newValue);
                nextPosition = nextPosition + 2;
            }
            loadInstructions(sc, nextPosition, instructionMemory);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Z808.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadInstructions(Scanner sc, Integer flag, Integer qtde_inst) { //carrega instruções
        Integer value;

        for (int cont = 0; cont < qtde_inst; cont++ ){
            StringTokenizer st = new StringTokenizer(sc.next());
            String opcode;

            if (st.countTokens() == 1) { //1 token
                opcode = st.nextToken();
                index.add(flag);
                Instruction new_instruction = new Instruction(opcode, 1);
                memory.put(flag, new_instruction);
                if (opcode.equals("9D") || opcode.equals("9C") || opcode.equals("EF") || opcode.equals("EE")) {
                    index_hlt = (opcode.equals("EE")) ? flag : index_hlt;
                    flag++;
                } else {//2 bytes
                    flag = flag + 2;
                }
            } else {//2 tokens
                opcode = st.nextToken();
                value = Integer.parseInt(st.nextToken());

                index.add(flag);
                Instruction new_instruction = new Instruction(opcode, value, 2);
                memory.put(flag, new_instruction);
                if (opcode.equals("05") || opcode.equals("25") || opcode.equals("0D")|| opcode.equals("35")
                        || opcode.equals("EB") || opcode.equals("74")|| opcode.equals("75")|| opcode.equals("7A")
                        || opcode.equals("E8")){
                    flag = flag + 3;//3 bytes
                } else {
                    flag = flag + 2;//2 bytes
                }
            }
        }

        loadValues(sc, flag);
    }

    private void loadValues(Scanner sc, Integer nextPosition) { //carrega valores

        for (int i = 0; i < dataMemory; i++) {
            Integer value = Integer.parseInt(sc.next());
            index.add(nextPosition);
            Value newValue = new Value(value);
            memory.put(nextPosition, newValue);
            nextPosition = nextPosition + 2;
        }
    }

    public Integer loadStack(Integer size) {
        Integer nextPosition = index.get(index.size() - 1) + 1;

       for (int cont = 0; cont < size; cont++) {
           Integer value = 0;
           index.add(nextPosition);
           Value newValue = new Value(value);
           memory.put(nextPosition, newValue);
           nextPosition = nextPosition + 2;
       }

       return nextPosition;//terminar com hlt
    }

    public int run(Instruction inst, JTextField label, Integer real_address) {
        Integer type = inst.getType();//tipo 1 = sem campo valor, tipo 2 = com campo valor

        String opcode = inst.getOpcode();
        if (type == 1) {  // INSTRUÇÕES SEM O CAMPO VALOR
            switch (opcode) {
                case "03C0":
                    AX = AX + AX;
                    label.setText("ADD AX,AX");
                    updateFlags(AX);
                    IP = IP + 2;
                    return -1;
                case "03C2":
                    AX = DX + AX;
                    label.setText("ADD AX,DX");
                    updateFlags(AX);
                    IP = IP + 2;
                    return -1;
                case "F7F6":
                    String bin_lower = Integer.toBinaryString(AX);
                    bin_lower = updateBinString(bin_lower);
                    String bin_higher = Integer.toBinaryString(DX);
                    bin_higher = bin_higher.concat(bin_lower);
                    Integer dividendo = Integer.parseInt(bin_higher, 2);
                    String bin_divisor;
                    bin_divisor = Integer.toBinaryString(SI);
                    label.setText("DIV SI");
                    bin_divisor = updateBinString(bin_divisor);
                    Integer divisor = Integer.parseInt(bin_divisor, 2);
                    AX = dividendo/divisor;
                    DX = dividendo % divisor;
                    IP = IP + 2;
                    return -1;
                case "F7F0":
                    String bin_lower2 = Integer.toBinaryString(AX);
                    bin_lower2 = updateBinString(bin_lower2);
                    String bin_higher2 = Integer.toBinaryString(DX);
                    bin_higher2 = bin_higher2.concat(bin_lower2);
                    int dividendo2 = Integer.parseInt(bin_higher2, 2);
                    String bin_divisor2;
                    bin_divisor2 = Integer.toBinaryString(AX); //div AX
                    label.setText("DIV AX");
                    bin_divisor2 = updateBinString(bin_divisor2);
                    int divisor2 = Integer.parseInt(bin_divisor2, 2);
                    AX = dividendo2/divisor2;
                    DX = dividendo2 % divisor2;
                    IP = IP + 2;
                    return -1;
                case "2BC2":
                    AX = AX - DX;
                    label.setText("SUB AX,DX");
                    updateFlags(AX);
                    IP = IP + 2;
                    return -1;
                case "2BC0":
                    AX = AX - AX;
                    label.setText("SUB AX,AX");
                    updateFlags(AX);
                    IP = IP + 2;
                    return -1;
                case "F7E6":
                    String multiplicando = Integer.toBinaryString(AX);
                    multiplicando = update32bitString(multiplicando);
                    multiplicando = multiplicando.substring(16, 32);
                    String multiplicador;
                    multiplicador = Integer.toBinaryString(SI);
                    label.setText("MUL SI");
                    int fat1 = Integer.parseInt(multiplicando, 2);
                    int fat2 = Integer.parseInt(multiplicador, 2);
                    int res = fat1*fat2;
                    String res_inteira = Integer.toBinaryString(res);
                    res_inteira = update32bitString(res_inteira);
                    DX = Integer.parseInt(res_inteira.substring(0, 16), 2);
                    AX = Integer.parseInt(res_inteira.substring(16, 32), 2);
                    updateFlags(AX);
                    IP = IP + 2;
                    return -1;
                case "F7E0": //TODO: VERIFICAR OPERAÇÃO
                    String multiplicando2 = Integer.toBinaryString(AX);
                    multiplicando2 = update32bitString(multiplicando2);
                    multiplicando2 = multiplicando2.substring(16, 32);
                    String multiplicador2;
                    multiplicador2 = Integer.toBinaryString(SI);
                    label.setText("MUL AX");
                    int fat3 = Integer.parseInt(multiplicando2, 2);
                    int fat4 = Integer.parseInt(multiplicador2, 2);
                    int res2 = fat3*fat4;
                    String res_inteira2 = Integer.toBinaryString(res2);
                    res_inteira = update32bitString(res_inteira2);
                    DX = Integer.parseInt(res_inteira.substring(0, 16), 2);
                    AX = Integer.parseInt(res_inteira.substring(16, 32), 2);
                    updateFlags(AX);
                    IP = IP + 2;
                    return -1;
                case "23C2": //TODO: testar
                    AX = AX & DX;
                    updateFlags(AX);
                    label.setText("AND AX,DX");
                    IP = IP +2;
                    return -1;
                case "23C0":
                    label.setText("AND AX,AX");
                    AX = AX & AX;
                    IP = IP + 2;
                    return -1;
                case "F8C0":
                    String valor = Integer.toBinaryString(AX);
                    valor = update32bitString(valor);
                    valor = reverseBinString(valor.substring(16, 32));
                    short s = (short)Integer.parseInt(valor, 2);
                    AX = (int) s;
                    IP = IP +2;
                    updateFlags(AX);
                    label.setText("NOT AX");
                    return -1;
                case "0BC2":
                    AX = AX | DX;
                    updateFlags(AX);
                    label.setText("OR AX,DX");
                    IP = IP + 2;
                    return -1;
                case "0BC0":
                    label.setText("OR AX,AX");
                    IP = IP + 2;
                    return -1;
                case "33C2":
                    AX = AX ^ DX;
                    updateFlags(AX);
                    label.setText("XOR AX,DX");
                    IP = IP +2;
                    return -1;
                case "33C0":
                    AX = 0;
                    ZF = 1;
                    label.setText("XOR AX,AX");
                    IP = IP + 2;
                    return -1;
                case "EF":
                    int end = SP;
                    Key algo1 = memory.get(end);
                    IP = algo1.getValue();
                    SP = SP + 2;
                    label.setText("RET");
                    return -1;
                case "58C0":
                    try {
                        AX = memory.get(SP).getValue();
                        SP = SP + 2;
                        label.setText("POP AX");
                    } catch(NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Empty stack", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    IP = IP + 2;
                    return -1;
                case "58C2":
                    try {
                        DX = memory.get(SP).getValue();
                        SP = SP + 2;
                        label.setText("POP DX");
                    } catch(NullPointerException e) {
                        JOptionPane.showMessageDialog(null, "Empty stack", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    IP = IP + 2;
                    return -1;
                case "50C0":
                    SP = SP -2;
                    if ( SP > index_hlt) {
                        Key key = memory.get(SP);
                        key.setValue(AX);
                        label.setText("PUSH AX");
                        IP = IP +1;
                        return SP;
                    } else {
                        JOptionPane.showMessageDialog(null, "Stack error", "Error", JOptionPane.ERROR_MESSAGE);
                        SP = SP +2;
                        IP = IP +1;
                        return -1;
                    }
                case "50C2":
                    SP = SP - 2;
                    if ( SP > index_hlt) {
                        Key key = memory.get(SP);
                        key.setValue(DX);
                        label.setText("PUSH DX");
                        IP = IP + 2;
                        return SP;
                    } else {
                        JOptionPane.showMessageDialog(null, "Stack error", "Error", JOptionPane.ERROR_MESSAGE);
                        SP = SP + 2;
                        IP = IP + 2;
                        return -1;
                    }
                default:
                    label.setText("HLT");
                    IP = IP +2;
                    return -1;
            }
        } else {  //INSTRUÇÕES DE TIPO 2 - TEM CAMPO VALOR CTE OU MEM
            switch (opcode) {
                case "05":
                    AX = AX + inst.getValue();
                    updateFlags(AX);
                    label.setText("ADD AX,#" + inst.getValue());
                    IP = IP + 3;
                    return -1;
                case "26":
                    AX = AX & inst.getValue();
                    updateFlags(AX);
                    label.setText("AND AX, " + inst.getValue());
                    IP = IP + 3;
                    return -1;
                case "2D"://ERA 25
                    AX = AX - inst.getValue();
                    updateFlags(AX);
                    label.setText("SUB AX,#" + inst.getValue());
                    IP = IP + 3;
                    return -1;
                case "0D":
                    AX = AX | inst.getValue();
                    updateFlags(AX);
                    label.setText("OR AX,#" + inst.getValue());
                    IP = IP + 3;
                    return -1;
                case "35":
//                    AX = AX ^ inst.getValue();
//                    updateFlags(AX);
//                    label.setText("XOR AX,#" + inst.getValue());
//                    IP = IP +2;
//                    return -1;
                    AX = AX | inst.getValue();//0D
                    updateFlags(AX);
                    label.setText("XOR AX,#" + inst.getValue());
                    IP = IP + 3;
                    return -1;
                case "07C0":
                    IP = IP +2;
                    Key key = memory.get(inst.getValue());
                    key.setValue(AX);
                    label.setText("STORE "+inst.getValue()+",AX");
                    return inst.getValue();
                case "74":
                    switch(SR){
                        case 2: IP = inst.getValue(); break;
                        case 6: IP = inst.getValue(); break;
                        default  : IP = IP + 3; break;
                    }
                    label.setText("JZ "+ inst.getValue());
                    return -1;
                case "75":
                    switch(SR){
                        case 0: IP = inst.getValue(); break;
                        case 1: IP = inst.getValue(); break;
                        case 4: IP = inst.getValue(); break;
                        case 5: IP = inst.getValue(); break;
                        default : IP = IP + 3; break;
                    }
                    label.setText("JNZ "+inst.getValue());
                    return -1;
                case "7A": //ERA 76
                    switch(SR) {
                        case 0: IP = inst.getValue(); break;
                        case 2: IP = inst.getValue(); break;
                        case 4: IP = inst.getValue(); break;
                        case 6: IP = inst.getValue(); break;
                        default : IP = IP +2; break;
                    }
                    label.setText("JP " + inst.getValue());
                    return -1;
                case "EB":
                    IP = inst.getValue();
                    label.setText("JMP " + inst.getValue());
                    return -1;
                case "E8":
                    SP = SP - 2;
                    Key algo = memory.get(SP);
                    algo.setValue(IP + 2);
                    IP = inst.getValue();
                    label.setText("CALL " + inst.getValue());
                    return SP;
                default:
                    IP = IP;
                    return -1;
            }
        }
    }
}
