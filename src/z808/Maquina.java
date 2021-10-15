
package z808;

public class Maquina {
    public static void main(String[] args){
        Z808 proc = new Z808();
        Frame fr = new Frame(proc);
        fr.setVisible(true);
    }
}
