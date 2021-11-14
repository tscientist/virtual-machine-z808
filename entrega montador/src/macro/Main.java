/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macro;
import montador.*;

/**
 *
 * @author Leandro
 */
public class Main {
    public static void main(String[] args)
    {
        Macro macro = new Macro();    
        Montador montador = new Montador();
        macro.run("entrada.txt");
        montador.Montador();
    }
}
