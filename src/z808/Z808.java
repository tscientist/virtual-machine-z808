package z808;

import z808.z808.Flags;

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

public class Z808 extends Flags {
    private ArrayList<Integer> index;
    private Hashtable<Integer,Key> memory;//Hashtable<Key,Value>
    private File entrada;

    public Z808(Integer qtde_dados1, Integer qtde_dados2, Integer qtde_inst1, Integer qtde_inst2, Integer index_hlt,
                Integer SP, Integer IP, Integer AX, Integer DX, Integer SI, Integer DS, Integer SS, Integer CS, Integer ZF, Integer SF, Integer SR) {
        super(qtde_dados1, qtde_dados2, qtde_inst1, qtde_inst2, index_hlt, SP, IP, AX, DX, SI, DS, SS, CS, ZF, SF, SR);
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

            qtde_dados1 = Integer.parseInt(sc.next());
            qtde_inst1 = Integer.parseInt(sc.next());
            qtde_dados2 = Integer.parseInt(sc.next());
            qtde_inst2 = Integer.parseInt(sc.next());

            for (int i = 0; i < qtde_dados1; i++) {
                Integer value = Integer.parseInt(sc.next());
                index.add(nextPosition);
                Value newValue = new Value(value);
                memory.put(nextPosition, newValue);
                nextPosition = nextPosition + 2;
            }
            loadInstructions(sc, nextPosition, qtde_inst1);

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
                if (opcode.equals("F4") || opcode.equals("58") || opcode.equals("50") || opcode.equals("9D") || opcode.equals("9C") || opcode.equals("C3")) {
                    index_hlt = (opcode.equals("F4")) ? flag : index_hlt;
                    flag++;
                } else {//2 bytes sem memória
                    flag = flag + 2;
                }
            } else {//2 tokens
                opcode = st.nextToken();
                value = Integer.parseInt(st.nextToken());
                index.add(flag);
                Instruction new_instruction = new Instruction(opcode, value, 2);
                memory.put(flag, new_instruction);
                if (opcode.equals("8984") || opcode.equals("8B84")){
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

    private void loadValues(Scanner sc, Integer nextPosition) { //carrega valores
        for (int i = 0; i < qtde_dados2; i++) {
            Integer value = Integer.parseInt(sc.next());
            index.add(nextPosition);
            Value newValue = new Value(value);
            memory.put(nextPosition, newValue);
            nextPosition = nextPosition + 2;
        }

        loadInstructions(sc, nextPosition, qtde_inst2);
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
        // qqer n > 0 = atualiza tab mem , -1 = n precisa atualizar
        int type = inst.getType();
        //tipo 1 = sem campo valor, tipo 2 = com campo valor

        String opcode = inst.getOpcode();

        if (type == 1) {  // INSTRUÇÕES SEM O CAMPO VALOR
            System.out.println("if tipo 1");
            /* INSTRUÇÕES ARITMÉTICAS */
            if (opcode.matches("03C[02]")){   // add ax,dx OU add ax,ax
                System.out.println("tipo 1 primeiro if"+ opcode);

                if (opcode.charAt(3) == '2' ){
                   AX = DX + AX;
                   label.setText("ADD AX,DX");
               } else {
                   AX = AX + AX;
                   label.setText("ADD AX,AX");
               }
                updateFlags(AX);
               IP = IP +2;
               return -1;
            } else if(opcode.matches("F7F[60]")){//div ax OU div SI
                System.out.println("tipo 1 segundo if"+ opcode);

                String bin_lower = Integer.toBinaryString(AX);
                bin_lower = updateBinString(bin_lower);
                String bin_higher = Integer.toBinaryString(DX);
                
                bin_higher = bin_higher.concat(bin_lower);
                int dividendo = Integer.parseInt(bin_higher, 2);
                String bin_divisor;
                if (opcode.charAt(3) == '6') {  //div SI
                    bin_divisor = Integer.toBinaryString(SI);
                    label.setText("DIV SI");
                } else {
                    bin_divisor = Integer.toBinaryString(AX); //div AX
                    label.setText("DIV AX");
                }
                bin_divisor = updateBinString(bin_divisor);
                int divisor = Integer.parseInt(bin_divisor, 2);
                AX = dividendo/divisor;
                DX = dividendo % divisor;
                IP = IP +2;
                return -1;
            } else if (opcode.matches("2BC[20]")) {        //sub ax,dx OU sub ax,ax
                System.out.println("tipo 1 terceiro if"+ opcode);

                if (opcode.charAt(3) == '2'){    //sub ax,dx
                    AX = AX - DX;
                    label.setText("SUB AX,DX");
                } else {   // sub ax, ax
                    System.out.println("deu ruim");
                    AX = AX - AX;
                    label.setText("SUB AX,AX");
                }
                updateFlags(AX);
                IP = IP + 2;
                return -1;
            } else if (opcode.matches("F7E[60]")) {   //mul si ou mul ax;
                System.out.println("tipo 1 quarto if"+ opcode);

                String multiplicando = Integer.toBinaryString(AX);
                multiplicando = update32bitString(multiplicando);
                multiplicando = multiplicando.substring(16, 32);
                String multiplicador;
                if (opcode.charAt(3) == '6'){
                    multiplicador = Integer.toBinaryString(SI);
                    label.setText("MUL SI");
                } else {
                    multiplicador = Integer.toBinaryString(AX); 
                    label.setText("MUL AX");
                }
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
            }
            
            /* INSTRUÇÕES LÓGICAS*/
            else if (opcode.matches("23C[20]")) {   //and ax,dx OU and ax,ax
                System.out.println("tipo 1 quinto if"+ opcode);

                if(opcode.charAt(3) == '2'){  // and ax,dx
                    AX = AX & DX;
                    updateFlags(AX);
                    label.setText("AND AX,DX");
                }
                else{ //and ax, ax
                    label.setText("AND AX,AX");
                    AX = AX & AX; //não testei se funciona ainda
                }
                IP = IP +2;
                return -1;
            }
            else if(opcode.equalsIgnoreCase("F7D0")){     //not ax
                System.out.println("tipo 1 sexto if"+ opcode);

                String valor = Integer.toBinaryString(AX);
                valor = update32bitString(valor);
                valor = reverseBinString(valor.substring(16, 32));
                short s = (short)Integer.parseInt(valor, 2);
                AX = (int)s;
                IP = IP +2;
                updateFlags(AX);
                label.setText("NOT AX");
                return -1;
            }
            else if(opcode.matches("0BC[20]")){  // or ax,dx OU or ax,ax
                System.out.println("tipo 1 sete if"+ opcode);

                if(opcode.charAt(3) == '2'){  // or ax,dx
                    AX = AX | DX;
                    updateFlags(AX);
                    label.setText("OR AX,DX");
                }
                else{ // não faz nada pq é o msm valor
                    label.setText("OR AX,AX");
                }
                IP = IP +2;
                return -1;
            }
            else if(opcode.matches("33C[20]")){  // xor ax,dx OU xor ax,ax
                System.out.println("tipo 1 oitovo if"+ opcode);

                if(opcode.charAt(3) == '2'){  // xor ax,dx
                    AX = AX ^ DX;
                    updateFlags(AX);
                    label.setText("XOR AX,DX");
                }
                else{
                    AX = 0;            // n xor n = 0 sempre
                    ZF = 1;
                    label.setText("XOR AX,AX");
                }
                IP = IP +2;
                return -1;
            }
            /* INSRUÇÕES DE DESVIO*/
            else if(opcode.equalsIgnoreCase("C3")){        // ret
                System.out.println("tipo 1 nono if"+ opcode);

                int end = SP;
                Key algo = memory.get(end);
                IP = algo.getValue();
                SP = SP + 2;                    //nao sei se ret altera o valor do stack pointer..
                label.setText("RET");
                return -1;
            }
            /* INSTRUÇÕES DE MOVIMENTAÇÃO*/
            else if(opcode.matches("8ED[08]")){   // mov ss,ax OU mov ds,ax
                System.out.println("tipo 1 decimo if"+ opcode);

                if(opcode.charAt(3) == '0'){ // mov ss,ax
                    SS = AX;
                    updateFlags(SS);
                    label.setText("MOV SS,AX");
                }
                else{
                   DS = AX;
                    updateFlags(DS);
                   label.setText("MOV DS,AX");
                }
                IP = IP +2;
                return -1;
            }
            else if (opcode.matches("8CD[08]")){  // mov ax,ss OU mov ax,ds
                System.out.println("tipo 1 decimo primeiro if"+ opcode);

                if(opcode.charAt(3) == '0'){ // mov ax,ss
                    AX = SS;
                    label.setText("MOV AX,SS");
                }
                else{
                   AX = DS;
                   label.setText("MOV AX,DS");
                }
                updateFlags(AX);
                IP = IP +2;
                return -1;
            } else if(opcode.equalsIgnoreCase("8CC8")){    // mov ax,cs
                System.out.println("tipo 1 12 if"+ opcode);

                AX = CS; 
                label.setText("MOV AX,CS");
                IP = IP +2;
                return -1;
            } else if(opcode.matches("8BC[246]")){   // mov ax,dx OU mov ax,sp OU mov ax,si
                System.out.println("tipo 1 13 if"+ opcode);

                switch(opcode.charAt(3)){
                    case '2': AX = DX; label.setText("MOV AX,DX");  break;
                    case '4': AX = SP; label.setText("MOV AX,SP"); break;
                    case '6': AX = SI; label.setText("MOV AX,SI"); break;
                    
                }
                updateFlags(AX);
                IP = IP +2;
                return -1;
            } else if(opcode.equalsIgnoreCase("8BE0")){  // mov sp,ax
                System.out.println("tipo 1 - 14 if"+ opcode);

                SP = AX;
                updateFlags(SP);
                IP = IP +2;
                label.setText("MOV SP,AX");
                return -1;
            } else if (opcode.matches("8B[DF]0")){  // mov dx,ax OU mov si,ax
                System.out.println("tipo 1 - 15 if"+ opcode);

                if (opcode.charAt(2) == 'D'){
                    DX = AX;
                    updateFlags(DX);
                    label.setText("MOV DX,AX");
                } else {
                    SI = AX;
                    updateFlags(SI);
                    label.setText("MOV SI,AX");
                }
                IP = IP +2;
                return -1;
            } else if(opcode.matches("8[B9]04")){  // mov ax,[si]  OU mov [SI],ax
                System.out.println("tipo 1 - 16 if"+ opcode);

                if(opcode.charAt(1) == 'B'){
                    AX = memory.get(SI).getValue();
                    updateFlags(AX);
                    IP = IP + 2;
                    label.setText("MOV AX,"+"["+SI+"]");
                    return -1;
                }
                else{                 //vai alterar algo na memory mov [si],ax
                    Key algo = memory.get(SI);
                    algo.setValue(AX);
                    IP = IP +2;
                    label.setText("MOV ["+SI+"],AX");
                    return SI;
                }
            } else if(opcode.matches("5[80]")){/* INSTRUÇÕES DE PILHA */     // pop ax OU push ax
                System.out.println("tipo 1 - 17 if"+ opcode);

                if (opcode.charAt(1) == '8') {  // pop ax
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
                } else { //push ax
                    /*AX = 5;*/
                    SP = SP -2;
                    
                    if ( SP > index_hlt) {                        //só pode empilhar se a pilha nao estiver invadindo area de dados, delimitada pelo indice do hlt
                        Key algo = memory.get(SP);
                        algo.setValue(AX);
                        label.setText("PUSH AX");
                        IP = IP +1;       // IP +1 pq é uma Instruction de um byte só
                        return SP;
                    } else {
                        JOptionPane.showMessageDialog(null, "Pilha invadiu área de dados!!!", "Erro", JOptionPane.ERROR_MESSAGE);
                        SP = SP +2;
                        IP = IP +1;
                        return -1;
                    }
                }
            } else { //tipo 1 Instruction vazia
                label.setText("HLT");
                IP = IP +2;
                return -1;
            }
        } else {  //INSTRUÇÕES DE TIPO 2 - TEM CAMPO VALOR CTE OU MEM
            /* INSTRUÇÕES ARITMÉTICAS */
            if (opcode.equalsIgnoreCase("05")) {  // add ax,cte
                AX = AX + inst.getValue();
                updateFlags(AX);
                label.setText("ADD AX,#"+inst.getValue());
                IP = IP +2;
                return -1;
            } else if (opcode.equalsIgnoreCase("2D")) {   // sub ax,cte
                AX = AX - inst.getValue();
                updateFlags(AX);
                label.setText("SUB AX,#"+inst.getValue());
                IP = IP +2;
                return -1;
            } else if (opcode.equalsIgnoreCase("25")) {  /* INSTRUÇÕES LÓGICAS */ // and ax,cte
                AX = AX & inst.getValue();
                updateFlags(AX);
                label.setText("AND AX,#"+inst.getValue());
                IP = IP +2;
                return -1;
            } else if (opcode.equalsIgnoreCase("0D")) {   // or ax,cte
                AX = AX | inst.getValue();
                updateFlags(AX);
                label.setText("OR AX,#"+inst.getValue());
                IP = IP +2;
                return -1;
            } else if(opcode.equalsIgnoreCase("35")){  // xor ax,cte
                AX = AX ^ inst.getValue();
                updateFlags(AX);
                label.setText("XOR AX,#"+inst.getValue());
                IP = IP +2;
                return -1;
            } else if( opcode.matches("7[45A]")){  // jz end, jnz end ou jp end
                if(opcode.charAt(1) == '4'){  // jz end
                    switch(SR){
                        case 2: IP = inst.getValue(); break;
                        case 6: IP = inst.getValue(); break;
                        default  : IP = IP +2; break;
                    }
                    label.setText("JZ "+inst.getValue());
                    return -1;
                }
                else if(opcode.charAt(1) == '5'){  // jnz end
                    switch(SR){
                        case 0: IP = inst.getValue(); break;
                        case 1: IP = inst.getValue(); break;
                        case 4: IP = inst.getValue(); break;
                        case 5: IP = inst.getValue(); break;
                        default : IP = IP +2; break;
                    }
                    label.setText("JNZ "+inst.getValue());
                    return -1;
                }
                else{  // jp end
                    switch(SR){
                        case 0: IP = inst.getValue(); break;
                        case 2: IP = inst.getValue(); break;
                        case 4: IP = inst.getValue(); break;
                        case 6: IP = inst.getValue(); break;
                        default : IP = IP +2; break;
                    }
                    label.setText("JP "+inst.getValue());
                    return -1;
                }
            } else if (opcode.matches("E[B8]")) {   // jmp end ou call end
                if (opcode.charAt(1) == 'B'){  // jmp end
                    IP = inst.getValue();
                    label.setText("JMP " + inst.getValue());
                    return -1;
                } else {   // call end
                    SP = SP - 2;
                    Key algo = memory.get(SP);
                    algo.setValue(IP + 2);
                    IP = inst.getValue();
                    label.setText("CALL "+inst.getValue());
                    return SP;
                }
            } else if(opcode.matches("A[13]")) {/* INSTRUÇÕES DE MOVIMENTAÇÃO */   // mov ax,mem ou mov mem,ax
                IP = IP +2;
                if (opcode.charAt(1) == '1'){  // mov ax,mem
                    //imprimememory();
                    int value = memory.get(real_address).getValue();
                    AX = value;
                    updateFlags(AX);
                    label.setText("MOV AX,"+inst.getValue());
                    return -1;
                } else {  // mov mem, ax
                    Key key = memory.get(inst.getValue());
                    key.setValue(AX);
                    label.setText("MOV "+inst.getValue()+",AX");
                    return inst.getValue();
                }
            } else if(opcode.matches("8[B9]84")){   // mov ax,mem[si]  ou mov mem[si],ax
                int end_real = SI + inst.getValue();
                IP = IP +3;
                if(opcode.charAt(1) == 'B'){  // mov ax,mem[si]
                    AX = memory.get(end_real).getValue();
                    updateFlags(AX);
                    label.setText("MOV AX,"+inst.getValue()+"["+SI+"]");
                    return -1;
                }
                else{  // mov mem[si],ax
                    Key algo = memory.get(end_real);
                    algo.setValue(AX);
                    label.setText("MOV "+inst.getValue()+"["+SI+"], AX");
                    return end_real;
                }
            }
            else if(opcode.equalsIgnoreCase("B8")){   // mov ax,cte
                AX = inst.getValue();
                updateFlags(AX);
                IP = IP +2;
                label.setText("MOV AX,#"+inst.getValue());
                return -1;
            } else {
                IP = IP;
                return -1;
            }
        }
     }
}
