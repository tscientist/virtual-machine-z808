
package z808;

import z808.z808.Builder;
import z808.z808.Loader;
import z808.z808.Macro;
import z808.z808.Linker;

public class VirtualMachine {
    public static void main(String[] args) {

        Macro macro = new Macro();
        Builder montador = new Builder();
        Loader carregador = new Loader();

        String file = "entrada_macro_processed.txt";
        macro.run("entrada.txt");


        montador.Builder(file);
        carregador.Loader();

        Linker ligador = new Linker();
        ligador.link();

        Z808 proc = new Z808(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Screen fr = new Screen(proc);
        fr.setVisible(true);
    }
}
