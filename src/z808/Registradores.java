package z808.z808;

import java.lang.String;

public class Registradores{
    /*Registradores de Uso Geral*/
    private String AX;
    private String DX;
    private String SP;
    private String SI;
    private String IP;
    private String SR;
    private String CS;
    private String DS;
    private String SS;
    /*Registradores Arquiteturais */
    private String PC;
    private int LC;
    private String RI;
    private String REM;
    private String RBM;

    /**
     * Construtor
     */
    public Registradores(){
        this.SP="0000000000000000";
        this.SI="0000000000000000";
        this.IP="0000000000000000";
        this.SR="0000000000000000";
        this.CS="0000000000000000";
        this.DS="0000000000000000";
        this.SS="0000000000000000";
    }

    /**
     * Getters and Setters
     */
    public String getAX() {
        return AX;
    }

    public String getDX() {
        return DX;
    }

    public String getIP() {
        return IP;
    }

    public String getSI() {
        return SI;
    }

    public String getSP() {
        return SP;
    }

    public String getSR() {
        return SR;
    }

    public String getCS() {
        return CS;
    }

    public String getDS() {
        return DS;
    }

    public String getSS() {
        return SS;
    }

    public int getLC() {
        return LC;
    }

    public String getPC() {
        return PC;
    }

    public String getRBM() {
        return RBM;
    }

    public String getREM() {
        return REM;
    }

    public String getRI() {
        return RI;
    }

    public void setAX(String AX) {
        if(this.tamanhoRegistrador(AX)) {
            this.AX = AX;
        }
    }

    public void setDX(String DX) {
        if(this.tamanhoRegistrador(DX)) {
            this.DX = DX;
        }
    }

    public void setIP(String IP) {
        if(this.tamanhoRegistrador(IP)) {
            this.IP = IP;
        }
    }

    public void setSI(String SI) {
        if(this.tamanhoRegistrador(SI)) {
            this.SI = SI;
        }
    }

    public void setSP(String SP) {
        if(this.tamanhoRegistrador(SP)) {
            this.SP = SP;
        }
    }

    public void setSR(String SR) {
        if(this.tamanhoRegistrador(SR)) {
            this.SR = SR;
        }
    }

    public void setSR(char bit,int flag){
        char charSR[]=this.SR.toCharArray();
        charSR[flag]=bit;
        this.SR=new String(charSR);
    }

    public void setCS(String CS) {
        if(this.tamanhoRegistrador(CS)) {
            this.CS = CS;
        }
    }

    public void setDS(String DS) {
        if(this.tamanhoRegistrador(DS)) {
            this.DS = DS;
        }
    }

    public void setSS(String SS) {
        if(this.tamanhoRegistrador(SS)) {
            this.SS = SS;
        }
    }

    public void setLC(int LC) {
        this.LC = LC;
    }

    public void setRBM(String RBM) {
        if(this.tamanhoRegistrador(RBM)) {
            this.RBM = RBM;
        }
    }

    public void setREM(String REM) {
        if(this.tamanhoRegistrador(REM)) {
            this.REM = REM;
        }
    }

    public void setRI(String RI) {
        if(this.tamanhoRegistrador(RI)) {
            this.RI = RI;
        }
    }

    public void setPC(String PC) {
        if(this.tamanhoRegistrador(PC)) {
            this.PC = PC;
        }
    }

    private boolean tamanhoRegistrador(String Registrador){
        if(Registrador.length()==16) {
            return true;
        }
        return false;
    }

    public void addLC(){
        this.setLC((getLC() + 1));
    }
}