package z808.z808;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Builder {
    private short memoria[] = new short[256];

    public void rotinaDeMontagem(){

        int contadorMemoria = 0;

        try {

            BufferedReader in = new BufferedReader(new FileReader("file_montador.txt")); //Lê na raiz do projeto
            String str;

            //Criação do arquivo para gravar código objeto
            File arqObjeto = new File("file_retorno.txt");
            arqObjeto.createNewFile();
            FileWriter escritor = new FileWriter(arqObjeto);
            BufferedWriter bw = new BufferedWriter(escritor); //variavel final para escrever no arquivo

            while (in.ready()) {
                str = in.readLine().toUpperCase();

                String delims = " ";
                String[] tokens = str.split(delims);

                for(int i=0; i< tokens.length; i++){
                    switch(tokens[i]){
                        case "ADD":
                            System.out.println("add");
                            bw.write("00");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;

                        case "SUB":
                            System.out.println("sub");
                            bw.write("01");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            //  System.out.println("SUB");
                            break;
                        case "MUL":
                            System.out.println("mul");
                            bw.write("02");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            //  System.out.println("MUL");
                            break;
                        case "DIV":
                            System.out.println("div");
                            bw.write("03");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            //  System.out.println("DIV");
                            break;
                        case "OR":
                            System.out.println("OR");
                            bw.write("04");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;
                        case "AND":
                            System.out.println("AND");
                            bw.write("05");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;
                        case "XOR":
                            System.out.println("XOR");
                            bw.write("06");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;
                        case "NOT":
                            System.out.println("NOT");
                            bw.write("07");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            break;
                        case "JMP":
                            System.out.println("JMP");
                            bw.write("08");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            break;
                        case "JE":
                            System.out.println("JE");
                            bw.write("09");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            break;
                        case "JNZ":
                            System.out.println("JNZ");
                            bw.write("10");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            break;
                        case "JZ":
                            System.out.println("JZ");
                            bw.write("11");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            break;
                        case "JP":
                            System.out.println("JP");
                            bw.write("12");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            i=i+1;
                            bw.newLine();
                            break;
                        case "MOV":
                            System.out.println("MOV");
                            bw.write("13");
                            bw.write(" "+ detectaRegistrador(tokens[i+1]));
                            bw.write(" "+ detectaRegistrador(tokens[i+2]));
                            i=i+2;
                            bw.newLine();
                            break;
                        case "HLT":
                            System.out.println("FIM DE EXECUÇÃO");
                            bw.write("99");
                            bw.newLine();
                            //i++;
                            break;

                        default:
                            //System.out.println("dado ");
                            i=i+2;
                            System.out.print(tokens[i]);
                            bw.write(tokens[i]);
                            bw.newLine();

                            break;
                    }//Fim switch

                }//Fim for

            }//Fim while leitura do arquivo
            in.close();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String detectaRegistrador(String entrada){

        String retorno = entrada;

        switch(entrada){
            case "AX":
                retorno = "-1";
                break;
            case "DX":
                retorno = "-2";
                break;
            case "SI":
                retorno = "-3";
                break;
        }
        return retorno;
    }
}