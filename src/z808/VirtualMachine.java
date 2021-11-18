
package z808;
import z808.*;

public class VirtualMachine {
    public static void main(String[] args) {
        Z808 proc = new Z808(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Screen fr = new Screen(proc);
        fr.setVisible(true);
    }
}
