package z808.z808;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Linker {
    public void Linker() {

}

public void link()
{
    try {
        String replace;
        String[] aux;
        HashMap<String, String> tbs = new HashMap<>();
        HashMap<String, String> tbs_aux;
        HashMap<String, String> tbs_aux2 = new HashMap<>();

        Macro macro1 = new Macro();
        Builder montador1 = new Builder();
        macro1.run("entrada_link1.txt");
        montador1.Builder("entrada_link1_macro_processed.txt");
        tbs = montador1.getLista();

        Macro macro2 = new Macro();
        Builder montador2 = new Builder();
        macro2.run("entrada_link2.txt");
        montador2.Builder("entrada_link2_macro_processed.txt");
        tbs_aux = montador2.getLista();

        tbs.putAll(tbs_aux);



        BufferedReader file1 = new BufferedReader(new FileReader("entrada_link1_macro_processed_montada.txt")); //Lê na raiz do projeto
        BufferedReader file2 = new BufferedReader(new FileReader("entrada_link2_macro_processed_montada.txt")); //Lê na raiz do projeto
        String str;
        ArrayList<String> instructions = new ArrayList<>();

        File arqObjeto = new File("entrada_linked.txt");
        arqObjeto.createNewFile();
        FileWriter escritor = new FileWriter(arqObjeto);
        BufferedWriter bw = new BufferedWriter(escritor);

        while (file1.ready()) {
            str = file1.readLine().toUpperCase();
            instructions.add(str);
        }
        while (file2.ready()) {
            str = file2.readLine().toUpperCase();
            instructions.add(str);
        }


        for (int i = 0; i < instructions.size(); i++) {
            replace="";
            aux = instructions.get(i).split(" ");
            for(int j=0; j< aux.length; j++)
            {
                if(tbs.containsKey(aux[j]))
                {
                    aux[j] = tbs.get(aux[j]);
                }
                replace = replace.concat(aux[j]);
                replace = replace.concat(" ");
            }

            instructions.set(i, replace);
        }
            /*bw.write("1");
            bw.newLine();
            bw.write(String.valueOf(instructions.size()));
            bw.newLine();
            bw.write("1");
            bw.newLine();
            bw.write("0000");
            bw.newLine();*/


        for (int i = 0; i < instructions.size(); i++) {
            bw.write(instructions.get(i));
            bw.newLine();
        }



        file1.close();
        file2.close();
        bw.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

