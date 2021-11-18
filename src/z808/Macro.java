package z808;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Macro {

    public String nome;                                 //Armazena o nome da macro
    public List<String> corpo = new ArrayList<>();      //Armazena o corpo do macro
    public List<String> argumentos = new ArrayList<>(); //Armazena os argumentos passados
    public int MACRO = 0;                               //Armazena a linha de inicio da macro
    public int MEND = 0;                                //Armazena a linha de fim da macro

    public static void run(String diretorio) {

        List<Macro> macros = new ArrayList<>();
        File arquivo_entrada = new File(diretorio);
        File arquivo_saida = new File(diretorio.split("[.]")[0].concat("_macro_processed.txt"));

        List<String> linhas_entrada = new ArrayList<>();

        try {
            arquivo_saida.createNewFile();
            Scanner reader = new Scanner(arquivo_entrada);
            FileWriter fileWriter = new FileWriter(arquivo_saida, false);
            BufferedWriter escrever = new BufferedWriter(fileWriter);

            while (reader.hasNextLine()) {                                      //Armazena em linhas_entrada todas as linhas do arquivo original
                String line = reader.nextLine();
                line = line.toUpperCase();
                if (!line.isEmpty()) {
                    linhas_entrada.add(line);
                }
            }
            String[] linha_args;
            String[] aux1;
            String linha_nome;
            for (int i = 0; i < linhas_entrada.size(); i++) {                   //percorre todo o texto e procura por uma linha que contenha MACRO para armazenar a definicao
                if (linhas_entrada.get(i).contains("MACRO") == true) {
                    Macro macro = new Macro();
                    macro.MACRO = i;

                    aux1 = linhas_entrada.get(i).split(",");

                    aux1[0] = aux1[0].split(" ")[2];

                    linha_args = linhas_entrada.get(i).split(",");
                    linha_args[0] = linha_args[0].split(" ")[2];
                    linha_nome = linhas_entrada.get(i).split(" ")[0];
                    linha_args[1] = linha_args[1].split(";;")[0];


                    for (int l = 0; l < linha_args.length; ++l) {           //Armazena os argumentos da macro
                        linha_args[l] = linha_args[l].replace(" ", "");
                        macro.argumentos.add(linha_args[l]);
                    }
                    macro.nome = linha_nome;

                    for (int j = i + 1; linhas_entrada.get(j).compareTo("ENDM") != 0; j++) {    //Armazena o corpo da macro
                        macro.corpo.add(linhas_entrada.get(j));
                        macro.MEND = j + 1;
                    }
                    macros.add(macro);                                          //Adiciona o macro a uma lista de macros
                }
            }
            for (int i = macros.size() - 1; i >= 0; --i) {                      //Remove a definicao da macro de linhas_entrada
                linhas_entrada.removeAll(linhas_entrada.subList(macros.get(i).MACRO, macros.get(i).MEND));
            }

            for (int i = 0; i < linhas_entrada.size(); ++i) {                   //Percorre todo o texto comparando se na linha existe o nome de alguma macro
                Macro macro_chamada = new Macro();
                String[] label1 = linhas_entrada.get(i).split(" ", 2);

                for (Macro iterator : macros) {

                    if (label1[0].compareTo(iterator.nome) == 0) {

                        macro_chamada.nome = label1[0];

                        String[] aux_argumentos = label1[1].split(",");
                        for (int l = 0; l < aux_argumentos.length; ++l) {
                            aux_argumentos[l] = aux_argumentos[l].replace(" ", "");
                            macro_chamada.argumentos.add(aux_argumentos[l]);
                        }

                        for (int l = 0; l < iterator.corpo.size(); ++l) {
                            macro_chamada.corpo.add(iterator.corpo.get(l));
                        }

                        String replace = null;
                        String []aux;
                        String aux2;


                        for (int l = 0; l < macro_chamada.corpo.size(); ++l) {
                            aux = iterator.corpo.get(l).split(" ", 2);
                            aux[1] = aux[1].replace(" ", "");
                            aux2 = aux[1];

                            for (int k = 0; k < macro_chamada.argumentos.size(); ++k) {
                                aux2 = aux2.replace(iterator.argumentos.get(k), macro_chamada.argumentos.get(k));

                                replace = aux[0] + " " + aux2;
                                macro_chamada.corpo.set(l, replace);
                            }
                        }

                        linhas_entrada.remove(i);

                        for (int l = 0; l < macro_chamada.corpo.size(); ++l) {
                            linhas_entrada.add(i + l, macro_chamada.corpo.get(l));
                        }
                        i = 0;
                    }
                }
            }

            if ("ENDM".equals(linhas_entrada.get(0)))
                linhas_entrada.remove(0);
            for (String iterator : linhas_entrada) {                            //escreve no arquivo de saida o codigo expandido
                iterator = iterator.replace(",", " ");
                iterator = iterator.replace("  ", " ");

                escrever.write(iterator);
                escrever.newLine();
            }
            escrever.close();
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }


    }
}
