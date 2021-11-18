package z808.z808;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Loader {
    public void Loader() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("entrada_montada.txt")); //Lê na raiz do projeto
            String str;
            ArrayList<String> instructions = new ArrayList<>();

            File arqObjeto = new File("test4.txt");
            arqObjeto.createNewFile();
            FileWriter escritor = new FileWriter(arqObjeto);
            BufferedWriter bw = new BufferedWriter(escritor);

            while (in.ready()) {
                str = in.readLine().toUpperCase();
                instructions.add(str);
            }

            bw.write("1");
            bw.newLine();
            bw.write(String.valueOf(instructions.size()));
            bw.newLine();
            bw.write("1");
            bw.newLine();
            bw.write("0000");
            bw.newLine();

            for (int i = 0; i < instructions.size(); i++) {
                bw.write(instructions.get(i));
                bw.newLine();
            }

            bw.write("0000");
            bw.newLine();
            in.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}