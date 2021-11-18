
package z808.z808;

public class VirtualMachine {
    public static void main(String[] args) {
        Linker ligador = new Linker();
        ligador.link("entrada_link1", "entrada_link2");
        Loader carregador = new Loader();
        carregador.loader();

        Z808 proc = new Z808(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Screen fr = new Screen(proc);
        fr.setVisible(true);
    }
}
