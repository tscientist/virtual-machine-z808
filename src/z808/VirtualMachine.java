
package z808;

import z808.z808.Builder;
import z808.z808.Macro;

public class VirtualMachine {
    public static void main(String[] args) {

        Macro macro = new Macro();
        Builder montador = new Builder();
        macro.run("entrada.txt");
        montador.Builder();

        /*Z808 proc = new Z808(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Screen fr = new Screen(proc);
        fr.setVisible(true);*/
    }
}
