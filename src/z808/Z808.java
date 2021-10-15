package z808;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class Z808 {
    private int SP = 0;// SP-STACK POINTER,
    private int IP = 0;// IP-INSTRUCTION POINTER
    private int AX = 0;// AX-OPERANDO DESTINO/FONTE
    private int DX = 0;// DX-OPERANDO FONTE
    private int SI = 0;// SI-ÍNDICE
    private int DS = 0;// DS-Segmento de Dados
    private int SS = 0;// SS-Segmento de Pilha
    private int CS = 0;//CS-Segmento de Código
    private int ZF = 0;
    private int SF = 0; // ZF-ZERO, SF-SINAL
    private int SR = 0;//SR-registrador de status, tem as flags OF,ZF e SF (as demais nao foram implementadas)
    private int qtde_dados1, qtde_dados2, qtde_inst1, qtde_inst2, index_hlt;

    private ArrayList<Integer> index;
    private Hashtable<Integer,Key> memory;//Hashtable<Key,Value>
    private File entrada;

    public Z808() {
        this.memory = new Hashtable<Integer, Key>();
        this.index = new ArrayList<Integer>();
    }

    public int getCS() {//Pega Segmento de Código
        return CS;
    }

    public void setCS(int CS) {//Adiciona Segmento de Código
        this.CS = CS;
    }

    public int getSR() {
        return SR;
    }

    public void setSR(int SR) {
        this.SR = SR;
    }

    public int getDS() {
        return DS;
    }

    public void setDS(int DS) {
        this.DS = DS;
    }

    public int getSS() {
        return SS;
    }

    public void setSS(int SS) {
        this.SS = SS;
    }

    public int getZF() {
        return ZF;
    }

    public void setZF(int ZF) {
        this.ZF = ZF;
    }

    public int getSF() {
        return SF;
    }

    public void setSF(int SF) {
        this.SF = SF;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public int getIP() {
        return IP;
    }

    public void setIP(int IP) {
        this.IP = IP;
    }

    public int getAX() {
        return AX;
    }

    public void setAX(int AX) {
        this.AX = AX;
    }

    public int getDX() {
        return DX;
    }

    public void setDX(int DX) {
        this.DX = DX;
    }

    public int getSI() {
        return SI;
    }

    public void setSI(int SI) {
        this.SI = SI;
    }

    public int getQtde_dados1() {
        return qtde_dados1;
    }

    public void setQtde_dados1(int qtde_dados1) {
        this.qtde_dados1 = qtde_dados1;
    }

    public int getQtde_dados2() {
        return qtde_dados2;
    }

    public void setQtde_dados2(int qtde_dados2) {
        this.qtde_dados2 = qtde_dados2;
    }

    public int getQtde_inst1() {
        return qtde_inst1;
    }

    public void setQtde_inst1(int qtde_inst1) {
        this.qtde_inst1 = qtde_inst1;
    }

    public int getQtde_inst2() {
        return qtde_inst2;
    }

    public void setQtde_inst2(int qtde_inst2) {
        this.qtde_inst2 = qtde_inst2;
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

            qtde_dados1 = Integer.parseInt(sc.next());
            System.out.println("qtde_dados1 " + qtde_dados1);
            qtde_inst1 = Integer.parseInt(sc.next());
            System.out.println("qtde_inst1 " + qtde_inst1);
            qtde_dados2 = Integer.parseInt(sc.next());
            System.out.println("qtde_dados2 " + qtde_dados2);
            qtde_inst2 = Integer.parseInt(sc.next());
            System.out.println("qtde_inst2 " + qtde_inst2);



            Integer flag = 0;
            for (int i = 0; i < qtde_dados1; i++ ){
                Integer valor = Integer.parseInt(sc.next());//0000
                index.add(flag);
                Value novo = new Value(valor);//0000
                System.out.println("valor " + valor);

                memory.put(flag, novo);
                flag = flag + 2;//anda as posições de 2 em 2
            }
            loadInstructions(sc, flag, qtde_inst1);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Z808.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadInstructions(Scanner sc, Integer flag, Integer qtde_inst) { //carrega instruções
        System.out.println("qtde_inst " + qtde_inst);

        for (int cont = 0; cont < qtde_inst; cont++ ){
            StringTokenizer st = new StringTokenizer(sc.next());
            String opcode;
            int cont_tokens = st.countTokens();
            if (cont_tokens == 1) { //Se é só 1 palavra
                opcode = st.nextToken();
                index.add(flag);
                Instruction nova = new Instruction(opcode, 1);
                memory.put(flag, nova);
                if (opcode.equalsIgnoreCase("F4") ||
                        opcode.equalsIgnoreCase("58") ||
                        opcode.equalsIgnoreCase("50") ||
                        opcode.equalsIgnoreCase("9D") ||
                        opcode.equalsIgnoreCase("9C") ||
                        opcode.equalsIgnoreCase("C3")){
                    if (opcode.equalsIgnoreCase("F4")){
                        index_hlt = flag;
                    }
                    flag++;
                } else {   //eh uma Instruction de dois bytes, mas sem cte ou mem
                    flag = flag + 2;
                }
            } else {   //tem dois tokens, pode ser 2 bytes EB mem, ou 3 bytes 8984 mem
                opcode = st.nextToken();
                int valor = Integer.parseInt(st.nextToken());
                index.add(flag);
                Instruction nova = new Instruction(opcode, valor,2);
                memory.put(flag, nova);
                if (opcode.equalsIgnoreCase("8984") || opcode.equalsIgnoreCase("8B84")){
                    flag = flag + 3;//3 bytes
                } else {
                    flag = flag + 2;//2 bytes
                }
            }
        }

        if (qtde_inst == qtde_inst1) {
            loadValues(sc, flag);
        }
    }
    private void loadValues(Scanner sc, Integer flag) { //carrega valores
        System.out.println("\nqtde_dados2 " + qtde_dados2);

        for (int i = 0; i < qtde_dados2; i++ ){
            Integer valor = Integer.parseInt(sc.next());
            index.add(flag);
            Value novo = new Value(valor);
            memory.put(flag, novo);
            flag = flag + 2;
        }
        System.out.println("\nqtde_inst2 " + qtde_inst2);

        loadInstructions(sc, flag, qtde_inst2);
    }

    public int loadStack(int tam_extra) {
       int prox_index = index.get(index.size()-1) + 1;       //2º segmento TEM q terminar com hlt

       for (int cont=0; cont < tam_extra; cont++){
           int valor = 0;
           index.add(prox_index);
           Value novo = new Value(valor);
           memory.put(prox_index, novo);
           prox_index = prox_index + 2;
       }
       //imprimeIndices();
       return prox_index;
    }

    public int run(Instruction inst, JTextField label, Integer real_address) {
        // qqer n > 0 = atualiza tab mem , -1 = n precisa atualizar
        int type = inst.getType();
        //tipo 1 = sem campo valor, tipo 2 = com campo valor

        String opcode = inst.getOpcode();

        if (type == 1) {  // INSTRUÇÕES SEM O CAMPO VALOR
            switch (opcode) {
                case "03C0":
                    AX = DX + AX;
                    label.setText("ADD AX,DX");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "03C2":
                    AX = AX + AX;
                    label.setText("ADD AX,AX");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "F7F6":
                    String bin_lower = Integer.toBinaryString(AX);
                    bin_lower = ajusta_bin_string(bin_lower);
                    String bin_higher = Integer.toBinaryString(DX);
                    bin_higher = bin_higher.concat(bin_lower);
                    int dividendo = Integer.parseInt(bin_higher, 2);
                    String bin_divisor;
                    bin_divisor = Integer.toBinaryString(SI);
                    label.setText("DIV SI");
                    bin_divisor = ajusta_bin_string(bin_divisor);
                    int divisor = Integer.parseInt(bin_divisor, 2);
                    AX = dividendo/divisor;
                    DX = dividendo % divisor;
                    IP = IP +2;
                    return -1;
                case "F7F0":
                    String bin_lower2 = Integer.toBinaryString(AX);
                    bin_lower2 = ajusta_bin_string(bin_lower2);
                    String bin_higher2 = Integer.toBinaryString(DX);
                    bin_higher2 = bin_higher2.concat(bin_lower2);
                    int dividendo2 = Integer.parseInt(bin_higher2, 2);
                    String bin_divisor2;
                    bin_divisor2 = Integer.toBinaryString(AX); //div AX
                    label.setText("DIV AX");
                    bin_divisor2 = ajusta_bin_string(bin_divisor2);
                    int divisor2 = Integer.parseInt(bin_divisor2, 2);
                    AX = dividendo2/divisor2;
                    DX = dividendo2 % divisor2;
                    IP = IP +2;
                    return -1;
                case "2BC2":
                    AX = AX - DX;
                    label.setText("SUB AX,DX");
                    ajusta_flags(AX);
                    IP = IP + 2;
                    return -1;
                case "2BC0":
                    System.out.println("Problema!");
                    AX = AX - AX;
                    label.setText("SUB AX,AX");
                    ajusta_flags(AX);
                    IP = IP + 2;
                    return -1;
                case "F7E6":
                    String multiplicando = Integer.toBinaryString(AX);
                    multiplicando = ajusta_32bits(multiplicando);
                    multiplicando = multiplicando.substring(16, 32);
                    String multiplicador;
                    multiplicador = Integer.toBinaryString(SI);
                    label.setText("MUL SI");
                    int fat1 = Integer.parseInt(multiplicando, 2);
                    int fat2 = Integer.parseInt(multiplicador, 2);
                    int res = fat1*fat2;
                    String res_inteira = Integer.toBinaryString(res);
                    res_inteira = ajusta_32bits(res_inteira);
                    DX = Integer.parseInt(res_inteira.substring(0, 16), 2);
                    AX = Integer.parseInt(res_inteira.substring(16, 32), 2);
                    ajusta_flags(AX);
                    IP = IP + 2;
                    return -1;
                case "F7E0":
                    String multiplicando2 = Integer.toBinaryString(AX);
                    multiplicando2 = ajusta_32bits(multiplicando2);
                    multiplicando2 = multiplicando2.substring(16, 32);
                    String multiplicador2;
                    multiplicador2 = Integer.toBinaryString(SI);
                    label.setText("MUL SI");
                    int fat3 = Integer.parseInt(multiplicando2, 2);
                    int fat4 = Integer.parseInt(multiplicador2, 2);
                    int res2 = fat3*fat4;
                    String res_inteira2 = Integer.toBinaryString(res2);
                    res_inteira = ajusta_32bits(res_inteira2);
                    DX = Integer.parseInt(res_inteira.substring(0, 16), 2);
                    AX = Integer.parseInt(res_inteira.substring(16, 32), 2);
                    ajusta_flags(AX);
                    IP = IP + 2;
                    return -1;
                case "23C2":
                    AX = AX & DX;
                    ajusta_flags(AX);
                    label.setText("AND AX,DX");
                    IP = IP +2;
                    return -1;
                case "23C0":
                    label.setText("AND AX,AX");
                    AX = AX & AX; //não testei se funciona ainda
                    IP = IP +2;
                    return -1;
                case "F7D0":
                    String valor = Integer.toBinaryString(AX);
                    valor = ajusta_32bits(valor);
                    valor = inverte_bin_string(valor.substring(16, 32));
                    short s = (short)Integer.parseInt(valor, 2);
                    AX = (int)s;
                    IP = IP +2;
                    ajusta_flags(AX);
                    label.setText("NOT AX");
                    return -1;
                case "0BC2":
                    AX = AX | DX;
                    ajusta_flags(AX);
                    label.setText("OR AX,DX");
                    IP = IP +2;
                    return -1;
                case "0BC0":
                    label.setText("OR AX,AX");
                    IP = IP +2;
                    return -1;
                case "33C2":
                    AX = AX ^ DX;
                    ajusta_flags(AX);
                    label.setText("XOR AX,DX");
                    IP = IP +2;
                    return -1;
                case "33C0":
                    AX = 0;            // n xor n = 0 sempre
                    ZF = 1;
                    label.setText("XOR AX,AX");
                    IP = IP +2;
                    return -1;
                case "C3":
                    int end = SP;
                    Key algo1 = memory.get(end);
                    IP = algo1.getValue();
                    SP = SP + 2;                    //nao sei se ret altera o valor do stack pointer..
                    label.setText("RET");
                    return -1;
                case "8ED0":
                    SS = AX;
                    ajusta_flags(SS);
                    label.setText("MOV SS,AX");
                    IP = IP +2;
                    return -1;
                case "8ED8":
                    DS = AX;
                    ajusta_flags(DS);
                    label.setText("MOV DS,AX");
                    IP = IP +2;
                    return -1;
                case "8CD0":
                    AX = SS;
                    label.setText("MOV AX,SS");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "8CD8":
                    AX = DS;
                    label.setText("MOV AX,DS");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "8CC8":
                    AX = CS;
                    label.setText("MOV AX,CS");
                    IP = IP +2;
                    return -1;
                case "8BC2":
                    AX = DX;
                    label.setText("MOV AX,DX");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "8BC4":
                    AX = SP;
                    label.setText("MOV AX,SP");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "8BC6":
                    AX = SI;
                    label.setText("MOV AX,SI");
                    ajusta_flags(AX);
                    IP = IP +2;
                    return -1;
                case "8BE0":
                    SP = AX;
                    ajusta_flags(SP);
                    IP = IP +2;
                    label.setText("MOV SP,AX");
                    return -1;
                case "8BD0":
                    DX = AX;
                    ajusta_flags(DX);
                    label.setText("MOV DX,AX");
                    IP = IP +2;
                    return -1;
                case "8BF0":
                    SI = AX;
                    ajusta_flags(SI);
                    label.setText("MOV SI,AX");
                    IP = IP +2;
                    return -1;
                case "8B04":
                    AX = memory.get(SI).getValue();
                    ajusta_flags(AX);
                    IP = IP + 2;
                    label.setText("MOV AX,"+"["+SI+"]");
                    return -1;
                case "8904":
                    Key algo2 = memory.get(SI);
                    algo2.setValue(AX);
                    IP = IP +2;
                    label.setText("MOV ["+SI+"],AX");
                    return SI;
                case "58":
                    try{
                        AX = memory.get(SP).getValue();
                        SP = SP +2;
                        label.setText("POP AX");
                    }
                    catch(NullPointerException e){
                        JOptionPane.showMessageDialog(null, "Pilha vazia", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    IP = IP +1;
                    return -1;
                case "50":
                    SP = SP -2;
                    if ( SP > index_hlt) {                        //só pode empilhar se a pilha nao estiver invadindo area de dados, delimitada pelo indice do hlt
                        Key algo3 = memory.get(SP);
                        algo3.setValue(AX);
                        label.setText("PUSH AX");
                        IP = IP +1;       // IP +1 pq é uma Instruction de um byte só
                        return SP;
                    } else {
                        JOptionPane.showMessageDialog(null, "Pilha invadiu área de dados!!!", "Erro", JOptionPane.ERROR_MESSAGE);
                        SP = SP +2;
                        IP = IP +1;
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
                    ajusta_flags(AX);
                    label.setText("ADD AX,#"+inst.getValue());
                    IP = IP +2;
                    return -1;
                case "2D":
                    AX = AX - inst.getValue();
                    ajusta_flags(AX);
                    label.setText("SUB AX,#"+inst.getValue());
                    IP = IP +2;
                    return -1;
                case "25":
                    AX = AX & inst.getValue();
                    ajusta_flags(AX);
                    label.setText("AND AX,#"+inst.getValue());
                    IP = IP +2;
                    return -1;
                case "0D":
                    AX = AX | inst.getValue();
                    ajusta_flags(AX);
                    label.setText("OR AX,#"+inst.getValue());
                    IP = IP +2;
                    return -1;
                case "35":
                    AX = AX ^ inst.getValue();
                    ajusta_flags(AX);
                    label.setText("XOR AX,#"+inst.getValue());
                    IP = IP +2;
                    return -1;
                case "74":
                    switch(SR){
                        case 2: IP = inst.getValue(); break;
                        case 6: IP = inst.getValue(); break;
                        default  : IP = IP +2; break;
                    }
                    label.setText("JZ "+inst.getValue());
                    return -1;
                case "75":
                    switch(SR){
                        case 0: IP = inst.getValue(); break;
                        case 1: IP = inst.getValue(); break;
                        case 4: IP = inst.getValue(); break;
                        case 5: IP = inst.getValue(); break;
                        default : IP = IP +2; break;
                    }
                    label.setText("JNZ "+inst.getValue());
                    return -1;
                case "76":
                    switch(SR){
                        case 0: IP = inst.getValue(); break;
                        case 2: IP = inst.getValue(); break;
                        case 4: IP = inst.getValue(); break;
                        case 6: IP = inst.getValue(); break;
                        default : IP = IP +2; break;
                    }
                    label.setText("JP "+inst.getValue());
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
                    label.setText("CALL "+inst.getValue());
                    return SP;
                case "A1":
                    IP = IP +2;
                    int value = memory.get(real_address).getValue();
                    AX = value;
                    ajusta_flags(AX);
                    label.setText("MOV AX,"+inst.getValue());
                    return -1;
                case "A3":
                    IP = IP +2;
                    Key key = memory.get(inst.getValue());
                    key.setValue(AX);
                    label.setText("MOV "+inst.getValue()+",AX");
                    return inst.getValue();
                case "8B84":
                    int end_real = SI + inst.getValue();
                    IP = IP +3;
                    AX = memory.get(end_real).getValue();
                    ajusta_flags(AX);
                    label.setText("MOV AX,"+inst.getValue()+"["+SI+"]");
                    return -1;
                case "8984":
                    int end_real2 = SI + inst.getValue();
                    IP = IP +3;
                    Key algo2 = memory.get(end_real2);
                    algo2.setValue(AX);
                    label.setText("MOV "+inst.getValue()+"["+SI+"], AX");
                    return end_real2;
                case "B8":
                    AX = inst.getValue();
                    ajusta_flags(AX);
                    IP = IP +2;
                    label.setText("MOV AX,#"+inst.getValue());
                    return -1;

                default:
                    IP = IP;
                    return -1;
            }}}

    private String ajusta_bin_string(String bin_string) {
        StringBuilder sb = new StringBuilder();
        int dif = 16 - bin_string.length();
        int cont = 0;
        if( dif > 0){
            while(cont < dif){
                sb.append('0');
                cont++;
            }
            return sb.toString().concat(bin_string);
        } else if( dif < 0){
            return bin_string.substring(bin_string.length()-16, bin_string.length());
        } else{
            return bin_string;
        }
    }

    private void ajusta_flags(int destino) {
        if((destino > Short.MAX_VALUE) || (destino < Short.MIN_VALUE)){
            SR = SR | 4;
        }
        
        if( destino == 0){
            ZF = 1;
            SF = 0;
            //System.out.print("deu zero");
            SR = SR | 2;
            SR = SR & 6;
            //System.out.printf("sr: %d", SR);
        }
        else{
            ZF = 0;
            // SR = SR & 5;
            if( destino >= 0){
                SF = 0;
                SR = SR & 4;
            }
            else{
                SF = 1;
                SR = SR | 1;
                SR = SR & 5;
            }
        }
        
        
    }

    private String ajusta_32bits(String res_inteira) {
        StringBuilder sb = new StringBuilder();
        int dif = 32 - res_inteira.length();
        int cont = 0;
        if( dif > 0){
            while(cont < dif){
                sb.append('0');
                cont++;
            }
            return sb.toString().concat(res_inteira);
        }
        else{
            return res_inteira;
        }
    }

    private String inverte_bin_string(String str) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '0'){
                sb.append('1');
            }
            else{
                sb.append('0');
            }
        }
        return sb.toString();
    }

    private void imprimeIndex() {
        for(int i = 0; i < index.size(); i++){
            System.out.print("\n"+i+":"+index.get(i));
        }
    }
}
