package z808.z808;

public class Flags {
    public int SP = 0;// SP-STACK POINTER,
    public int IP = 0;// IP-INSTRUCTION POINTER
    public int AX = 0;// AX-OPERANDO DESTINO/FONTE
    public int DX = 0;// DX-OPERANDO FONTE
    public int SI = 0;// SI-ÍNDICE
    public int DS = 0;// DS-Segmento de Dados
    public int SS = 0;// SS-Segmento de Pilha
    public int CS = 0;//CS-Segmento de Código
    public int ZF = 0;
    public int SF = 0; // ZF-ZERO, SF-SINAL
    public int SR = 0;//SR-registrador de status, tem as flags OF,ZF e SF (as demais nao foram implementadas)
    public int qtde_dados1, qtde_dados2, qtde_inst1, qtde_inst2, index_hlt;

    public Flags(Integer qtde_dados1, Integer qtde_dados2, Integer qtde_inst1, Integer qtde_inst2, Integer index_hlt,
                 Integer SP, Integer IP, Integer AX, Integer DX, Integer SI, Integer DS, Integer SS, Integer CS, Integer ZF, Integer SF, Integer SR) {
        this.qtde_dados1 = qtde_dados1;
        this.qtde_dados2 = qtde_dados2;
        this.qtde_inst1 = qtde_inst1;
        this.qtde_inst2 = qtde_inst2;
        this.index_hlt = index_hlt;
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

    public void updateFlags(int destino) {
        if ((destino > Short.MAX_VALUE) || (destino < Short.MIN_VALUE)) {
            SR = SR | 4;
        }

        if (destino == 0) {
            ZF = 1;
            SF = 0;
            SR = SR | 2;
            SR = SR & 6;
        } else {
            ZF = 0;// SR = SR & 5;
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
}
