package z808.z808;

public class Flags {
    public Integer SP;// SP-STACK POINTER,
    public Integer IP;// IP-INSTRUCTION POINTER
    public Integer AX;// AX-OPERANDO DESTINO/FONTE
    public Integer DX;// DX-OPERANDO FONTE
    public Integer SI;// SI-ÍNDICE
    public Integer DS;// DS-Segmento de Dados
    public Integer SS;// SS-Segmento de Pilha
    public Integer CS;//CS-Segmento de Código
    public Integer ZF;// ZF-ZERO
    public Integer SF; //, SF-SINAL
    public Integer SR;//SR-registrador de status, tem as flags OF,ZF e SF (as demais nao foram implementadas)
    public int constantsMemory, dataMemory, instructionMemory, index_hlt;

    public Flags(Integer constantsMemory, Integer dataMemory, Integer instructionMemory, Integer index_hlt,
        Integer SP, Integer IP, Integer AX, Integer DX, Integer SI, Integer DS, Integer SS, Integer CS, Integer ZF, Integer SF, Integer SR) {

        this.SP = SP;
        this.IP = IP;
        this.AX = AX;
        this.DX = DX;
        this.SI = SI;
        this.DS = DS;
        this.SS = SS;
        this.CS = CS;
        this.ZF = ZF;
        this.SF = SF;
        this.SR = SR;
        this.constantsMemory = constantsMemory;
        this.dataMemory = dataMemory;
        this.instructionMemory = instructionMemory;
        this.index_hlt = index_hlt;
    }

    public Integer getCS() {//Pega Segmento de Código
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

    public int getConstantsMemory() {
        return constantsMemory;
    }

    public void setConstantsMemory(int constantsMemory) {
        this.constantsMemory = constantsMemory;
    }

    public int getDataMemory() {
        return dataMemory;
    }

    public void setDataMemory(int dataMemory) {
        this.dataMemory = dataMemory;
    }

    public int getInstructionMemory() {
        return instructionMemory;
    }

    public void setInstructionMemory(int instructionMemory) {
        this.instructionMemory = instructionMemory;
    }

    public void updateFlags(Integer destino) {
        if (destino == 0) {
            ZF = 1;
            SF = 0;
            SR = SR | 2;
            SR = SR & 6;
        } else {
            ZF = 0;
            if (destino > 0) {
                SF = 0;
                SR = SR & 4;
            } else {
                SF = 1;
                SR = SR | 1;
                SR = SR & 5;
            }
        }
    }

    public String updateBinString(String bin_string) {
        StringBuilder sb = new StringBuilder();
        Integer dif = 16 - bin_string.length();

        if (dif > 0) {
            for (int i = 0; i < dif; i++){
                sb.append('0');
            }
            return sb.toString().concat(bin_string);
        } else if (dif < 0){
            return bin_string.substring(bin_string.length() - 16, bin_string.length());
        } else {
            return bin_string;
        }
    }

    public String update32bitString(String res_inteira) {
        StringBuilder sb = new StringBuilder();
        Integer dif = 32 - res_inteira.length();

        if (dif > 0){
            for (int i = 0; i < dif; i++){
                sb.append('0');
            }
            return sb.toString().concat(res_inteira);
        } else{
            return res_inteira;
        }
    }

    public String reverseBinString(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }
}
