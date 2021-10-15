
package z808;

public class VirtualMachine {
    public static void main(String[] args) {
        Z808 proc = new Z808(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Frame fr = new Frame(proc);
        fr.setVisible(true);
    }
}
